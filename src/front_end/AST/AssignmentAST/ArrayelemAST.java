package front_end.AST.AssignmentAST;

import antlr.BasicParser;
import back_end.Error;
import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.*;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import front_end.AST.ExpressionAST.ExpressionAST;

import back_end.instruction.load_store.LOAD;
import front_end.AST.ExpressionAST.IdentAST;
import front_end.AST.ExpressionAST.IntLiterAST;
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

public class ArrayelemAST extends ExpressionAST {
    private String arrayIdent;
    private List<Node> expressions = new ArrayList<>();

    private static boolean hasError;
    private final int ARRAY_SIZE = 4;

    public ArrayelemAST(ParserRuleContext ctx, String ident, List<Node> expressionNodes) {
        super(ctx);
        this.arrayIdent = ident;
        this.expressions = expressionNodes;
        hasError = false;
    }

    @Override
    public void check() {
        //check idents
        IDENTIFIER N = Visitor.ST.lookUpAll(arrayIdent);
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

        //find the the variable's address from its identifier from the current symbol table
        CodeGen.main.add(new ADD(first, Register.SP, Visitor.ST.getAddress(arrayIdent).getShiftVal()));

        for(Node n : expressions) {
            Register reg = n.getRegister();

            //load the first value of an array to a register
            n.translate();

            //if two errors have not been printed yet
            if(!hasError) {
                Utility.pushData(Error.arrayOutOfBoundsNegative);
                Utility.pushData(Error.arrayOutOfBoundsLarge);

                //add the print function to endFunctions to make sure it will be defined
                PrintUtility.addToEndFunctions("p_check_array_bounds", getRegister());
                //throw runtime error
                PrintUtility.throwRuntimeError();
                hasError = true;
            }

            CodeGen.main.add(new LOAD(first, new PreIndex(first)));
            CodeGen.main.add(new MOV(Register.R0, reg));
            CodeGen.main.add(new MOV(Register.R1, first));
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
    }

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {
        IGNode = findIGNode(arrayIdent + findPosition());
        if(IGNode.getFrom() == 0 || IGNode.getFrom() > index) {
            IGNode.setFrom(index);
        }

        //array_pos is linked to node array_elem in Interference Graph. It's destroyed after we exit array_elem
        IGNode array_pos = findIGNode("array_pos" + findPosition());
        array_pos.addEdge(IGNode);

        for(Node n : expressions) {
            n.setIGNode(array_pos);
        }

        //print error message may caused by array out of bound exception
        reserveRegForPrintStr();

        //set the range of IGNode
        if(IGNode.getTo() < index) {
            IGNode.setTo(index);
        }

        IGNode parent = findIGNode(ident);
        if(parent.getTo() < index) {
            parent.setTo(index);
        }
    }

    private String findPosition() {
        String result = "[";
        for(Node e : expressions) {
            String value = (e instanceof IdentAST) ? ((IdentAST) e).getIdent()
                    : String.valueOf(((IntLiterAST) e).getValue());
            result += value + "]";
        }

        return result;
    }
}
