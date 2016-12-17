package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
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

    //IGNode stores the value of array's element
    //stores the size of the array
    private IGNode arrayElem;
    private IGNode arrayIndex;

    public ArraylitAST(ParserRuleContext ctx, List<ExpressionAST> arraylits) {
        super(ctx);
        this.arraylits = arraylits;

        if (!arraylits.isEmpty()) {
            //check all expressions are of the same type
            Node firstElem = arraylits.get(0);

            for (ExpressionAST a : arraylits) {
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
        for (ExpressionAST a : arraylits) {
            a.checkNode();
        }
    }

    @Override
    public void translate() {
        //Store size in R0 to malloc successfully
        CodeGen.main.add(new MOV(Register.R0,
                new ImmValue(identObj.getSize() +
                        (arraylits.size()) * ((ARRAY) identObj).getElem_size())));
        CodeGen.main.add(new Branch("L", "malloc"));

        CodeGen.main.add(new MOV(getRegister(), Register.R0));
        //identObj.getSize() returns size of array
        ProgramAST.nextAddress += identObj.getSize();
        //Transverse through the list to translate each expr
        for (ExpressionAST a: arraylits) {
            a.translate();

            Utility.addMain(new STORE(arrayElem.getRegister(), new PreIndex(getRegister(),
                    new ImmValue(ProgramAST.nextAddress)), identObj.getSize()));
            ProgramAST.nextAddress += a.getIdentObj().getType().getSize();
        }

        CodeGen.main.add(new LOAD(arrayElem.getRegister(), new ImmValue(arraylits.size())));
        CodeGen.main.add(new STORE(arrayElem.getRegister(),new PreIndex(getRegister()), identObj.getSize()));

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
        String ident = findIdent();

        //IGNode represents the register that is used to store array's elem's values
        arrayElem = new IGNode(ident + "_elem");
        arrayIndex = new IGNode(ident + "_index");

        //IGNode which has the register that stores the array's size
        IGNode = new IGNode(ident + "_array_size");
        IGNode.addEdge(arrayElem);
        IGNode.addEdge(arrayIndex);
        arrayElem.addEdge(arrayIndex);

        linkToString(arrayElem, arrayIndex, arrayIndex);
        InterferenceGraph.add(IGNode);
        InterferenceGraph.add(arrayElem);
        InterferenceGraph.add(arrayIndex);

        for (ExpressionAST e : arraylits) {
            e.setIGNode(arrayElem);
        }
    }
}