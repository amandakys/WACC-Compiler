package AST.StatementAST;

import main.Visitor;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by andikoh on 10/11/2016.
 */
public class SequenceAST extends StatementAST {
    List<StatementAST> statements;

    public SequenceAST(List<StatementAST> statements) {
        this.statements = statements;
    }

    @Override
    public void check() {
        Visitor.ST = new SymbolTable(Visitor.ST.getEncSymbolTable());
        for (StatementAST s : statements) {
            s.check();
        }
    }
}
