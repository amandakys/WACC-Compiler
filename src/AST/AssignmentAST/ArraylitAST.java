package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ArraylitAST extends AssignrhsAST {
    private List<ExpressionAST> arraylits;

    public ArraylitAST(List<ExpressionAST> arraylits) {
        super();
        this.arraylits = arraylits;
    }


    @Override
    public void check() {
        for(Node a : arraylits) {
            a.check();
        }
    }
}
