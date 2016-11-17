package AST.StatementAST;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class SequenceAST extends StatementAST {
    private List<StatementAST> statements;

    public SequenceAST(ParserRuleContext ctx, List<StatementAST> statements) {
        super(ctx);
        this.statements = statements;
    }

    @Override
    public void check() {
        for (StatementAST s : statements) {
            s.checkNode();
        }
    }
}
