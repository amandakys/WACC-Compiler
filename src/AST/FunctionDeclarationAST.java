package AST;

import main.Visitor;
import symbol_table.*;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FunctionDeclarationAST extends Node {
    private String returntypename;
    private String funcname;
    private ParamlistAST parameters;
    private FUNCTION funcObj;

    public FunctionDeclarationAST(String returntypename, String funcname, ParamlistAST paramList) {
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
