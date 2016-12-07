package front_end.AST.StatementAST;

import front_end.AST.ProgramAST;
import front_end.symbol_table.SymbolTable;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class BeginAST extends StatementAST {
    private StatementAST statement;
    private SymbolTable ST;

    public BeginAST(ParserRuleContext ctx, StatementAST statement) {
        super(ctx);
        this.statement = statement;
        this.ST = Visitor.ST;
    }

    @Override
    public void check() {
        statement.checkNode();
    }

    @Override
    public void translate() {
        Visitor.ST = ST;
        ProgramAST.newScope(statement);
        Visitor.ST = ST.getEncSymbolTable();
    }

    @Override
    public void weight() {
        statement.weight();
        size = statement.getSize();
    }

    @Override
    public void IRepresentation() {
        StatementIRepresentation("begin");
        statement.IRepresentation();
    }
}
