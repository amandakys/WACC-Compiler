package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.POP;
import back_end.instruction.PUSH;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ExpressionAST.IntLiterAST;
import front_end.AST.ExpressionAST.PairliterAST;
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

        for (ExpressionAST elem: pairelems) {
            //store elem's value to sp - 4 and decrement sp by 4
            if(!(elem instanceof IntLiterAST)) {
                CodeGen.main.add(new PUSH(elem.getRegister()));
            }
            elem.translate();

            int sizeElem = elem.getIdentObj().getSize();

            //store elem's value to sp - 4 and decrement sp by 4
            CodeGen.main.add(new PUSH(elem.getRegister()));
            //load the size of each element
            CodeGen.main.add(new MOV(elem.getRegister(), new ImmValue(sizeElem)));
            CodeGen.main.add(new Branch("L", "malloc"));
            //load pair's size from sp and increment sp by 4
            CodeGen.main.add(new POP(pairSize.getRegister()));
            //store the size of each element to the memory address specified by the size of a pair & next available address
            CodeGen.main.add(new STORE(pairSize.getRegister(), new PreIndex(elem.getRegister()),
                    elem.getIdentObj().getSize()));

            ProgramAST.nextAddress += identObj.getSize();
        }

        //store pair's size to sp - 4 and decrement sp by 4
        CodeGen.main.add(new PUSH(getRegister()));
        CodeGen.main.add(new LOAD(getRegister(), new ImmValue(identObj.getSize() * 2)));
        CodeGen.main.add(new Branch("L", "malloc"));
        //load elemSize's and pair's elem's value from sp and increment sp by 4, 8
        CodeGen.main.add(new POP(pairSize.getRegister(), elemSize.getRegister()));

        CodeGen.main.add(new STORE(elemSize.getRegister(), new PreIndex(getRegister(),
                new ImmValue(0)), identObj.getSize()));
        CodeGen.main.add(new STORE(pairSize.getRegister(), new PreIndex(getRegister(),
                new ImmValue(4)), identObj.getSize()));
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
        //add the register that stores the value of each element to the graph
        defaultIRep("elem_value");

        //add the register that stores the size of a pair to the graph
        pairSize = new IGNode("pair_size");
        InterferenceGraph.add(pairSize);

        for(ExpressionAST e : pairelems) {
            e.IRepresentation();
        }

        //add the register that stores the size of pair's elemSize
        elemSize = new IGNode("elem_size");
        InterferenceGraph.add(elemSize);

        //indicate that these register must be alive at the same time
        IGNode.addEdge(elemSize);
        IGNode.addEdge(pairSize);
        pairSize.addEdge(elemSize);
    }
}
