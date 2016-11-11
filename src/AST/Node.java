package AST;

import symbol_table.ARRAY;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;
import symbol_table.TYPE;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class Node {
    protected IDENTIFIER identObj;

    public abstract void check();

    public TYPE getType() {
        return identObj.getType();
    }

    public IDENTIFIER getIdentObj() {
        return identObj;
    }

    public void checkType(Node node) {

        if (!Compare.types(this.getType(), node.getType())) {
            Utility.error("types do not match\nexpected: " + this.getType() + "\nactual: " + node.getType());
        }

        if (this.getType() == null) {
            //this value cannot be assigned to
            Utility.error("trying to assign to unassignable value");
        } else if ((this.getType() instanceof ARRAY) && (node.getType() instanceof ARRAY)) {
            TYPE thisElemType = ((ARRAY) this.getType()).getElementType();
            TYPE nodeElemType = ((ARRAY) node.getType()).getElementType();
            if (!thisElemType.equals(nodeElemType)) {
                Utility.error("types do not match\nexpected: array of" + thisElemType.getTypeName() + "\nactual: array of" + nodeElemType.getTypeName());
            }

        }
//        if (this.getType() instanceof PAIR) {
//            if (!this.getType().equals(node.getType())) {
//                //error
//                Utility.error("types do not match\nexpected " + this.getType().getTypeName() + "\nactual: " + node.getType().getTypeName());
//            }
//        }
        if (!this.getType().getTypeName().equals(node.getType().getTypeName())) {
            Utility.error("types do not match\nexpected: " + this.getType().toString() /*this.getType().getTypeName()*/ + "\nactual: " + node.getType().toString() /*node.getType().getTypeName()*/);
        }
    }

}
