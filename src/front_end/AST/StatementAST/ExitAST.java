package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.symbol_table.IDENTIFIER;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class ExitAST extends StatementAST{
    private ExpressionAST expression;

    public ExitAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        //expression can only be an int
        IDENTIFIER T = Visitor.ST.lookUpAll("int");

        expression.checkNode();
        if(!expression.getType().equals(T.getType())) {
            error("Exit statement must take integer");
        }
    }

    @Override
    public void translate() {
        expression.translate();

        //exit function in ARM takes the value store in R0 as exit code
        Utility.addMain(new MOV(Register.R0, expression.getRegister()));
        Utility.addMain(new Branch("L", "exit"));
    }

    @Override
    public void weight() {
        expression.weight();
        size = expression.getSize();
    }

    @Override
    public void IRepresentation() {
        IGNode = expression.getIGNode();
    }
}
