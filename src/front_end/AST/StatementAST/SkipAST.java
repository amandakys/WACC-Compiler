package front_end.AST.StatementAST;

import back_end.data_type.Register;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

/**
 * Created by andikoh on 10/11/2016.
 */
public class SkipAST extends StatementAST {

    public SkipAST(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void check() {

    }

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {

    }
}

