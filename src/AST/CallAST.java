package AST;

import main.Visitor;
import symbol_table.FUNCTION;
import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class CallAST extends Node {
    String funcname;
    ArglistAST arglist;
    FUNCTION funcObj;

    public CallAST(String funcname, ArglistAST arglist) {
        super();
        this.funcname = funcname;
        this.arglist = arglist;
    }
    @Override
    public void check() {
        IDENTIFIER F = Visitor.ST.lookUpAll(funcname);
        if (F == null) {
            System.err.println("Unknown function "+funcname);
        } else if (!(F instanceof FUNCTION)) {
            System.err.println(funcname + " is not a function");
        } else if (((FUNCTION)F).getParamList().size() != arglist.expressions.size())) {
            System.err.println("wrong no. of params");
        } else {
            arglist.check();
            ((ArglistAST)arglist).check(((FUNCTION) F).getParamList());
            funcObj = (FUNCTION)F;
        }
    }
}
