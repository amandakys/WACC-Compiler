package front_end.AST.StatementAST;

import org.antlr.v4.runtime.ParserRuleContext;

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

