package front_end.AST.StatementAST;

import back_end.Utility;
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

    @Override
    public void translate() {
        for(StatementAST stat : statements) {
            stat.translate();
            Utility.pushBackParam();
        }


    }
}
