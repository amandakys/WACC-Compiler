package front_end.AST.StatementAST;

import back_end.data_type.Register;
import back_end.instruction.Instruction;
import back_end.instruction.data_manipulation.Mov;
import front_end.AST.ExpressionAST.ExpressionAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
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
    public void translate() {
        expression.translate();

        CodeGen.globalMain.add(new Mov(r0, r4));

    }
}
