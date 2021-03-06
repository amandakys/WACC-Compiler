package front_end.AST.StatementAST;

import back_end.Utility;
import front_end.AST.ExpressionAST.IdentAST;
import front_end.AST.ProgramAST;
import main.Visitor;
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
        }
    }

    @Override
    public void weight() {
        for(StatementAST stat :  statements) {
            stat.weight();
            size += stat.getSize();
        }
    }

    @Override
    public void IRepresentation() {
        for(StatementAST statement : statements) {
            statement.IRepresentation();
            if(IGNode == null) {
                IGNode = statement.getIGNode();
            }
        }
    }

    @Override
    public void extractLoopInvariants(List<String> idents) {
        for (StatementAST stat: statements) {
            stat.extractLoopInvariants(idents);
        }
    }

    public List<StatementAST> getStatements() {
        return statements;
    }

    @Override
    public void extractLoopDependents(List<String> idents) {
        for (StatementAST stat: statements) {
            stat.extractLoopDependents(idents);
        }
    }

    @Override
    public void findLoopInvariants() {
        for (StatementAST stat: statements) {
            stat.findLoopInvariants();
        }
    }
}
