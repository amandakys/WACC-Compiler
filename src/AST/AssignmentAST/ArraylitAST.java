package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;

import java.util.List;

public class ArraylitAST extends AssignrhsAST {
    //Array list has stores a list of expression
    private List<ExpressionAST> arraylits;

    public ArraylitAST(List<ExpressionAST> arraylits) {
        super();
        this.arraylits = arraylits;
    }


    @Override
    public void check() {
        //all expressions in the array list must have the same type
        for(Node a : arraylits) {
            a.check();
        }
    }
}
