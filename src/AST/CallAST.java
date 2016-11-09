package AST;

import symbol_table.FUNCTION;
import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class CallAST extends Node {
    String funcname;
    Node arglist;
    FUNCTION funcObj;

    public CallAST(SymbolTable ST, String funcname, Node arglist) {
        super(ST);
        this.funcname = funcname;
        this.arglist = arglist;
    }
    @Override
    public void check() {
        IDENTIFIER F = ST.lookUpAll(funcname);
        if (F == null) {
            System.err.println("Unknown function "+funcname);
        } else if (!(F instanceof FUNCTION)) {
            System.err.println(funcname + " is not a function");
        } else if (!(arglist instanceof ArglistAST))     {
            System.err.println("downcast failure");
        } else if (!(((FUNCTION)F).getParams().parameters.size() == ((ArglistAST)arglist).expressions.size())) {
            System.err.println("wrong no. of params");
        } else {
            arglist.check();
            ((ArglistAST)arglist).check(((FUNCTION) F).getParams());
            funcObj = (FUNCTION)F;
        }
    }
}
