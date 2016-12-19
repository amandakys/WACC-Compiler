package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.AssignmentAST.AssignrhsAST;
import main.CodeGen;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

public class PairliterAST extends ExpressionAST {
    private String nullStr;

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
            CodeGen.main.add(new MOV(getRegister(), new ImmValue(0)));
        }
    }

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {
        defaultIRep(ident);
    }

    public String getNullStr() {
        return nullStr;
    }
}
