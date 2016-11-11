package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PrintAST extends StatementAST {
    ExpressionAST expression;

    public PrintAST(ExpressionAST expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
    }
}
