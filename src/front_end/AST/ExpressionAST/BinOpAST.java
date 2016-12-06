package front_end.AST.ExpressionAST;

import antlr.BasicParser;
import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PostIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.Shift;
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

public class BinOpAST extends ExpressionAST {
    private String op;
    private List<String> expectedElemType = new ArrayList<>();
    private String returnType;

    private ExpressionAST rhs;
    private ExpressionAST lhs;
    private boolean longExpr; // This fields tells if the BinOp is in a longEpr
    private Register previousReg; //holds previous register to support longExpr
    private String previousOp; //holds previousOp to determine precedence
    //2 following protected fields are accessible by UnOpAST
    protected static boolean hasErrorDivByZero;
    protected static boolean hasErrorOverflow;

    private final int SHIFT_VALUE = 31;

    /*
    TODO: the last test yet to pass is longSplitExpr.wacc. NOT POSSIBLE with current Parser
    The problem lies in the expression parsing. eg. (-1 + 2) would be read as -(1 + 2).
    Hence, passing this test is not achievable with current Parser, regardless of how BinOpAST ,UnOpAST and other
    back_end codes are implemented.
    */

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
        //Checking rhs & lhs expressions
        lhs.checkNode();
        rhs.checkNode();
        //getting type of expression
        String firstType = lhs.getType().getTypeName();
        //Comparing type of expressions
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
        if(returnType.equals("int")) {
            Integer evaluable = constantOptimise();
            if(evaluable != null) {
                String sign = evaluable < 0 ? "-" : "";
                String value = evaluable.toString().replace("-", "");
                IntLiterAST optimisedConst = new IntLiterAST(ctx, sign, value);
                optimisedConst.translate();
                return;
            }
        } else { // return type is bool
            Boolean evaluable = booleanOptimise();
            if(evaluable != null) {
                BoolliterAST optimisedBool = new BoolliterAST(ctx, evaluable.toString());
                optimisedBool.translate();
                return;
            }
        }

        //Holds the reference to the registers going to hold lhs & rhs value
        Register lhsResult = CodeGen.notUsedRegisters.peek();
        lhs.translate();
        Register rhsResult = CodeGen.notUsedRegisters.peek();

        if ("+-*/%".contains(op)) { //op is arithmetic
            if (!(lhs instanceof BinOpAST)) {
                if (rhs instanceof BinOpAST) { //rhs is further BinOp
                    BinOpAST next = (BinOpAST) rhs;
                    next.longExpr = true; // because this is in a long expr
                    next.previousOp = op;
                    if (previousReg == null) { //previousReg has not been set
                        next.previousReg = lhsResult;
                    } else { //keep the previousReg & pass it on
                        next.previousReg = previousReg;
                    }
                }
                if (!longExpr || (!(rhs instanceof BinOpAST) && !(previousOp.equals(op)))) {
                    //run if rhs is not BinOp or not belongs to a longExpr
                    rhs.translate();
                } else {
                    //longExpr case, this will use register from previous BinOp to continue calculating
                    rhsResult = lhsResult;
                    lhsResult = previousReg;
                }
            } else {
                //lhs has higher precedence
                rhs.translate();
            }
        } else {
            //op is a logical expression
            rhs.translate();
        }

