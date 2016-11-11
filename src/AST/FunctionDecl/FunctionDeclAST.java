package AST.FunctionDecl;

import AST.Node;
import AST.TypeAST.TypeAST;
import AST.Utility;
import main.Visitor;
import symbol_table.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FunctionDeclAST extends Node {
    private TypeAST returntype;
    private String returntypename;
    private String funcname;
    private ParamlistAST parameters;

    public FunctionDeclAST(TypeAST returntype, String funcname) {
        this.returntype = returntype;
        this.returntypename = returntype.getType().getTypeName();
        this.funcname = funcname;
        this.parameters = null;
    }
    public FunctionDeclAST(TypeAST returntype, String funcname, ParamlistAST paramList) {
        super();
        //return type name will remove all non alphanumeric characters to
        // search for primitive types
        this.returntype = returntype;
        this.returntypename = returntype.getType().getTypeName();
        this.funcname = funcname;
        this.parameters = paramList;
    }

    public void CheckFunctionNameAndReturnType() {
        IDENTIFIER returnType = null;
        if (returntype.getType() instanceof PAIR) {
            //return type is a pair
             returnType = new PAIR(((PAIR) returntype.getType()).getFirst(), ((PAIR) returntype.getType()).getSecond());
            //Visitor.ST.add();
        } else if (returntype.getType() instanceof ARRAY) {
            returnType = new ARRAY(((ARRAY) returnType.getType()).getElementType(), 0);
        } else {
            returnType = Visitor.ST.lookUpAll(returntypename);
            IDENTIFIER F = Visitor.ST.lookUp(funcname);

            if (returntype == null) {
                Utility.error("Unknown type " + returntypename);
            } else if (!(returntype.getType() instanceof TYPE)) {
                Utility.error(returntypename + " is not a type");
            } else if (!(returntype.getType()).isReturnable()) {
                Utility.error("cannot return " + returntypename + " objects");
            } else if (F != null) {
                Utility.error(funcname + " is already declared");
            } else {
                Visitor.ST.add(funcname, identObj);
            }
        }
        FUNCTION f;
        if (parameters == null) {
            //no parameters
            f = new FUNCTION(null, (TYPE) returnType, new ArrayList<TYPE>());
        } else {
            f = new FUNCTION(null, (TYPE) returnType, parameters.getParamTypes());
        }
        identObj = f;
    }

    @Override
    public void check() {
        CheckFunctionNameAndReturnType();
        if (parameters != null) {
            List<ParamAST> paramASTs = parameters.getParams();

            for (ParamAST p : paramASTs) {
                Visitor.ST.add(p.getIdent(), p.getType());
            }
        }

        ((FUNCTION) identObj).setSymbolTable(Visitor.ST);

    }
}
