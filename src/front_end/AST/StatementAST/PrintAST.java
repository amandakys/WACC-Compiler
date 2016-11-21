package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.Pop;
import back_end.instruction.Push;
import back_end.instruction.data_manipulation.Add;
import back_end.instruction.data_manipulation.Mov;
import back_end.instruction.load_store.Load;
import front_end.AST.ExpressionAST.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

public class PrintAST extends StatementAST {
    ExpressionAST expression;

    public PrintAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.checkNode();
    }

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {
        expression.translate(unusedRegs, paramRegs);
        Utility.addMain(new Mov(paramRegs.pop(), unusedRegs.pop()));

        String specifier = "";
        String typeName = expression.getType().getTypeName();
        String functionName = "p_print_" + typeName;

        if (expression instanceof StringLiterAST) {
            specifier = "\"%.*s\\0\"";
        } else if(expression instanceof IntLiterAST) {
            specifier = "\"%d\0\"";
        } else if (expression instanceof BoolliterAST) {
            specifier = "\"" + ((BoolliterAST) expression).getBoolVal() + "\"";
        } else if (expression instanceof CharLitAST) {
            functionName = "putchar";
        }

        Utility.addMain(new Branch(functionName));

        if(!specifier.isEmpty()) {
            Utility.pushData(specifier);
        }


        int exprSize = expression.getType().getSize();

        String type = expression.getType().getTypeName();
        Utility.addFunction(new LabelInstr("p_print_" + type));
        Utility.addFunction(new Push(Register.LR));

        if(expression instanceof IntLiterAST || expression instanceof StringLiterAST) {
            Utility.addFunction(new Load(paramRegs.pop(), new Address(Register.R0)));
            Utility.addFunction(new Add(unusedRegs.pop(), Register.R0, new ImmValue(exprSize)));
        }

        Utility.addFunction(new Load(Register.R0, new LabelExpr(Utility.getLastMessage())));
        Utility.addFunction(new Add(Register.R0, Register.R0, new ImmValue(exprSize)));

        Utility.addFunction(new Branch("printf"));
        Utility.addFunction(new Mov(Register.R0, new ImmValue(0)));
        Utility.addFunction(new Branch("fflush"));
        Utility.addFunction(new Pop(Register.PC));
    }
}
