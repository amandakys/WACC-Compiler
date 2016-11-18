package front_end.AST.StatementAST;

import back_end.instruction.Instruction;
import front_end.AST.ExpressionAST.ExpressionAST;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FreeAST extends StatementAST {
    ExpressionAST expression;

    public FreeAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.checkNode();

        if (!(expression.getType() instanceof PAIR)) {
            error("free must take a pair type");
        }
    }

    @Override
    public void translate() {

    }
}
