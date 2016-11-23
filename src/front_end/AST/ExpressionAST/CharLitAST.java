package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.instruction.data_manipulation.Mov;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class CharLitAST extends ExpressionAST {
    String charac;

    public CharLitAST(ParserRuleContext ctx, String charac) {
        super(ctx);
        this.charac = charac;
        identObj = Visitor.ST.lookUpAll("char");
    }

    @Override
    public void check() {
        checkIfInScope(charac);
    }

    @Override
    public void translate() {
        Utility.addMain(new Mov(Utility.popUnusedReg(), new ImmValue(charac)));
    }
}
