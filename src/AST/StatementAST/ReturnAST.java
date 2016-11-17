package AST.StatementAST;

import AST.Node;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ReturnAST extends StatementAST {
    Node expression;
    public ReturnAST(ParserRuleContext ctx, Node expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        //check that return expression returns correct type
        expression.checkNode();
    }
}
