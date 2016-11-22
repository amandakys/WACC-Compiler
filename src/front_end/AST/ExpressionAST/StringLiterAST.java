package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class StringLiterAST extends ExpressionAST{
    private static final int NUM_DOUBLE_QUOTE = 2;

    //TODO: Check if occurrences can be deleted
    public static int occurences = 0;
    private String value;

    public StringLiterAST(ParserRuleContext ctx, String value) {
        super(ctx);
        this.value = value;
        this.identObj = Visitor.ST.lookUpAll("string");
        occurences++;
    }

    @Override
    public void check() {
        checkIfInScope(value);
    }

    @Override
    public void translate() {
        String label = "msg_" + Utility.getLastMessage();

        Utility.pushData(value);
        Utility.addMain(new LOAD(Utility.popUnusedReg(), new LabelExpr(label)));
    }

    public String getValue() {
        return value;
    }
}
