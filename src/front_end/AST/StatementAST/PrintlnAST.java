package front_end.AST.StatementAST;

import back_end.PrintUtility;
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
        expression.checkNode();
    }

    @Override
    public void translate() {
        (new PrintAST(null, expression)).translate();
        addMain(new Branch("L", "p_print_ln"));
            PrintUtility.addToPlaceholders("\"\\0\"");
            PrintUtility.addToEndFunctions("p_print_ln");
    }

    public ExpressionAST getExpression() {
        return expression;
    }
}
