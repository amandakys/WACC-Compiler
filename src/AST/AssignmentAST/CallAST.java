package AST.AssignmentAST;

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
    private FUNCTION funcObj;
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
            Visitor.ST.getEncSymbolTable().add(funcname, null);
           isDeclared = false;
//            Utility.error("Unknown function " + funcname);
        } else if (!(F instanceof FUNCTION)) {
            Utility.error(funcname + " is not a function");
        } else if (((FUNCTION)F).getParamList().size() != arglist.size()) {
            Utility.error("wrong no. of params");
        } else {
            arglist.check();

            for(int i = 0; i < arglist.size(); i++) {
                if(!arglist.getType(i).equals(((FUNCTION) F).getParamList().get(i))) {
                    Utility.error("unexpected type in function " + funcname + "\nexpected: " + ((FUNCTION) F).getParamList().get(i).getTypeName() + "\nactual: " + arglist.getType(i).getTypeName());
                }
            }

            funcObj = (FUNCTION)F;
            identObj = F;
        }
    }

    public boolean isDeclared() {
        return isDeclared;
    }

    @Override
    public TYPE getType() {
        return funcObj.getReturntype();
    }

}
