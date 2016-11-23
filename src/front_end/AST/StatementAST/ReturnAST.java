package front_end.AST.StatementAST;

import back_end.data_type.Register;
import back_end.instruction.Instruction;
import back_end.instruction.data_manipulation.Mov;
import com.sun.org.apache.regexp.internal.RE;
import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Deque;
import java.util.List;

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
Deque<Register> unused;
    @Override
    public void translate(Deque<Register> unused/*r4-r13*/) {
        expression.translate(/*r5-r13*/);

        Register dst = unused.pop();
        expression.translate(unused);
        Register result = unused.pop();
        new Mov(dst,result);
        unused.addFirst(result);

    }
}
-