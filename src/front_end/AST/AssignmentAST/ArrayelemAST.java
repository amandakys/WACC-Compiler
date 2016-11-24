package front_end.AST.AssignmentAST;

import antlr.BasicParser;
import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.PostIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.Shift;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.Branch;
import back_end.instruction.POP;
import back_end.instruction.PUSH;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ExpressionAST.IntLiterAST;

import back_end.instruction.load_store.LOAD;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import front_end.AST.StatementAST.PrintAST;
import front_end.AST.StatementAST.PrintlnAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;

import java.util.ArrayList;
import java.util.List;

public class ArrayelemAST extends ExpressionAST {
    private String ident;
    private List<Node> expressions = new ArrayList<>();

    private final String NEGATIVE_ARRAY_BOUND = "\"ArrayIndexOutOfBoundsError: negative index\\n\\0\"";
    private final String TOO_LARGE_ARRAY_BOUND = "\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"";
    private static boolean hasError;
    private final int INT_SIZE = 4;

    public ArrayelemAST(ParserRuleContext ctx, String ident, List<Node> expressionNodes) {
        super(ctx);
        this.ident = ident;
        this.expressions = expressionNodes;
        this.hasError = false;
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
        if(!hasError) {
            Register first = Utility.popUnusedReg();
            Utility.pushData(NEGATIVE_ARRAY_BOUND);
            Utility.pushData(TOO_LARGE_ARRAY_BOUND);

            CodeGen.main.add(new ADD(first, Register.SP, new ImmValue(ProgramAST.nextAddress)));
            Register reg = CodeGen.notUsedRegisters.peek();
            for(Node n : expressions) {
                n.translate();
            }

            CodeGen.main.add(new LOAD(first, new Address(first)));
            CodeGen.main.add(new MOV(Register.R0, reg));
            CodeGen.main.add(new MOV(Utility.popParamReg(), first));
            CodeGen.main.add(new Branch("L", "p_check_array_bounds"));

            ProgramAST.nextAddress += identObj.getSize();
            ShiftedReg size = new PostIndex(first, Utility.popParamReg(), Shift.LSL, new ImmValue(2));

            CodeGen.main.add(new ADD(first, first, new ImmValue(ProgramAST.nextAddress)));
            CodeGen.main.add(new ADD(first, first, size));
            CodeGen.main.add(new LOAD(first, new Address(first)));

            CodeGen.endFunctions.add("p_check_array_bounds");
            Utility.throwRuntimeError();
            hasError = true;
        }
    }

    public static boolean isHasError() {
        return hasError;
    }
}
