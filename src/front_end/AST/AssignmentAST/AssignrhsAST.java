package front_end.AST.AssignmentAST;

import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class AssignrhsAST extends Node {

    public AssignrhsAST(ParserRuleContext ctx) {
        super(ctx);

    }
}
