package front_end.AST.StatementAST;

import front_end.AST.Node;
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

    @Override
    public void translate() {

    }
}
