package AST.AssignmentAST;

import AST.Node;
import AST.StatementAST.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 08/11/2016.
 */

public class AssignmentAST extends StatementAST {
    AssignlhsAST lhs;
    AssignrhsAST rhs;

    public AssignmentAST(ParserRuleContext ctx, AssignlhsAST lhs, AssignrhsAST rhs) {
        super(ctx);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void check() {
        lhs.check();
        rhs.check();

        if(!(rhs instanceof CallAST) ||
                (rhs instanceof CallAST && ((CallAST) rhs).isDeclared())) {
            lhs.checkType(rhs);
        }
    }
}
