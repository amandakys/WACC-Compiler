package front_end.AST.StatementAST;

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

import static back_end.Utility.*;

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
        Register result = unusedRegs.peek();

        expression.translate(unusedRegs, paramRegs);
        addMain(new Mov(Register.R0, result));

        String placeholder = "";
        String typeName = expression.getType().getTypeName();
        String functionName = "p_print_" + typeName;

        if (expression instanceof StringLiterAST) {
            placeholder = "\"%.*s\\0\"";
        } else if(expression instanceof IntLiterAST) {
            placeholder = "\"%d\0\"";
        } else if (expression instanceof BoolliterAST) {
            placeholder = "\"" + ((BoolliterAST) expression).getBoolVal() + "\"";
        } else if (expression instanceof CharLitAST) {
            functionName = "putchar";
        }

        addMain(new Branch(functionName));

        if(!placeholder.isEmpty()) {
            pushData(placeholder);
        }


        int exprSize = expression.getType().getSize();

        String type = expression.getType().getTypeName();
        if(!(expression instanceof CharLitAST)) {
            addFunction(new LabelInstr("p_print_" + type));
            addFunction(new Push(Register.LR));

            if(expression instanceof StringLiterAST) {
                addFunction(new Load(popParamReg(), new Address(Register.R0)));
                addFunction(new Add(popParamReg(), Register.R0, new ImmValue(exprSize)));
            } else if(expression instanceof IntLiterAST) {
                addFunction(new Mov(popParamReg(), Register.R0));
            }

            addFunction(new Load(Register.R0, new LabelExpr(getLastMessage())));
            addFunction(new Add(Register.R0, Register.R0, new ImmValue(exprSize)));

            addFunction(new Branch("printf"));
            addFunction(new Mov(Register.R0, new ImmValue(0)));
            addFunction(new Branch("fflush"));
            addFunction(new Pop(Register.PC));
        }
    }
}
