package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;
import AST.Utility;
import symbol_table.ARRAY;
import symbol_table.SymbolTable;
import symbol_table.TYPE;

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
        for(ExpressionAST a : arraylits) {
            a.check();
        }

        //check all expressions are of the same type
        TYPE type = arraylits.get(0).getType();

        for (ExpressionAST a: arraylits) {
            if (!type.equals(a.getType())) {
                Utility.error("array elements must all be of same type");
            }
        }

        //initialies IDENTOBJ to array type
        identObj = new ARRAY(type, arraylits.size());
    }
}
