package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PrintlnAST extends StatementAST {
    ExpressionAST expression;

    public PrintlnAST(ExpressionAST expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
       // identObj =
        expression.check();
    }
}