        switch(op) {
            case "+":
            case "-":
            case "*":
                if(op.equals("+")) {
                    CodeGen.main.add(new ADD(lhsResult, lhsResult, rhsResult));
                    Utility.pushRegister(rhsResult);
                    CodeGen.main.add(new Branch("LVS", "p_throw_overflow_error"));
                } else if(op.equals("-")){
                    CodeGen.main.add(new SUB(lhsResult, lhsResult, rhsResult));
                    Utility.pushRegister(rhsResult);
                    CodeGen.main.add(new Branch("LVS", "p_throw_overflow_error"));
                } else if(op.equals("*")) {
                    CodeGen.main.add(new SMULL(lhsResult, rhsResult, lhsResult, rhsResult));
                    Utility.pushRegister(rhsResult);
                    //Mult involves shifting in CMP
                    CodeGen.main.add(new CMP(rhsResult, new PostIndex
                            (lhsResult, Shift.ASR, new ImmValue(SHIFT_VALUE))));
                    CodeGen.main.add(new Branch("LNE", "p_throw_overflow_error"));
                }
                Utility.pushRegister(rhsResult); // push back register which holds temporary rhs value
                /*
                Error messages & functions will only be added if they are not yet declared
                 */
                if (!hasErrorOverflow) {
                    Utility.pushData(overflow);
                    PrintUtility.addToEndFunctions("p_integer_overflow");

                    if(!hasErrorDivByZero) {
                        PrintUtility.throwRuntimeError();
                    }

                    hasErrorOverflow = true;
                }
                break;

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

                /*
                Error messages & functions will only be added if they are not yet declared
                 */
                if (!hasErrorDivByZero) {
                    Utility.pushData(divideByZero);
                    PrintUtility.addToEndFunctions("p_divide_by_zero");

                    if(ctx.getParent() instanceof BasicParser.PrintlnContext) {
                        PrintUtility.addToPlaceholders("\"\\0\"");
                        PrintUtility.addToEndFunctions("p_print_ln");
                    }
                    if(!hasErrorOverflow) {
                        PrintUtility.throwRuntimeError();
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
        if(longExpr) { //case when this BinOp is in a longExpr
            Utility.pushRegister(rhsResult);
            if(rhs instanceof BinOpAST || previousOp.equals(op)) {
            rhs.translate();
            }
        }
    }

    private Boolean booleanOptimise() {
        Boolean result = null;
        Integer rhsValue = null;
        Integer lhsValue = null;
        if(lhs instanceof BinOpAST) {
            if(((BinOpAST) lhs).returnType.equals("bool")) {
                lhsValue = ((BinOpAST) lhs).booleanOptimise() ? 1 : 0;
            } else { // return type is int
                lhsValue = ((BinOpAST) lhs).constantOptimise();
            }
        } else if(lhs instanceof IntLiterAST) {
            lhsValue = ((IntLiterAST) lhs).getValue();
        } else if(lhs instanceof BoolliterAST) {
            lhsValue = ((BoolliterAST) lhs).getBoolVal().equals("true") ? 1 : 0;
        } else if(lhs instanceof CharLitAST) {
            lhsValue = ((CharLitAST) lhs).getCodePoint();
        }

        if(rhs instanceof BinOpAST) {
            if(((BinOpAST) rhs).returnType.equals("bool")) {
                rhsValue = ((BinOpAST) rhs).booleanOptimise() ? 1 : 0;
            } else {
                rhsValue = ((BinOpAST) rhs).constantOptimise();
            }
        } else if(rhs instanceof IntLiterAST) {
            rhsValue = ((IntLiterAST) rhs).getValue();
        } else if(rhs instanceof BoolliterAST) {
            rhsValue = ((BoolliterAST) rhs).getBoolVal().equals("true") ? 1 : 0;
        } else if(rhs instanceof CharLitAST) {
            rhsValue = ((CharLitAST) rhs).getCodePoint();
        }

        if(lhsValue != null && rhsValue != null) {
            switch (op) {
                case ">":
                    result = lhsValue > rhsValue;
                    break;
                case ">=":
                    result = lhsValue >= rhsValue;
                    break;
                case "<":
                    result = lhsValue < rhsValue;
                    break;
                case "<=":
                    result = lhsValue <= rhsValue;
                    break;
                case "==":
                    result = lhsValue.equals(rhsValue);
                    break;
                case "!=":
                    result = !lhsValue.equals(rhsValue);
                    break;
                case "&&":
                    result = (lhsValue == 1) && (rhsValue == 1);
                    break;
                case "||":
                    result = (lhsValue == 1) || (rhsValue == 1);
                    break;
            }
        }
        return result;
    }

    private Integer constantOptimise() {
        Integer result = null;
        Integer rhsValue = null;
        Integer lhsValue = null;
        if(lhs instanceof BinOpAST) {
            lhsValue = ((BinOpAST) lhs).constantOptimise();
        } else if(lhs instanceof IntLiterAST) {
            lhsValue = ((IntLiterAST) lhs).getValue();
        }

        if(rhs instanceof BinOpAST) {
            rhsValue = ((BinOpAST) rhs).constantOptimise();
        } else if(rhs instanceof IntLiterAST) {
            rhsValue = ((IntLiterAST) rhs).getValue();
        }

        if(lhsValue != null && rhsValue != null) {
            switch(op) {
                case "+":
                    result = lhsValue + rhsValue;
                    break;
                case "-":
                    result = lhsValue - rhsValue;
                    break;
                case "*":
                    result = lhsValue * rhsValue;
                    break;
                case "/":
                    result = lhsValue / rhsValue;
                    break;
                case "%":
                    result = lhsValue % rhsValue;
                    break;
            }

        }
        return result;
    }

    /*
    Assign expected type & return type for specific operator this BinOp is having
     */
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
}
