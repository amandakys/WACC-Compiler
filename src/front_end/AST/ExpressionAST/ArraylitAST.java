package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.AssignmentAST.AssignrhsAST;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import main.CodeGen;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
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
        ARRAY varType = (ARRAY) identObj.getType();
        int arrSize = arraylits.size();
        //finding total array_size
        int array_size = arrSize*varType.getElementType().getSize() + identObj.getSize();
        CodeGen.main.add(new LOAD(getRegister(), new ImmValue(array_size)));

        CodeGen.main.add(new Branch("L", "malloc"));

        //identObj.getSize() returns size of array
        ProgramAST.nextAddress += identObj.getSize();

        Register res = arraylits.get(0).getRegister();
        //Transverse through the list to translate each expr
        for (ExpressionAST a: arraylits) {
            a.translate();

            Utility.addMain(new STORE(res, new PreIndex(getRegister(),
                    new ImmValue(ProgramAST.nextAddress)), identObj.getSize()));
            ProgramAST.nextAddress += a.getIdentObj().getType().getSize();
        }

        CodeGen.main.add(new LOAD(res, new ImmValue(getArraylits().size())));
        CodeGen.main.add(new STORE(res, new PreIndex(getRegister()), identObj.getSize()));
    }

    @Override
    public void weight() {
        for (ExpressionAST arrayelem : arraylits) {
            arrayelem.weight();
            size += arrayelem.getSize();
        }
    }

    @Override
    public void IRepresentation() {
        //IGNode represents the register that is used to store array's elem's values
        defaultIRep("elem_size");

        for(ExpressionAST e : arraylits) {
            e.setIGNode(IGNode);
        }
    }

    public List<ExpressionAST> getArraylits() {
        return arraylits;
    }
}
