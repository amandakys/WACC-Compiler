package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.Operand;
import back_end.data_type.Register;
import back_end.instruction.data_manipulation.Mov;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

public class BoolliterAST extends ExpressionAST{
    String boolVal;

    public BoolliterAST(ParserRuleContext ctx, String boolVal) {
        super(ctx);
        this.boolVal = boolVal;
        identObj = Visitor.ST.lookUpAll("bool");
    }

    @Override
    public void check() {
        checkIfInScope(boolVal);
    }

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {
        Utility.addMain(new Mov(Utility.popUnusedReg(), new ImmValue(boolVal.equals("true") ? 1 : 0)));
    }

    public String getBoolVal() {
        return boolVal;
    }
}
