package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
<<<<<<< HEAD
=======
import AST.Node;
import symbol_table.SymbolTable;
>>>>>>> semanticsV2

/**
 * Created by tsd15 on 09/11/16.
 */
public class PairelemAST extends AssignrhsAST{
    ExpressionAST expression;

    public PairelemAST(ExpressionAST expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
        identObj = expression.getType();
    }
}
