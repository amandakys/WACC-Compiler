package front_end.AST.ExpressionAST;

import antlr.BasicParser;
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
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

import static back_end.Error.divideByZero;
import static back_end.Error.overflow;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class BinOpAST extends ExpressionAST {
    private String op;
    private List<String> expectedElemType = new ArrayList<>();
    private String returnType;

    private ExpressionAST rhs;
    private ExpressionAST lhs;
    private boolean longExpr; // This fields tells if the BinOp is in a longEpr
    private Register previousReg;

    private static boolean hasErrorDivByZero;
    private static boolean hasErrorOverflow;


    public BinOpAST(ParserRuleContext ctx, String op, ExpressionAST lhs, ExpressionAST rhs) {
        super(ctx);
        this.op = op;
        this.rhs = rhs;
        this.lhs = lhs;
        hasErrorDivByZero = false;
        hasErrorOverflow = false;
        longExpr = false;
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
        lhs.translate();
        Register rhsResult = CodeGen.notUsedRegisters.peek();
        if(rhs instanceof BinOpAST) { //rhs is further BinOp
            BinOpAST next = (BinOpAST) rhs;
            next.longExpr = true; // because this is in a long expr
            if(previousReg == null) { //previousReg has not been set
                next.previousReg = lhsResult;
            } else { //keep the previousReg & pass it on
                next.previousReg = previousReg;
            }
        }
        if(!longExpr) { // if not a longExpr do as normal
            rhs.translate();
        } else {
//            Utility.pushRegister(lhsResult);
            rhsResult = lhsResult;
            lhsResult = previousReg;
        }
        switch(op) {
            case "+":
            case "-":
            case "*":
                if(op.equals("+")) {
                    CodeGen.main.add(new ADD(lhsResult, lhsResult, rhsResult));
                    CodeGen.main.add(new Branch("LVS", "p_throw_overflow_error"));
                } else if(op.equals("-")){
                    CodeGen.main.add(new SUB(lhsResult, lhsResult, rhsResult));
                    CodeGen.main.add(new Branch("LVS", "p_throw_overflow_error"));
                } else if(op.equals("*")) {
                    CodeGen.main.add(new SMULL(lhsResult, rhsResult, lhsResult, rhsResult));
                    // TODO:add ASR #31 shifting HERE as a third param for multNoWhitespaceExpr.wacc
                    CodeGen.main.add(new CMP(rhsResult, lhsResult));
                    CodeGen.main.add(new Branch("LNE", "p_throw_overflow_error"));
                }
                Utility.pushRegister(rhsResult);
                if (!hasErrorOverflow) {
                    Utility.pushData(overflow);
                    CodeGen.endFunctions.add("p_integer_overflow");
                    if(!hasErrorDivByZero) {
                        Utility.throwRuntimeError();
                    }
                    hasErrorOverflow = true;
                }
                break;

//                try {
//                    int val =
//                    CodeGen.main.add(new LOAD(Utility.popUnusedReg(), new ImmValue(val)));
//                } catch (NumberFormatException e) {
//                    Utility.pushData(OVERFLOW_ERROR_MESSAGE);
//
//                    Register second = CodeGen.toPushUnusedReg.get(0);
//                    Register first = CodeGen.toPushUnusedReg.get(1);
//
//                    //CodeGen.main.add(new CMP(second, new PostIndex(first, ASR, new ImmValue(31))));
//                    CodeGen.main.add(new Branch("LNE", "p_throw_overflow_error"));
//                    CodeGen.endFunctions.add("p_print_string");

            case "/":
            case "%":
                CodeGen.main.add(new MOV(Register.R0, lhsResult));

                    Register res = Utility.popParamReg();
                    CodeGen.main.add(new MOV(res, rhsResult));
                    CodeGen.main.add(new Branch("L", "p_check_divide_by_zero"));
                    if(op.equals("/")) {
                        CodeGen.main.add(new Branch("L", "__aeabi_idiv"));
                    } else {
                        CodeGen.main.add(new Branch("L", "__aeabi_idivmod"));
                    }

                res = op.equals("%") ? res : Register.R0;
                CodeGen.main.add(new MOV(lhsResult, res));

                if (!hasErrorDivByZero) {
                    Utility.pushData(divideByZero);
                    CodeGen.endFunctions.add("p_divide_by_zero");
                    if(ctx.getParent() instanceof BasicParser.PrintlnContext) {
                        CodeGen.placeholders.add("\"\\0\"");
                        CodeGen.endFunctions.add("p_print_ln");
                    }
                    if(!hasErrorOverflow) {
                        Utility.throwRuntimeError();
                    }
                    hasErrorDivByZero = true;
                }
                break;
            case ">":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                Utility.pushRegister(rhsResult);
                CodeGen.main.add(new MOV("GT", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("LE", lhsResult, new ImmValue(0)));
                break;
            case ">=":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                Utility.pushRegister(rhsResult);
                CodeGen.main.add(new MOV("GE", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("LT", lhsResult, new ImmValue(0)));
                break;
            case "<":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                Utility.pushRegister(rhsResult);
                CodeGen.main.add(new MOV("LT", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("GE", lhsResult, new ImmValue(0)));
                break;
            case "<=":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                Utility.pushRegister(rhsResult);
                CodeGen.main.add(new MOV("LE", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("GT", lhsResult, new ImmValue(0)));
                break;
            case "==":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                Utility.pushRegister(rhsResult);
                CodeGen.main.add(new MOV("EQ", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("NE", lhsResult, new ImmValue(0)));
                break;
            case "!=":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                Utility.pushRegister(rhsResult);
                CodeGen.main.add(new MOV("NE", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("EQ", lhsResult, new ImmValue(0)));
                break;
            case "&&":
                CodeGen.main.add(new AND(lhsResult, lhsResult, rhsResult));
                Utility.pushRegister(rhsResult);
                break;
            case "||":
                CodeGen.main.add(new ORR(lhsResult, lhsResult, rhsResult));
                Utility.pushRegister(rhsResult);
                break;
        }
        if(longExpr) {
            Utility.pushRegister(rhsResult);
            rhs.translate();
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

    public String getOp() {
        return op;
    }

    public static boolean isHasErrorDivByZero() {
        return hasErrorDivByZero;
    }
}
