package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

/**
 * Created by andikoh on 11/11/2016.
 */
public class PairliterAST extends ExpressionAST {
    String nullStr;
    public PairliterAST(ParserRuleContext ctx, String text) {
        super(ctx);
        nullStr = text;
        identObj = new PAIR(null, null);
    }

    @Override
    public void check() {
        checkIfInScope(nullStr);
    }

    @Override
    public void translate() {
        if(nullStr.equals("null")) {
            CodeGen.main.add(new LOAD(Utility.popUnusedReg(), new ImmValue(0)));
        } else {
            CodeGen.main.add(new LOAD(Register.R0, new ImmValue(identObj.getSize() * 2)));
            CodeGen.main.add(new Branch("L", "malloc"));
            CodeGen.main.add(new MOV(Utility.popUnusedReg(), Register.R0));
        }
    }

    public String getNullStr() {
        return nullStr;
    }
}
