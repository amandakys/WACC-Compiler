package front_end.AST.StatementAST;

import org.antlr.v4.runtime.ParserRuleContext;

public class SkipAST extends StatementAST {

    public SkipAST(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void check() {
    }

    public boolean determineLoopInvariance() {
        return false;
    }

    @Override
    public void translate() {}

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {
        defaultIRep("skip");
    }
}

