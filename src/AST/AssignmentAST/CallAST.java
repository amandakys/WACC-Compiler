package AST.AssignmentAST;

import AST.FunctionDecl.ArglistAST;
import AST.Node;
import main.Visitor;
import symbol_table.FUNCTION;
import symbol_table.IDENTIFIER;

/**
 * Created by tsd15 on 09/11/16.
 */
public class CallAST extends Node {
    private String funcname;
    private ArglistAST arglist;
    private FUNCTION funcObj;

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
        } else if (((FUNCTION)F).getParamList().size() != arglist.size()) {
            System.err.println("wrong no. of params");
        } else {
            arglist.check();

            for(int i = 0; i < arglist.size(); i++) {
                if(arglist.getType(i).equals(((FUNCTION) F).getParamList().get(i))) {
                    System.err.println("unexpected type in function " + funcname);
                }
            }

            funcObj = (FUNCTION)F;
            identObj = F;
        }
    }
}
