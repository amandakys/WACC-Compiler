package front_end.AST.AssignmentAST;

import back_end.data_type.Address;
import back_end.data_type.register.Register;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.load_store.STORE;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import front_end.AST.StatementAST.StatementAST;
import front_end.symbol_table.FUNCTION;
import main.CodeGen;
import main.Visitor;
import optimisation.IGNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class AssignmentAST extends StatementAST {
    private AssignlhsAST lhs;
    private AssignrhsAST rhs;

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
        Node lhsChild = lhs.getChild();

        //translate righthandside
        rhs.translate();

        if(lhsChild != null) { //if lhs is not an Ident
            lhsChild.translate();

            //store the RHS address into the top of the Unused stack
            CodeGen.main.add(new STORE(rhs.getRegister(), new Address(lhsChild.getRegister()), rhs.getIdentObj().getSize()));
        } else {//lhs is an Ident

            //Store the RHS into the adress of the ident on the memory address
            ShiftedReg res = Visitor.ST.getAddress(lhs.getIdent());
            int typeSize;

            if (rhs.getIdentObj() instanceof FUNCTION) {
                typeSize = ((FUNCTION) rhs.getIdentObj()).getReturntype().getSize();
            } else {
                typeSize = rhs.getIdentObj().getSize();
            }
            CodeGen.main.add(new STORE(rhs.getRegister(), res, typeSize));
        }

        ProgramAST.nextAddress += rhs.getIdentObj().getSize();
    }

    @Override
    public void weight() {
        lhs.weight();
        rhs.weight();
        size = lhs.getSize() + rhs.getSize();
    }

    @Override
    public void IRepresentation() {
        StatementIRepresentation("assignment");
        lhs.IRepresentation();
        rhs.setIGNode(IGNode);
    }
}
