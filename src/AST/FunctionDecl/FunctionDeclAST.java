package AST.FunctionDecl;

import AST.Node;
import AST.Utility;
import main.Visitor;
import symbol_table.*;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FunctionDeclAST extends Node {
    private String returntypename;
    private String funcname;
    private ParamlistAST parameters;

    public FunctionDeclAST(String returntypename, String funcname, ParamlistAST paramList) {
        super();
        //return type name will remove all non alphanumeric characters to
        // search for primitive types
        this.returntypename = returntypename;
        this.funcname = funcname;
        this.parameters = paramList;
    }

    public void CheckFunctionNameAndReturnType() {
        IDENTIFIER T = Visitor.ST.lookUpAll(returntypename);
        IDENTIFIER F = Visitor.ST.lookUp(funcname);

        if (T == null) {
            Utility.error("Unknown type "+returntypename);
        } else if (!(T instanceof TYPE)) {
            Utility.error(returntypename+" is not a type");
        } else if (!((TYPE)T).isReturnable()) {
            Utility.error("cannot return "+returntypename+" objects");
        } else if (F != null) {
            Utility.error(funcname+" is already declared");
        } else {
            identObj= new FUNCTION(null, (TYPE)T, parameters.getParamTypes());
            Visitor.ST.add(funcname, identObj);
        }
    }

    @Override
    public void check() {
        CheckFunctionNameAndReturnType();

        List<ParamAST> params = parameters.getParams();

        for (ParamAST p: params) {
            Visitor.ST.add(p.getIdent(), p.getType());
        }

        ((FUNCTION) identObj).setSymbolTable(Visitor.ST);
    }
}
