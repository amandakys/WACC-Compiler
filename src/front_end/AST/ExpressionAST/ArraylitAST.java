package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.load_store.STORE;
import front_end.AST.AssignmentAST.AssignrhsAST;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;

import java.util.List;

public class ArraylitAST extends AssignrhsAST {
    //Array list has stores a list of expression
    private List<ExpressionAST> arraylits;

    public ArraylitAST(ParserRuleContext ctx, List<ExpressionAST> arraylits) {
        super(ctx);
        this.arraylits = arraylits;

        if(!arraylits.isEmpty()) {
            //check all expressions are of the same type
            Node firstElem = arraylits.get(0);

            for (ExpressionAST a: arraylits) {
                firstElem.checkType(a);
            }

            //initialies IDENTOBJ to array type
            identObj = new ARRAY(firstElem.getType(), arraylits.size());
        } else {
            identObj = new ARRAY(null, arraylits.size());
        }
    }


    @Override
    public void check() {
        for(ExpressionAST a : arraylits) {
            a.checkNode();
        }
    }

    @Override
    public void translate() {
        ProgramAST.nextAddress += identObj.getSize();
        for (ExpressionAST a: arraylits) {
            Register res = CodeGen.notUsedRegisters.peek();

            a.translate();

            Utility.addMain(new STORE(res, new PreIndex(CodeGen.toPushUnusedReg.peek(),
                    new ImmValue(ProgramAST.nextAddress)), identObj.getSize()));
            ProgramAST.nextAddress += a.getIdentObj().getType().getSize();
        }
    }


    public List<ExpressionAST> getArraylits() {
        return arraylits;
    }
}
