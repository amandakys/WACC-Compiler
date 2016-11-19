package front_end.AST;

import back_end.data_type.Register;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class Node {
    protected final static int SP = 13;
    protected final static int LR = 14;
    protected final static int PC = 15;

    protected IDENTIFIER identObj;
    protected ParserRuleContext ctx;
    private boolean isChecked = false;

    public Node(ParserRuleContext ctx) {
        this.ctx = ctx;
    }

    public void checkNode() {
        if(!isChecked) {
            check();
            isChecked = true;
        }
    }

    protected abstract void check();

    public IDENTIFIER getIdentObj() {
        return identObj;
    }

    public void checkType(Node node) {

        if (getType() == null) {
            //this value cannot be assigned to
            error("trying to assign to unassignable value");
        }

        if (!Compare.types(this.getType(), node.getType())) {
            error("types do not match\nexpected: " + this.getType() + "\nactual: " + node.getType());
        }
    }

    public TYPE getType() {
        return identObj.getType();
    }

    protected void error(String message) {
        Visitor.error(ctx, message);
    }

    protected void checkIfInScope(String name) {
        IDENTIFIER N = Visitor.ST.lookUpAll(name);
        if(N != null) {
            error(name + " has already been declared");
        }
    }

    public abstract void translate();

    public Register getRegister(int i) {
        return CodeGen.registers.get(i);
    }
}
