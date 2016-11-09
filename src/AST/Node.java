package AST;

import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;
import symbol_table.TYPE;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class Node  {
    protected SymbolTable ST;
    protected IDENTIFIER identObj;

    public Node(SymbolTable ST) {
        this.ST = ST;
    }


    public abstract void check();

    public TYPE getType() {
        return identObj.getType();
    }

    public void checkType(Node node) {

        if (!this.getType().getClass().equals(node.getType().getClass())) {
            System.err.println("types do not match");
        }
    }
}
