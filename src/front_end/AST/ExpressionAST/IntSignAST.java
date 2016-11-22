package front_end.AST.ExpressionAST;

import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 08/11/2016.
 */
public class IntSignAST extends Node {

    public IntSignAST(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void check() {
        //no checked needed
    }

    @Override
    public void translate() {

    }
}
