package front_end.AST.StatementAST;

import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public abstract class StatementAST extends Node {
    protected static Integer labelCount = 0;
    public StatementAST(ParserRuleContext ctx) {
        super(ctx);
    }

}
