package front_end.AST.StatementAST;

import front_end.AST.ExpressionAST.ExpressionAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public class WhileAST extends StatementAST {
    ExpressionAST expression;
    StatementAST statement;

    public WhileAST(ParserRuleContext ctx, ExpressionAST expression, StatementAST statement) {
        super(ctx);
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public void check() {
        //check that expression is valid
        expression.checkNode();

        if (expression.getType().equals(Visitor.ST.lookUpAll("bool"))) {
            //check that statement is valid
            statement.checkNode();
        } else {
            error("expression is not of type boolean");
        }
    }

    @Override
    public void translate() {
        expression.translate();
    }
}