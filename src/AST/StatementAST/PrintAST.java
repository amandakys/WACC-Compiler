package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PrintAST extends StatementAST {
    ExpressionAST expression;

    public PrintAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.checkNode();
    }
}
