package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.Operand;
import back_end.data_type.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.Mov;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.symbol_table.IDENTIFIER;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

public class ExitAST extends StatementAST{
    private ExpressionAST expression;

    public ExitAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        IDENTIFIER T = Visitor.ST.lookUpAll("int");
        expression.checkNode();
        if(!expression.getType().equals(T.getType())) {
            error("Exit statement must take integer");
        }
    }

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {
        expression.translate(unusedRegs, paramRegs);

        Utility.addMain(new Mov(Utility.popUnusedReg(), Utility.popParamReg()));
        Utility.addMain(new Branch("exit"));
    }
}
