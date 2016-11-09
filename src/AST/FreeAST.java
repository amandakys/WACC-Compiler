package AST;

import symbol_table.SymbolTable;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FreeAST extends Node {
    Node expression;

    public FreeAST(Node expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
        TYPE arrayType = ST.lookUpAll("array").getType();
        if (expression.getType() != arrayType) {
            //error
        }
    }
}
