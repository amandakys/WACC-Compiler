package front_end.AST.ExpressionAST;

import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Error.overflow;

public class IntLiterAST extends ExpressionAST {
    private String intsign; //intsign, can be null(for positive), "+" or "-"
    private String value;

    public IntLiterAST(ParserRuleContext ctx, String intsign, String value) {
        super(ctx);

        //only leaves digit in the string value, e.g "1-2-3" will be stored as "123"
        this.value = value.replaceAll("\\D+","");;
        this.intsign = intsign;
        this.ident = "int_" + this.intsign + this.value;
        identObj = Visitor.ST.lookUpAll("int");
    }

    @Override
    public void check() {
        checkIfInScope(value);
    }

    @Override
    public void translate() {
        int val = Integer.parseInt(intsign + value);
        CodeGen.main.add(new LOAD(getRegister(), new ImmValue(val)));
    }

    @Override
    public void weight() {
        size = 1;
    }

    public int getValue() {
        return Integer.parseInt(intsign + value);
    }
}
