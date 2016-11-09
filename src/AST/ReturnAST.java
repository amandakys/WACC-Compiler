package AST;

import symbol_table.SymbolTable;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ReturnAST extends Node {
    Node expression;
    public ReturnAST(Node expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
    }
}
