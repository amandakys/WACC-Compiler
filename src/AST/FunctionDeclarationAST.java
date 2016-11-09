package AST;

import symbol_table.*;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FunctionDeclarationAST extends Node {
    String returntypename;
    String funcname;
    Node parameters;
    FUNCTION funcObj;

    public FunctionDeclarationAST(SymbolTable ST, String returntypename, String funcname, Node paramList) {
        super(ST);
        this.returntypename = returntypename;
        this.funcname = funcname;
        this.parameters = paramList;
    }

    public void CheckFunctionNameAndReturnType() {
        IDENTIFIER T = ST.lookUpAll(returntypename);
        IDENTIFIER F = ST.lookUp(funcname);
        if (T == null) {
            System.err.println("Unknown type "+returntypename);
        } else if (!(T instanceof TYPE)) {
            System.err.println(returntypename+" is not a type");
        } else if (!((TYPE)T).isReturnable()) {
            System.err.println("cannot return "+returntypename+" objects");
        } else if (F != null) {
            System.err.println(funcname+" is already declared");
        } else if (!(parameters instanceof ParamlistAST)) {
            System.err.println("Downcast error");
        } else {
            ParamlistAST paramlistAST;
            funcObj= new FUNCTION(null, (TYPE)T, null);
            ST.add(funcname, funcObj);
        }
    }

    @Override
    public void check() {
        CheckFunctionNameAndReturnType();
        ST = new SymbolTable(ST);
        funcObj.setSymbolTable(ST);
        for (Node p : ((ParamlistAST)parameters).getExpressions()) {
            p.check();
            funcObj.getParamList().addParam(p);
        }
        ST = ST.getEncSymbolTable();
    }
}
