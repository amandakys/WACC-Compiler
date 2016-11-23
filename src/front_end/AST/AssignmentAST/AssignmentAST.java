package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
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

    private static int byte_size = 0;

    public AssignmentAST(ParserRuleContext ctx, AssignlhsAST lhs, AssignrhsAST rhs) {
        super(ctx);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void check() {
        lhs.checkNode();
        rhs.checkNode();

        if (!(rhs instanceof CallAST) ||
                (rhs instanceof CallAST && ((CallAST) rhs).isDeclared())) {
            lhs.checkType(rhs);
        }
    }

    @Override
    public void translate() {
        if(rhs instanceof ArraylitAST) {
            int arrSize = ((ArraylitAST) rhs).getArraylits().size();
            int ARRAY_SIZE = 4;
            byte_size = (arrSize + 1) * ARRAY_SIZE;

            CodeGen.main.add(new LOAD(Register.R0, new ImmValue(byte_size)));
            CodeGen.main.add(new Branch("L", "malloc"));
            CodeGen.main.add(new MOV(Utility.popUnusedReg(), Register.R0));
        } else {
            byte_size = ProgramAST.size;
        }
        ShiftedReg res = CodeGen.memoryAddress.get(lhs.getIdent());

        Register rhsResult = CodeGen.notUsedRegisters.peek();
        rhs.translate();

        if (rhs instanceof ArraylitAST) {
            Register value = Utility.popUnusedReg();

            CodeGen.main.add(new LOAD(value, new ImmValue(((ArraylitAST) rhs).getArraylits().size())));
            CodeGen.main.add(new STORE(value, new PreIndex(rhsResult), rhs.getIdentObj().getSize()));
        }

        ProgramAST.nextAddress += rhs.getIdentObj().getSize();
        CodeGen.main.add(new STORE(rhsResult, res, rhs.getIdentObj().getSize()));
    }
}
