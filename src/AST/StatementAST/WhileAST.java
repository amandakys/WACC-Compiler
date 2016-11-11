package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Utility;
import main.Visitor;

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

        if(expression.getType().equals(Visitor.ST.lookUp("boolean"))) {
            //check that statement is valid
            statement.check();
        } else {
            Utility.error("expression is not of type boolean");
        }
    }
}
