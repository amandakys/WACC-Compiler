package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class IntLiterAST extends ExpressionAST {

    private String intsign; //intsign
    private String value;

    public static int occurences = 0;

    public IntLiterAST(ParserRuleContext ctx, String intsign, String value) {
        super(ctx);

        //only leaves digit in the string value, e.g "1-2-3" will be stored as "123"
        this.value = value.replaceAll("\\D+","");;
        this.intsign = intsign;
        identObj = Visitor.ST.lookUpAll("int");
        occurences++;
    }

    @Override
    public void check() {
        checkIfInScope(value);
    }

    @Override
    public void translate() {
        int val = Integer.parseInt(intsign + value);
        CodeGen.main.add(new LOAD(Utility.popUnusedReg(), new ImmValue(val)));
    }

    public int getValue() {
        // TODO: Negative
        return Integer.parseInt(intsign + value);
    }
}
