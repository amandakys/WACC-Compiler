package front_end.AST.StatementAST;

import front_end.AST.ExpressionAST.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public class SkipAST extends StatementAST {

    public SkipAST(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void check() {

    }

    @Override
    public void translate() {

    }
}

