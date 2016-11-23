package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.load_store.Store;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ProgramAST;
import front_end.AST.StatementAST.StatementAST;
import main.CodeGen;
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
        lhs.checkNode();
        rhs.checkNode();

        if(!(rhs instanceof CallAST) ||
                (rhs instanceof CallAST && ((CallAST) rhs).isDeclared())) {
            lhs.checkType(rhs);
        }
    }

    @Override
    public void translate() {
        Register res = CodeGen.notUsedRegisters.peek();
        lhs.translate();
        rhs.translate();
        Utility.addMain(new Store(res, new PreIndex(Register.SP,
                new ImmValue(ProgramAST.nextAddress))));
    }
}
