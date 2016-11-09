package AST;

import main.Visitor;
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

    public FunctionDeclarationAST(String returntypename, String funcname, Node paramList) {
        super();
        this.returntypename = returntypename;
        this.funcname = funcname;
        this.parameters = paramList;
    }

    public void CheckFunctionNameAndReturnType() {
        IDENTIFIER T = Visitor.ST.lookUpAll(returntypename);
        IDENTIFIER F = Visitor.ST.lookUp(funcname);
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
            Visitor.ST.add(funcname, funcObj);
        }
    }

    @Override
    public void check() {
        CheckFunctionNameAndReturnType();
        Visitor.ST = new SymbolTable(Visitor.ST);
        funcObj.setSymbolTable(Visitor.ST);
        for (Node p : ((ParamlistAST)parameters).getExpressions()) {
            p.check();
            funcObj.getParamList().addParam(p);
        }
        Visitor.ST = Visitor.ST.getEncSymbolTable();
    }
}
