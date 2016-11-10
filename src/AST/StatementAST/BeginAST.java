package AST.StatementAST;

import main.Visitor;
import symbol_table.SymbolTable;

/**
 * Created by andikoh on 10/11/2016.
 */
public class BeginAST extends StatementAST {
    StatementAST statement;

    public BeginAST(StatementAST statement) {
        this.statement = statement;
    }
    @Override
    public void check() {
        Visitor.ST = new SymbolTable(Visitor.ST.getEncSymbolTable());
    }
}
