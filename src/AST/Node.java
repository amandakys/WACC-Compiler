package AST;

import symbol_table.IDENTIFIER;
import symbol_table.PAIR;
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
        if (this.getType() == null) {
            //this value cannot be assigned to
            Utility.error("trying to assign to unassignable value");
        }

        if (this.isPair() && node.isPair()) {
            //compare inner types
            TYPE thisFirst = ((PAIR) (this.getIdentObj())).getFirst();
            TYPE thisSecond = ((PAIR) (this.getIdentObj())).getSecond();
            TYPE nodeFirst = ((PAIR) (node.getIdentObj())).getFirst();
            TYPE nodeSecond = ((PAIR) (node.getIdentObj())).getSecond();


            if (nodeFirst != null) {
                if ((thisFirst.equals(nodeFirst)) && (thisSecond.equals(nodeSecond))){
                    //pair is valid
                } else {
                    Utility.error("types in pair do not match\n expected: (" + thisFirst.getTypeName()+", " + thisSecond.getTypeName() + ")" + "\nactual: (" + nodeFirst.getTypeName() + ", " + nodeSecond.getTypeName() + ")");
                }
            } else {

            }
        } else {
            if (!this.getType().equals(node.getType())) {
                Utility.error("types do not match\nexpected: " + this.getType().getTypeName() + "\nactual: " + node.getType().getTypeName());
            }
        }
    }

    private boolean isPair() {
        if (this.getType() instanceof PAIR) {
            return true;
        }
//        if (node.getType() == null) {
//            if(this.getIdentObj() instanceof PAIR) {
//                return true;
//            }
//        }
        return false;
    }
}
