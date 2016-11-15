package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.ARRAY;
import symbol_table.TYPE;

import java.util.List;

public class ArraylitAST extends AssignrhsAST {
    //Array list has stores a list of expression
    private List<ExpressionAST> arraylits;

    public ArraylitAST(ParserRuleContext ctx, List<ExpressionAST> arraylits) {
        super(ctx);
        this.arraylits = arraylits;
    }


    @Override
    public void check() {
        for(ExpressionAST a : arraylits) {
            a.checkNode();
        }

        //check all expressions are of the same type
        TYPE type = arraylits.get(0).getType();

        for (ExpressionAST a: arraylits) {
            if (!type.equals(a.getType())) {
                error("array elements must all be of same type");
            }
        }

        //initialies IDENTOBJ to array type
        identObj = new ARRAY(type, arraylits.size());
    }

    public int getSize() {
        return arraylits.size();
    }
}
