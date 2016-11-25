package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class StringLiterAST extends ExpressionAST{
    private String value;

    public StringLiterAST(ParserRuleContext ctx, String value) {
        super(ctx);
        this.value = value;
        identObj = Visitor.ST.lookUpAll("string");
    }

    @Override
    public void check() {
        checkIfInScope(value);
    }

    @Override
    public void translate() {
        String label = "msg_" + (CodeGen.data.size() - 1)/3;

        Utility.pushData(value);
        Utility.addMain(new LOAD(Utility.popUnusedReg(), new LabelExpr(label)));
    }

    public String getValue() {
        return value;
    }
}
