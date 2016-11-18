package front_end.AST.ExpressionAST;

import front_end.AST.AssignmentAST.AssignrhsAST;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class ExpressionAST extends AssignrhsAST {
    public ExpressionAST(ParserRuleContext ctx) {
        super(ctx);
    }
}
