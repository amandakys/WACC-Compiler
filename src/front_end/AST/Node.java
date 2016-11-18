package front_end.AST;

import back_end.data_type.Register;
import back_end.instruction.Instruction;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class Node {
    //initialising registers available in a program
    protected Register r0 = new Register(0);
    protected Register r1 = new Register(1);
    protected Register r2 = new Register(2);
    protected Register r3 = new Register(3);
    protected Register r4 = new Register(4);
    protected Register r5 = new Register(5);
    protected Register r6 = new Register(6);
    protected Register r7 = new Register(7);
    protected Register r8 = new Register(8);
    protected Register r9 = new Register(9);
    protected Register r10 = new Register(10);
    protected Register r11 = new Register(11);
    protected Register r12 = new Register(12);
    protected Register sp = new Register(13);
    protected Register lr = new Register(14);
    protected Register pc = new Register(15);

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
}
