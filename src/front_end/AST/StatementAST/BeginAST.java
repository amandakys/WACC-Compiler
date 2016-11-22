package front_end.AST.StatementAST;

import front_end.AST.ExpressionAST.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public class BeginAST extends StatementAST {
    StatementAST statement;

    public BeginAST(ParserRuleContext ctx, StatementAST statement) {
        super(ctx);
        this.statement = statement;
    }
    @Override
    public void check() {
        statement.checkNode();
    }

    @Override
    public void translate() {
        statement.translate();
    }
}
