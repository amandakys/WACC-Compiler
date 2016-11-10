package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;
import symbol_table.SymbolTable;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PairelemAST extends AssignrhsAST{
    Node expression;

    public PairelemAST(Node expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
    }
}
