package AST.AssignmentAST;

import AST.Compare;
import AST.FunctionDecl.ArglistAST;
import AST.Utility;
import main.Visitor;
import symbol_table.FUNCTION;
import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class CallAST extends AssignrhsAST{
    private String funcname;
    private ArglistAST arglist;
    private boolean isDeclared = true;

    public CallAST(String funcname, ArglistAST arglist) {
        super();
        this.funcname = funcname;
        this.arglist = arglist;
    }
    @Override
    public void check() {
        IDENTIFIER F = Visitor.ST.lookUpAll(funcname);

        if (F == null) {
            Visitor.ST.getEncSymbolTable().add(funcname, new FUNCTION(null, null, null));
            isDeclared = false;
        } else if (!(F instanceof FUNCTION)) {
            Utility.error(funcname + " is not a function");
        } else if (((FUNCTION) F).getReturntype() == null) { // case when function F is not declared
            Utility.error("Unknown function " + funcname);
        } else if (((FUNCTION)F).getParamList().size() != arglist.size()) {
            Utility.error("wrong no. of params");
        } else {
            arglist.check();

            for(int i = 0; i < arglist.size(); i++) {
                if (!Compare.types(arglist.getType(i), ((FUNCTION) F).getParamList().get(i))){
                    Utility.error("unexpected type in function " + funcname + "\nexpected: " + ((FUNCTION) F).getParamList().get(i)+ "\nactual: " + arglist.getType(i));
                }
            }

            identObj = F;
        }
    }

    public boolean isDeclared() {
        return isDeclared;
    }

    @Override
    public TYPE getType() {
        return ((FUNCTION) identObj).getReturntype();
    }

}
