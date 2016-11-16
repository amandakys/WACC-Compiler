package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.PAIR;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FreeAST extends StatementAST {
    ExpressionAST expression;

    public FreeAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();

        if (!(expression.getType() instanceof PAIR)) {
            error("free must take a pair type");
        }
    }
}
