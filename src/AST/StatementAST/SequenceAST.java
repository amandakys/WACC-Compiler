package AST.StatementAST;

import java.util.List;

public class SequenceAST extends StatementAST {
    private List<StatementAST> statements;

    public SequenceAST(List<StatementAST> statements) {
        this.statements = statements;
    }

    @Override
    public void check() {
        for (StatementAST s : statements) {
                s.check();
        }
    }
}
