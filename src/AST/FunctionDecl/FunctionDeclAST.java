package AST.FunctionDecl;

import AST.Node;
import AST.Utility;
import main.Visitor;
import symbol_table.*;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FunctionDeclAST extends Node {
    private String returntypename;
    private String funcname;
    private ParamlistAST parameters;
    private FUNCTION funcObj;

    public FunctionDeclAST(String returntypename, String funcname, ParamlistAST paramList) {
        super();
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
            funcObj= new FUNCTION(null, (TYPE)T, parameters.getParamTypes());
            Visitor.ST.add(funcname, funcObj);
        }
    }

    @Override
    public void check() {
        CheckFunctionNameAndReturnType();

        Visitor.ST = new SymbolTable(Visitor.ST);
        funcObj.setSymbolTable(Visitor.ST);
        parameters.check();

        Visitor.ST = Visitor.ST.getEncSymbolTable();
    }
}
