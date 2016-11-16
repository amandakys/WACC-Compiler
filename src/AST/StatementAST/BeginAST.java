package AST.StatementAST;

import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.SymbolTable;

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
        statement.check();
    }
}
