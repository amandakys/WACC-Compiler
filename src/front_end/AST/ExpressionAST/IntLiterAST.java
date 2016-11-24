package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.load_store.LOAD;
import front_end.AST.StatementAST.PrintAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class IntLiterAST extends ExpressionAST {

    private String intsign; //intsign
    private String value;
    private final String OVERFLOW_ERROR_MESSAGE = "\"OverflowError: the result is too small/large to store in a " +
            "4-byte signed-integer.\n\"";

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
        int val;

        try {
            val = Integer.parseInt(value);
            CodeGen.main.add(new LOAD(Utility.popUnusedReg(), new ImmValue(val)));
        } catch (NumberFormatException e) {
            Utility.pushData(OVERFLOW_ERROR_MESSAGE);

            Register second = CodeGen.toPushUnusedReg.get(0);
            Register first = CodeGen.toPushUnusedReg.get(1);

            //CodeGen.main.add(new CMP(second, new PostIndex(first, ASR, new ImmValue(31))));

            CodeGen.endFunctions.add("p_print_string");
        }
    }

    public int getValue() {
        // TODO: Negative
        return Integer.parseInt(value);
    }
}
