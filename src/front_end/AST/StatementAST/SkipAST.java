package front_end.AST.StatementAST;

import org.antlr.v4.runtime.ParserRuleContext;

public class SkipAST extends StatementAST {

    public SkipAST(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
<<<<<<< HEAD
    public void check() {}
=======
    public boolean determineLoopInvariance() {
        return false;
    }

    @Override
    public void check() {
>>>>>>> b254bb1a8196684f77b37759ce994e5fdbb3cbb0

    @Override
    public void translate() {}

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {

    }
}

