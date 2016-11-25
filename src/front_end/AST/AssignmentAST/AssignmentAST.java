package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.ExpressionAST.ArraylitAST;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import front_end.AST.StatementAST.StatementAST;
import front_end.symbol_table.FUNCTION;
import main.CodeGen;
import main.Visitor;
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

        if (!(rhs instanceof CallAST) ||
                (rhs instanceof CallAST && ((CallAST) rhs).isDeclared())) {
            lhs.checkType(rhs);
        }
    }

    @Override
    public void translate() {

        //get the top of the unsedRegisters stack
        Register result = CodeGen.notUsedRegisters.peek();
        Node lhsChild = lhs.getChild();

        //translate righthandside
        rhs.translate();

        if(lhsChild != null) { //if lhs is not an Ident
            Register res = CodeGen.notUsedRegisters.peek();
            lhsChild.translate();

            //store the RHS address into the top of the Unused stack
            CodeGen.main.add(new STORE(result, new Address(res), rhs.getIdentObj().getSize()));
        } else { //lhs is an Ident
            //Store the RHS into the adress of the ident on the memory address
            ShiftedReg res = Visitor.ST.getAddress(lhs.getIdent());
            int typeSize;
            if (rhs.getIdentObj() instanceof FUNCTION) {
                typeSize = ((FUNCTION) rhs.getIdentObj()).getReturntype().getSize();
            } else {
                typeSize = rhs.getIdentObj().getSize();
            }
            CodeGen.main.add(new STORE(result, res, typeSize));
        }

        ProgramAST.nextAddress += rhs.getIdentObj().getSize();
    }
}
