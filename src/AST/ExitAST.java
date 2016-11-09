package AST;

import symbol_table.SymbolTable;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ExitAST extends Node {
    Node expression;

    public ExitAST(Node expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
    }
}
