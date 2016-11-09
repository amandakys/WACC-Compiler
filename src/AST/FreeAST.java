package AST;

import main.Visitor;
import symbol_table.SymbolTable;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FreeAST extends Node {
    ExpressionAST expression;

    public FreeAST(ExpressionAST expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
        TYPE arrayType = Visitor.ST.lookUpAll("array").getType();
        if (expression.getType() != arrayType) {
            //error
        }
    }
}
