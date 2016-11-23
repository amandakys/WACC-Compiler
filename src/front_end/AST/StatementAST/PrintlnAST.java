package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.LabelInstr;
import back_end.instruction.Branch;
import back_end.instruction.POP;
import back_end.instruction.PUSH;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.ExpressionAST.*;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Utility.*;
import static back_end.data_type.register.Register.*;

public class PrintlnAST extends StatementAST {
    private ExpressionAST expression;

    public PrintlnAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        // identObj =
        expression.checkNode();
    }

    @Override
    public void translate() {
        (new PrintAST(null, expression)).translate();
        addMain(new Branch("L", "p_print_ln"));
        if(!Utility.hasPlaceholder("\"\\0\"")) {
            CodeGen.placeholders.add("\"\\0\"");
        }

//       if(!Utility.hasFunction("p_print_ln")) {
//           addMain(new Branch("L", "p_print_ln"));
//
//           addFunction(new LabelInstr("p_print_ln"));
//           addFunction(new PUSH(LR));
//           addFunction(new LOAD(R0, new LabelExpr(getLastPlaceholder())));
//           addFunction(new ADD(R0, R0, new ImmValue(4)));
//           addFunction(new Branch("L", "puts"));
//           addFunction(new MOV(Register.R0, new ImmValue(0)));
//           addFunction(new Branch("L", "fflush"));
//
//           addFunction(new POP(PC));
//       }
    }

    public ExpressionAST getExpression() {
        return expression;
    }
}
