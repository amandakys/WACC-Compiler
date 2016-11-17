package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.IDENTIFIER;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ExitAST extends StatementAST{
    private ExpressionAST expression;

    public ExitAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        IDENTIFIER T = Visitor.ST.lookUpAll("int");
        expression.checkNode();
        if(!expression.getType().equals(T.getType())) {
            error("Exit statement must take integer");
        }
    }
}
