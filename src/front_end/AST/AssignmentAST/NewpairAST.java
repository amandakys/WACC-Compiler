package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ExpressionAST.PairliterAST;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class NewpairAST extends AssignrhsAST {
    private List<ExpressionAST> pairelems;

    public NewpairAST(ParserRuleContext ctx, List<ExpressionAST> pairelems) {
        super(ctx);
        this.pairelems = pairelems;

        identObj = new PAIR(pairelems.get(0).getType(), pairelems.get(1).getType());
    }

    @Override
    public void check() {
        for(Node p : pairelems) {
            p.checkNode();
        }
    }

    @Override
    public void translate() {
        ProgramAST.nextAddress = 0;

        for (ExpressionAST elem: pairelems) {
            Register res = CodeGen.notUsedRegisters.peek();

            elem.translate();

//            if(elem instanceof PairliterAST && ((PairliterAST) elem).getNullStr().equals("null")) {
//                CodeGen.main.add(new LOAD(Utility.popUnusedReg(), new ImmValue(0)));
//            }

            int sizeElem = elem.getIdentObj().getSize();

            CodeGen.main.add(new LOAD(Register.R0, new ImmValue(sizeElem)));
            CodeGen.main.add(new Branch("L", "malloc"));
            CodeGen.main.add(new STORE(res, new PreIndex(Register.R0), elem.getIdentObj().getSize()));
            CodeGen.main.add(new STORE(Register.R0, new PreIndex(Utility.getBefore(res),
                    new ImmValue(ProgramAST.nextAddress)), identObj.getSize()));

            ProgramAST.nextAddress += identObj.getSize();
        }
    }
}
