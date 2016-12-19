package front_end.AST.AssignmentAST;

import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import main.CodeGen;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

import java.util.List;

public class NewpairAST extends AssignrhsAST {
    private List<ExpressionAST> pairelems;
    //IGNode represents element's value which store the size of a pair object
    //elemSize stores pair's individual elemSize's size
    private IGNode elemSize;
    private IGNode pairSize;

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

        CodeGen.main.add(new LOAD(Register.R0, new ImmValue(identObj.getSize() * 2)));
        CodeGen.main.add(new Branch("L", "malloc"));
        CodeGen.main.add(new MOV(getRegister(), Register.R0));

        for (ExpressionAST elem: pairelems) {
            Register res = elem.getRegister();

            elem.translate();

            int sizeElem = elem.getIdentObj().getSize();

            CodeGen.main.add(new LOAD(Register.R0, new ImmValue(sizeElem)));
            CodeGen.main.add(new Branch("L", "malloc"));
            CodeGen.main.add(new STORE(res, new PreIndex(Register.R0), elem.getIdentObj().getSize()));
            CodeGen.main.add(new STORE(Register.R0, new PreIndex(getRegister(), new ImmValue(ProgramAST.nextAddress)),
                    identObj.getSize()));

            ProgramAST.nextAddress += identObj.getSize();
        }
    }

    @Override
    public void weight() {
        for (ExpressionAST e : pairelems) {
            e.weight();
            size += e.getSize();
        }
    }

    @Override
    public void IRepresentation() {
        //add the register that stores the size of a pair to the graph
        IGNode = InterferenceGraph.findIGNode(ident);

        //add the register that stores the value of each element to the graph
        pairSize = InterferenceGraph.findIGNode(ident + "_pair_size");

        for(ExpressionAST e : pairelems) {
            e.setIGNode(pairSize);
        }

        //add the register that stores the size of pair's elemSize
        elemSize = InterferenceGraph.findIGNode(ident + "_elem_size");

        //indicate that these register must be alive at the same time
        linkToString();
        elemSize.addEdge(pairSize);
        IGNode.addEdge(elemSize);
        IGNode.addEdge(pairSize);
    }
}
