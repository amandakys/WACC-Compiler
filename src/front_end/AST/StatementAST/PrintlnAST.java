package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.LabelInstr;
import back_end.instruction.Branch;
import back_end.instruction.Pop;
import back_end.instruction.Push;
import back_end.instruction.data_manipulation.Add;
import back_end.instruction.data_manipulation.Mov;
import back_end.instruction.load_store.Load;
import front_end.AST.ExpressionAST.*;
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

        if(!Utility.hasPlaceholder("\\0")) {
            Utility.pushToPushDatat("\\0");
        }

       if(!Utility.hasFunction("p_print_ln")) {
           addMain(new Branch("L", "p_print_ln"));

           addFunction(new LabelInstr("p_print_ln"));
           addFunction(new Push(LR));
           addFunction(new Load(R0, new LabelExpr(getLastMessage())));
           addFunction(new Add(R0, R0, new ImmValue(4)));
           addFunction(new Branch("L", "puts"));
           addFunction(new Mov(Register.R0, new ImmValue(0)));
           addFunction(new Branch("L", "fflush"));

           addFunction(new Pop(PC));
       }
    }

    public ExpressionAST getExpression() {
        return expression;
    }
}
