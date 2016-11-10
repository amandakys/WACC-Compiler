package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;
import main.Visitor;
import symbol_table.IDENTIFIER;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ExitAST extends StatementAST{
    private ExpressionAST expression;

    public ExitAST(ExpressionAST expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        IDENTIFIER T = Visitor.ST.lookUpAll("int");
        expression.check();

        if(!expression.getType().equals(T.getType())) {
            System.err.println("Exit statement must take integer");
        }
    }
}
