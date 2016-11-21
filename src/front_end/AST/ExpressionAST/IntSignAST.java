package front_end.AST.ExpressionAST;

import back_end.data_type.Register;
import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

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
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {

    }
}
