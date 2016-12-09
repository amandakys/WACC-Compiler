package front_end.AST.AssignmentAST;

import antlr.BasicParser;
import back_end.Error;
import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.*;
import back_end.instruction.Branch;
import back_end.instruction.POP;
import back_end.instruction.PUSH;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import front_end.AST.ExpressionAST.ExpressionAST;

import back_end.instruction.load_store.LOAD;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import main.CodeGen;
import main.Visitor;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;

import java.util.ArrayList;
import java.util.List;

import static back_end.Utility.addMain;

public class ArrayelemAST extends ExpressionAST {
    private String ident;
    private List<Node> expressions = new ArrayList<>();

    private static boolean hasError;
    private final int ARRAY_SIZE = 4;

    public ArrayelemAST(ParserRuleContext ctx, String ident, List<Node> expressionNodes) {
        super(ctx);
        this.ident = ident;
        this.expressions = expressionNodes;
        hasError = false;
    }

    @Override
    public void check() {
        //check idents
        IDENTIFIER N = Visitor.ST.lookUpAll(ident);
        if (N == null) {
            error("undeclared variable");
        } else {

            for (Node n : expressions) {
                n.checkNode();
                TYPE T = Visitor.ST.lookUpAll("int").getType();
                if (!T.equals(n.getType())) {
                    error("arrayelement only takes integers, actual: " + T.getTypeName());
                }
                identObj =((ARRAY) N.getType()).getElementType();
            }
        }
    }

    @Override
    public void translate() {
        // take the first unused register in the stack
        Register first = getRegister();
        //get the register that stores the value of n - all values will be stored in the same register
        Register reg = expressions.get(0).getRegister();

        CodeGen.main.add(new LOAD(reg, Visitor.ST.getAddress(ident)));
        //store value at sp - 4 to register in arrayelem and decrement sp by 4
        CodeGen.main.add(new PUSH(first));
        CodeGen.main.add(new MOV(first, reg));

        for(Node n : expressions) {
            //load the first value of an array to a register
            n.translate();

            //if two errors have not been printed yet
            if(!hasError) {
                Utility.pushData(Error.arrayOutOfBoundsNegative);
                Utility.pushData(Error.arrayOutOfBoundsLarge);

                //add the print function to endFunctions to make sure it will be defined
                PrintUtility.addToEndFunctions("p_check_array_bounds", first);
                //throw runtime error
                PrintUtility.throwRuntimeError();
                hasError = true;
            }

            CodeGen.main.add(new Branch("L", "p_check_array_bounds"));

            //incrementing the next address with the object's size
            ProgramAST.nextAddress += identObj.getSize();

            CodeGen.main.add(new ADD(first, first, new ImmValue(ARRAY_SIZE)));

            //shift the register if it is not a char
            if(!identObj.getType().getTypeName().equals("char")) {
                ShiftedReg size = new PostIndex(first, reg, Shift.LSL, new ImmValue(2));
                CodeGen.main.add(new ADD(first, size));
            } else {
                CodeGen.main.add(new ADD(first, first, reg));
            }
        }

        //when array elem is on the rhs of print, read,...
        if(ctx.getParent() instanceof BasicParser.ExprNoBinOpContext) {
            CodeGen.main.add(new LOAD(first, new PreIndex(first)));
        }

        CodeGen.main.add(new MOV(reg, first));
        CodeGen.main.add(new POP(first));
    }

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {
        IGNode = new IGNode(ident + "_value");

        for(Node e : expressions) {
            e.setIGNode(InterferenceGraph.findIGNode(ident));
        }

        newIGNode("p_check_array_bounds");
        //print string is needed to throw runtime error
        if (InterferenceGraph.findIGNode("print_string_mov") == null) {
            IGNode string_mov = new IGNode("print_string_mov");
            IGNode string_load = new IGNode("print_string_ldr");

            //find array_elem
            IGNode arrayElem = InterferenceGraph.findIGNode(ident + "_elem");
            //find array_size
            IGNode arraySize = InterferenceGraph.findIGNode(ident + "_array_size");
            //remove array_size so that print_string will have appropriate registers to call printf
            InterferenceGraph.getNodes().remove(arraySize);
            string_mov.addEdge(string_load);

            arrayElem.addEdge(string_load);
            arrayElem.addEdge(string_mov);
            arrayElem.addEdge(IGNode);

            IGNode.addEdge(string_load);
            IGNode.addEdge(string_mov);
            IGNode.addEdge(arraySize);

            InterferenceGraph.add(string_load);
            InterferenceGraph.add(string_mov);

            //add arraySize back
            InterferenceGraph.add(arraySize);
            arraySize.addEdge(string_load);
            arraySize.addEdge(string_mov);
        }

        InterferenceGraph.add(IGNode);
    }
}
