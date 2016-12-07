package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.instruction.data_manipulation.MOV;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class BoolliterAST extends ExpressionAST{
    private String boolVal;

    public BoolliterAST(ParserRuleContext ctx, String boolVal) {
        super(ctx);
        this.boolVal = boolVal;
        identObj = Visitor.ST.lookUpAll("bool");
    }

    @Override
    public void check() {
        checkIfInScope(boolVal);
    }

    @Override
    public void translate() {
        Utility.addMain(new MOV(getRegister(), new ImmValue(boolVal.equals("true") ? 1 : 0)));
    }

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {

    }

    public String getBoolVal() {
        return boolVal;
    }
}
