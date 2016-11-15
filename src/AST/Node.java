package AST;

import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.IDENTIFIER;
import symbol_table.TYPE;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class Node {
    protected IDENTIFIER identObj;
    protected boolean isChecked = false;
    protected ParserRuleContext ctx;

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

    public TYPE getType() {
        return identObj.getType();
    }

    public IDENTIFIER getIdentObj() {
        return identObj;
    }

    public void checkType(Node node) {

        if (this.getType() == null) {
            //this value cannot be assigned to
            error("trying to assign to unassignable value");
        }

        if (!Compare.types(this.getType(), node.getType())) {
            error("types do not match\nexpected: " + this.getType() + "\nactual: " + node.getType());
        }
    }

    public void error(String message) {
        Visitor.error(ctx, message);
    }
}
