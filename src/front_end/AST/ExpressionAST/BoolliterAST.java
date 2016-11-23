package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class BoolliterAST extends ExpressionAST{
    private String boolVal;
    public static int occurences = 0;

    public BoolliterAST(ParserRuleContext ctx, String boolVal) {
        super(ctx);
        this.boolVal = boolVal;
        identObj = Visitor.ST.lookUpAll("bool");
        occurences++;
    }

    @Override
    public void check() {
        checkIfInScope(boolVal);
    }

    @Override
    public void translate() {
        Utility.addMain(new MOV(Utility.popUnusedReg(), new ImmValue(boolVal.equals("true") ? 1 : 0)));
    }

    public String getBoolVal() {
        return boolVal;
    }
}
