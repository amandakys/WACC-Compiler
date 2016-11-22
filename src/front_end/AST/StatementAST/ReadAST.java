package front_end.AST.StatementAST;

import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.LabelExpr;
import back_end.data_type.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.Pop;
import back_end.instruction.Push;
import back_end.instruction.data_manipulation.Add;
import back_end.instruction.data_manipulation.Mov;
import back_end.instruction.load_store.Load;
import front_end.AST.AssignmentAST.AssignlhsAST;
import front_end.AST.ExpressionAST.IntLiterAST;
import front_end.AST.ExpressionAST.StringLiterAST;
import front_end.symbol_table.TYPE;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.rmi.CORBA.Util;
import java.util.Stack;

import static back_end.Utility.*;

public class ReadAST extends StatementAST {
    private AssignlhsAST expression;
    public ReadAST(ParserRuleContext ctx, AssignlhsAST expression) {
        super(ctx);
        this.expression = expression;
    }
    @Override
    public void check() {
        expression.check();
        TYPE t = expression.getType(); //check that expresison is of a type acceptable to read

        TYPE intType = Visitor.ST.lookUpAll("int").getType();

        TYPE charType = Visitor.ST.lookUpAll("char").getType();

        if (!t.equals(intType) && !t.equals(charType)){
            //error
            error("read only takes int or char types");
        }

    }

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {
        Register r = unusedRegs.peek();
        int typeSize = expression.getType().getSize();
        addMain(new Add(r, Register.SP, new ImmValue(0))); // why 0 ??

        addMain(new Mov(Register.R0, r));

        String functionName = "p_read_" + expression.getType().getTypeName();
        addMain(new Branch(functionName));

//        addMain(new Add(Register.SP, Register.SP, new ImmValue(typeSize)));

        String placeholder = "";
        if (expression.getType().getTypeName().equals("char")) {
            placeholder = "\" %c\\0\"";
        } else if(expression.getType().getTypeName().equals("int")) {
            placeholder = "\"%d\\0\"";
        }
        pushData(placeholder);

        addFunction(new LabelInstr(functionName));
        addFunction(new Push(Register.LR));
        addFunction(new Mov(popParamReg(), Register.R0));
        addFunction(new Load(Register.R0, new LabelExpr(getLastMessage())));


        addFunction(new Add(Register.R0, Register.R0, new ImmValue(typeSize)));
        addFunction(new Branch("scanf"));
        addFunction(new Pop(Register.PC));
    }

}
