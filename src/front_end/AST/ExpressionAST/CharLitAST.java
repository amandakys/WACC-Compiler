package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.instruction.data_manipulation.MOV;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class CharLitAST extends ExpressionAST {
    String charac;

    public CharLitAST(ParserRuleContext ctx, String charac) {
        super(ctx);
        this.charac = charac;
        this.ident = "char_" + charac;
        identObj = Visitor.ST.lookUpAll("char");
    }

    @Override
    public void check() {
        checkIfInScope(charac);
    }

    @Override
    public void translate() {
        if (!charac.equals("'\\0'")) {
            Utility.addMain(new MOV(getRegister(), new ImmValue(charac)));
        } else {
            Utility.addMain(new MOV(getRegister(), new ImmValue(0)));
        }
    }

    @Override
    public void weight() {
        size = 1;
    }

    public int getCodePoint() {
        return charac.replace("\'", "").codePointAt(0);
    }
}
