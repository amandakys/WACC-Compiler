package front_end.AST.AssignmentAST;

import back_end.data_type.register.PreIndex;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.load_store.STORE;
import front_end.AST.ExpressionAST.*;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import front_end.AST.StatementAST.StatementAST;
import front_end.symbol_table.FUNCTION;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

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
        Node lhsChild = lhs.getChild();
        rhs.setRegister(lhs.getRegister());

        //translate righthandside
        rhs.translate();

        if(lhsChild != null) { //if lhs is not an Ident
            lhsChild.translate();

            //store the RHS address into the top of the Unused stack
            CodeGen.main.add(new STORE(rhs.getRegister(), new PreIndex(lhsChild.getRegister()), rhs.getIdentObj().getSize()));
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
        lhs.IRepresentation();
        rhs.IRepresentation();
        IGNode = lhs.getIGNode();

    }

    @Override
    public boolean determineLoopInvariance(List<String> idents) {
        if ((rhs instanceof ArraylitAST || rhs instanceof BoolliterAST || rhs instanceof CharLitAST ||
                rhs instanceof IntLiterAST || rhs instanceof PairliterAST || rhs instanceof StringLiterAST ||
                rhs instanceof UnopAST)) {
            if (idents.isEmpty()) {
                return true;
            } else if (lhs.getIdent() != null) { //lhs is an ident
                if (idents.contains(lhs.getIdent())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
