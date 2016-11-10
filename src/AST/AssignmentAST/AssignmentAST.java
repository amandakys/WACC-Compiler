package AST.AssignmentAST;

<<<<<<< HEAD
=======
import AST.Node;
>>>>>>> semanticsV2
import AST.StatementAST.StatementAST;

/**
 * Created by andikoh on 08/11/2016.
 */
<<<<<<< HEAD
public class
AssignmentAST extends StatementAST {
=======
public class AssignmentAST extends StatementAST {
>>>>>>> semanticsV2
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
