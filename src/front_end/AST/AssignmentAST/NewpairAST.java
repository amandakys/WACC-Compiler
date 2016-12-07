package front_end.AST.AssignmentAST;

import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.instruction.Branch;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import main.CodeGen;
import main.Visitor;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;
import java.util.List;

public class NewpairAST extends AssignrhsAST {
    private List<ExpressionAST> pairelems;
    //IGNode represents register which store the size of a pair object
    //elemSize stores the size of each element
    private IGNode elemSize;

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
            elem.translate();

            int sizeElem = elem.getIdentObj().getSize();

            //load the size of each element
            CodeGen.main.add(new LOAD(elemSize.getRegister(), new ImmValue(sizeElem)));
            CodeGen.main.add(new Branch("L", "malloc"));
            //store the value of each element to the memory address specified by the size of the element
            CodeGen.main.add(new STORE(elem.getRegister(), new PreIndex(elemSize.getRegister()),
                    elem.getIdentObj().getSize()));
            //store the size of each element to the memory address specified by the size of a pair & next available address
            CodeGen.main.add(new STORE(elemSize.getRegister(), new PreIndex(getRegister(),
                    new ImmValue(ProgramAST.nextAddress)), identObj.getSize()));

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
        for(ExpressionAST e : pairelems) {
            //add the register that stores the value of each element to the graph
            e.IRepresentation();
            //add the register that stores the size of a pair to the graph
            IGNode = e.getIGNode();

            //add the register that stores the size of each element to the graph
            elemSize = new IGNode(e + "_size");
            Visitor.ST.add(elemSize);

            IGNode.addEdge(elemSize);
            IGNode.addEdge(e.getIGNode());
        }
    }
}
