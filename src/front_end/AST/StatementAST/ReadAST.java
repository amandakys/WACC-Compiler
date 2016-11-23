package front_end.AST.StatementAST;

import back_end.data_type.ImmValue;
import back_end.data_type.LabelExpr;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.POP;
import back_end.instruction.PUSH;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.AssignmentAST.AssignlhsAST;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.symbol_table.TYPE;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

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
    public void translate() {
        Register r = CodeGen.notUsedRegisters.peek();
        addMain(new ADD(r, Register.SP, new ImmValue(0))); // why 0 ??

        addMain(new MOV(Register.R0, r));

        String functionName = "p_read_" + expression.getType().getTypeName();
        addMain(new Branch("L", functionName));

        String placeholder = "";
        if (expression.getType().getTypeName().equals("char")) {
            placeholder = "\"%c\\0\"";
        } else if (expression.getType().getTypeName().equals("int")) {
            placeholder = "\"%d\\0\"";
        }
        CodeGen.placeholders.add(placeholder);
        if (!CodeGen.endFunctions.contains("read")) {
            CodeGen.endFunctions.add("read");
        }

        addFunction(new LabelInstr(functionName));
        addFunction(new PUSH(Register.LR));
        addFunction(new MOV(popParamReg(), Register.R0));
        addFunction(new LOAD(Register.R0, new LabelExpr(getLastString())));
        
        addFunction(new ADD(Register.R0, Register.R0, new ImmValue(4)));
        addFunction(new Branch("L", "scanf"));
        addFunction(new POP(Register.PC));
    }
}
