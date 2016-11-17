package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.ARRAY;

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
        Node firstElem = arraylits.get(0);

        for (ExpressionAST a: arraylits) {
            firstElem.checkType(a);
        }

        //initialies IDENTOBJ to array type
        identObj = new ARRAY(firstElem.getType(), arraylits.size());
    }

    public int getSize() {
        return arraylits.size();
    }
}
