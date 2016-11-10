package AST.AssignmentAST;

import AST.StatementAST.StatementAST;

/**
 * Created by andikoh on 08/11/2016.
 */
public class
AssignmentAST extends StatementAST {
    AssignlhsAST lhs;
    AssignrhsAST rhs;

    public AssignmentAST(AssignlhsAST lhs, AssignrhsAST rhs) {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void check() {
        lhs.check();
        rhs.check();
        lhs.checkType(rhs);
    }
}
