package AST.StatementAST;

import AST.Node;
import main.Visitor;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ReturnAST extends StatementAST {
    Node expression;
    public ReturnAST(Node expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        //check that return expression returns correct type
        expression.check();
    }
}
