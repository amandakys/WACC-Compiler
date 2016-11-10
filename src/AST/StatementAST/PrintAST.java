package AST.StatementAST;

import AST.Node;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PrintAST extends StatementAST {
    Node expression;

    public PrintAST(Node expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();
    }
}
