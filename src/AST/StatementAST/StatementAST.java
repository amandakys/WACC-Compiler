package AST.StatementAST;

import AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public abstract class StatementAST extends Node {
    public StatementAST(ParserRuleContext ctx) {
        super(ctx);
    }
}
