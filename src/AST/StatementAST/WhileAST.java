package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;

/**
 * Created by andikoh on 10/11/2016.
 */
public class WhileAST extends StatementAST {
    ExpressionAST expression;
    StatementAST statement;

    public WhileAST(ExpressionAST expression, StatementAST statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public void check() {
        //check that expression is valid
        expression.check();
        //TODO:check that expression is a boolean
        //check that statement is valid
        statement.check();
    }
}
