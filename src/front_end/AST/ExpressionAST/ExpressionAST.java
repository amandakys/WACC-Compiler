package front_end.AST.ExpressionAST;

import back_end.data_type.register.Register;
import front_end.AST.AssignmentAST.AssignrhsAST;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class ExpressionAST extends AssignrhsAST {
    public ExpressionAST(ParserRuleContext ctx) {
        super(ctx);
    }

}
