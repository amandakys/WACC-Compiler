package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

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
    }

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {
        defaultIRep(nullStr);
    }

    public String getNullStr() {
        return nullStr;
    }
}
