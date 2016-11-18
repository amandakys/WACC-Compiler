package front_end.AST.StatementAST;

import back_end.instruction.Instruction;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

/**
 * Created by andikoh on 10/11/2016.
 */
public class BeginAST extends StatementAST {
    StatementAST statement;

    public BeginAST(ParserRuleContext ctx, StatementAST statement) {
        super(ctx);
        this.statement = statement;
    }
    @Override
    public void check() {
        statement.checkNode();
    }

    @Override
    public void translate() {

    }
}
