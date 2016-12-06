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
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import main.CodeGen;
import main.Visitor;
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
        Register first = Utility.popUnusedReg();

        //take the first param register in the stack
        Register paramReg = Utility.popParamReg();

        //find the the variable's address from its identifier from the current symbol table
        CodeGen.main.add(new ADD(first, Register.SP, Visitor.ST.getAddress(ident).getShiftVal()));

        for(Node n : expressions) {
            Register reg = CodeGen.notUsedRegisters.peek();

            //load the first value of an array to a register
            n.translate();

            //if two errors have not been printed yet
            if(!hasError) {
                Utility.pushData(Error.arrayOutOfBoundsNegative);
                Utility.pushData(Error.arrayOutOfBoundsLarge);

                //add the print function to endFunctions to make sure it will be defined
                PrintUtility.addToEndFunctions("p_check_array_bounds");
                //throw runtime error
                PrintUtility.throwRuntimeError();
                hasError = true;
            }

            CodeGen.main.add(new LOAD(first, new Address(first)));
            CodeGen.main.add(new MOV(Register.R0, reg));
            CodeGen.main.add(new MOV(paramReg, first));
            CodeGen.main.add(new Branch("L", "p_check_array_bounds"));

            ProgramAST.nextAddress += identObj.getSize();

            CodeGen.main.add(new ADD(first, first, new ImmValue(ARRAY_SIZE)));
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
}
