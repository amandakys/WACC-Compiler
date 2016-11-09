package AST;

import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;
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

    public void checkType(Node node) {
        if (!this.getType().equals(node.getType())) {
            System.err.println("types do not match");
        }
    }
}
