package AST;

import symbol_table.IDENTIFIER;
import symbol_table.TYPE;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class Node  {
    protected IDENTIFIER identObj;

    public abstract void check();

    public TYPE getType() {
        return identObj.getType();
    }

    public IDENTIFIER getIdentObj() {
        return identObj;
    }

    public void checkType(Node node) {
        if (!this.getType().equals(node.getType())) {
            Utility.error("types do not match\nexpected: " + this.getType().getTypeName() + "\nactual: " + node.getType().getTypeName());
        }
    }
}
