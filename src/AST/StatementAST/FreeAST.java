package AST.StatementAST;

import AST.AssignmentAST.ArraylitAST;
import AST.ExpressionAST.ExpressionAST;
import AST.Utility;
import main.Visitor;
import symbol_table.ARRAY;
import symbol_table.PAIR;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FreeAST extends StatementAST {
    ExpressionAST expression;

    public FreeAST(ExpressionAST expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
        //TYPE type = expression.getType();

        if (!(expression.getIdentObj() instanceof ARRAY) && !(expression.getIdentObj() instanceof PAIR)) {
            Utility.error("free must take array or pair type");
        }
    }
}
