package front_end.AST.ExpressionAST;


import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.condition.AND;
import back_end.instruction.condition.CMP;
import back_end.instruction.condition.ORR;

import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.data_manipulation.SMULL;
import back_end.instruction.data_manipulation.SUB;
import com.sun.org.apache.bcel.internal.classfile.Code;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class BinOpAST extends ExpressionAST {
    private String op;
    private List<String> expectedElemType = new ArrayList<>();
    private String returnType;

    private ExpressionAST rhs;
    private ExpressionAST lhs;

    public BinOpAST(ParserRuleContext ctx, String op, ExpressionAST lhs, ExpressionAST rhs) {
        super(ctx);
        this.op = op;
        this.rhs = rhs;
        this.lhs = lhs;
        initialise();
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll(returnType);

        lhs.checkNode();
        rhs.checkNode();

        String firstType = lhs.getType().getTypeName();

        if(expectedElemType.contains(firstType)) {
            if(!rhs.getType().getTypeName().equals(lhs.getType().getTypeName())) {
                error("not the same type");
            }
        } else {
            error("not expected type");
        }
    }

    @Override
    public void translate() {
        Register lhsResult = CodeGen.notUsedRegisters.peek();
        lhs.translate(); //this will then add to main a LOAD instr which put value of lhs into lhsResult
        Register rhsResult = CodeGen.notUsedRegisters.peek();
        rhs.translate(); //same as lhs
        switch(op) {
            case "*":
                CodeGen.main.add(new SMULL(lhsResult, rhsResult, lhsResult, rhsResult));
                CodeGen.main.add(new CMP(rhsResult, lhsResult)); // TODO: add ASR #31 shifting HERE as a third param
                CodeGen.main.add(new Branch("BLNE", "p_throw_overflow_error"));
                break;
            case "/":
                CodeGen.main.add(new MOV(Register.R0, lhsResult)); // TODO: Find out why Mov into R0 but not R1, same for %
                CodeGen.main.add(new MOV(Utility.popParamReg(), rhsResult));
                CodeGen.main.add(new Branch("L", "p_check_divide_by_zero")); //TODO: wrtite check_divide.. function
                CodeGen.main.add(new Branch("L", "__aeabi_idiv"));
                CodeGen.main.add(new MOV(lhsResult, Register.R0));
                break;
            case "%":
                CodeGen.main.add(new MOV(Register.R0, lhsResult));
                CodeGen.main.add(new MOV(Utility.popParamReg(), rhsResult));
                CodeGen.main.add(new Branch("L", "p_check_divide_by_zero"));
                CodeGen.main.add(new Branch("L", "__aeabi_idivmod"));
                CodeGen.main.add(new MOV(lhsResult, Register.R1));
                break;
            case "+":
                CodeGen.main.add(new ADD(lhsResult, lhsResult, rhsResult));
                break;
            case "-":
                CodeGen.main.add(new SUB(lhsResult, lhsResult, rhsResult));
                //TODO: make function in Utility to throw overflow error
                break;
            case ">":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));

                CodeGen.main.add(new MOV("GT", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("LE", lhsResult, new ImmValue(0)));
                break;
            case ">=":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                CodeGen.main.add(new MOV("GE", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("LT", lhsResult, new ImmValue(0)));
                break;
            case "<":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                CodeGen.main.add(new MOV("LT", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("GE", lhsResult, new ImmValue(0)));
                break;
            case "<=":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                CodeGen.main.add(new MOV("LE", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("GT", lhsResult, new ImmValue(0)));
                break;
            case "==":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                CodeGen.notUsedRegisters.push(rhsResult);
                CodeGen.main.add(new MOV("EQ", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("NE", lhsResult, new ImmValue(0)));
                break;
            case "!=":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                CodeGen.notUsedRegisters.push(rhsResult);
                CodeGen.main.add(new MOV("NE", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("EQ", lhsResult, new ImmValue(0)));
                break;
            case "&&":
                CodeGen.main.add(new AND(lhsResult, lhsResult, rhsResult));
                break;
            case "||":
                CodeGen.main.add(new ORR(lhsResult, lhsResult, rhsResult));
                break;
        }

    }

    private void initialise() {
        switch (op) {
            case "*":
            case "/":
            case "%":
            case "+":
            case "-":
                expectedElemType.add("int");
                returnType = "int";
                break;
            case ">":
            case ">=":
            case "<":
            case "<=":
                expectedElemType.add("int");
                expectedElemType.add("char");
                returnType = "bool";
                break;
            case "==":
            case "!=":
                expectedElemType.add("bool");
                expectedElemType.add("int");
                expectedElemType.add("char");
                expectedElemType.add("string");
                expectedElemType.add("pair");
                returnType = "bool";
            case "&&":
            case "||":
                expectedElemType.add("bool");
                returnType = "bool";
                break;
        }
    }
}
