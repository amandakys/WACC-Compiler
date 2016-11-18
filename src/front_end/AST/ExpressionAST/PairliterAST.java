package front_end.AST.ExpressionAST;

import back_end.instruction.Instruction;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

import java.util.List;

/**
 * Created by andikoh on 11/11/2016.
 */
public class PairliterAST extends ExpressionAST {
    String nullStr;
    public PairliterAST(ParserRuleContext ctx, String text) {
        super(ctx);
        nullStr = text;
//        IDENTIFIER pair = Visitor.ST.lookUpAll("pair");
//        identObj = pair;
    }

    @Override
    public void check() {
        checkIfInScope(nullStr);
        identObj = new PAIR(null, null);
    }

    @Override
    public void translate() {
    }
}
