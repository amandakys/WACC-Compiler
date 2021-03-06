package front_end.AST.ExpressionAST;

import antlr.BasicParser;
import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PostIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.Shift;
import back_end.instruction.Branch;
import back_end.instruction.POP;
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
        this.longExpr = false;

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
        //Holds the reference to the registers going to hold lhs & rhs value
        lhs.translate();

        if ("+-*/%".contains(op)) { //op is arithmetic
            if (!(lhs instanceof BinOpAST)) {
                if (rhs instanceof BinOpAST) { //rhs is further BinOp
                    BinOpAST next = (BinOpAST) rhs;
                    next.longExpr = true; // because this is in a long expr
                    next.previousOp = op;
//                    if (previousReg == null) { //previousReg has not been set
//                        next.previousReg = lhsResult;
//                    } else { //keep the previousReg & pass it on
//                        next.previousReg = previousReg;
//                    }
                }
                if (!longExpr || (!(rhs instanceof BinOpAST) && !(previousOp.equals(op)))) {
                    //run if rhs is not BinOp or not belongs to a longExpr
                    rhs.translate();
//                } else {
//                    //longExpr case, this will use register from previous BinOp to continue calculating
//                    rhsResult = lhsResult;
//                    lhsResult = previousReg;
                }
            } else {
                //lhs has higher precedence
                rhs.translate();
            }
        } else {
            //op is a logical expression
            rhs.translate();
        }

        Register lhsResult = lhs.getRegister();
        Register rhsResult = rhs.getRegister();

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
//                    if(lhs instanceof IntLiterAST) {
//                        int shiftNumber = ((IntLiterAST) lhs).getValue();
//                        if(shiftNumber % 2 == 0) {
//                            CodeGen.main.add(new MOV(lhsResult, new PostIndex(rhsResult, Shift.ASR,
//                                    new ImmValue(shiftNumber / 2))));
//                        }
//                    }
                    CodeGen.main.add(new SMULL(lhsResult, rhsResult, lhsResult, rhsResult));
                    //Mult involves shifting in CMP
                    CodeGen.main.add(new CMP(rhsResult, new PostIndex
                            (lhsResult, Shift.ASR, new ImmValue(SHIFT_VALUE))));
                    CodeGen.main.add(new Branch("LNE", "p_throw_overflow_error"));
                }

                //Error messages & functions will only be added if they are not yet declared
                if (!hasErrorOverflow) {
                    Utility.pushData(overflow);
                    PrintUtility.addToEndFunctions("p_integer_overflow", lhsResult);

                    //a new function must be added as this also uses registers

                    if(!hasErrorDivByZero) {
                        PrintUtility.throwRuntimeError();
                    }

                    hasErrorOverflow = true;
                }
                break;
            case "/":
            case "%":
                CodeGen.main.add(new MOV(Register.R0, lhsResult));

                //__aeabi_idiv divides the value stored in R0 by the value stored in R1
                CodeGen.main.add(new MOV(Register.R1, rhsResult));
                CodeGen.main.add(new Branch("L", "p_check_divide_by_zero"));

                if(op.equals("/")) {
                    CodeGen.main.add(new Branch("L", "__aeabi_idiv"));
                } else {
                    CodeGen.main.add(new Branch("L", "__aeabi_idivmod"));
                }

                Register reg = op.equals("%") ? Register.R1 : Register.R0;
                //depending on the operator, the result inside in lhs is stored as specification
                CodeGen.main.add(new MOV(lhsResult, reg));

                //Error messages & functions will only be added if they are not yet declared
                if (!hasErrorDivByZero) {
                    Utility.pushData(divideByZero);
                    PrintUtility.addToEndFunctions("p_divide_by_zero", rhsResult);

                    if(ctx.getParent() instanceof BasicParser.PrintlnContext) {
                        PrintUtility.addToPlaceholders("\"\\0\"");
                        PrintUtility.addToEndFunctions("p_print_ln", Register.R0);
                    }

                    if(!hasErrorOverflow) {
                        PrintUtility.throwRuntimeError();
                    }
                    hasErrorDivByZero = true;
                }
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
                CodeGen.main.add(new MOV("EQ", lhsResult, new ImmValue(1)));
                CodeGen.main.add(new MOV("NE", lhsResult, new ImmValue(0)));
                break;
            case "!=":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
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

        if(longExpr) { //case when this BinOp is in a longExpr
            if(rhs instanceof BinOpAST || previousOp.equals(op)) {
                rhs.translate();
            }
        }
    }

    @Override
    public void weight() {
        lhs.weight();
        rhs.weight();
        size = lhs.getSize() + rhs.getSize();
    }

    @Override
    public void IRepresentation() {
        //print error message may be caused by divide by zero exception or integer overflow
        if(("%/+-*").contains(op)) {
            reserveRegForPrintStr();
        }

        lhs.IRepresentation();
        rhs.IRepresentation();

        IGNode = lhs.getIGNode();
        //lhs and rhs must be alive at the same time as they both come from a binOp node
        lhs.getIGNode().addEdge(rhs.getIGNode());
    }

    private boolean isNull(ExpressionAST exp) {
        return ((BinOpAST) exp).booleanOptimise() == null;
    }

    /*
    Extension: Trying evaluation
    Returning appropriate result of type BinOpAST(failed to evaluate) or IntLiterAST or BoolLiterAST(succeeded)
     */
    public ExpressionAST tryEvaluate() {
        if(returnType.equals("int")) {
            Integer evaluable = constantOptimise(); //try evaluate & get result constant
            if(evaluable != null) {
                String sign = evaluable < 0 ? "-" : "";
                String value = evaluable.toString().replace("-", "");
                return new IntLiterAST(ctx, sign, value);
            }
        } else { // return type must be a bool
            Boolean evaluable = booleanOptimise(); //try evaluate & get boolean value
            if(evaluable != null) {
                return new BoolliterAST(ctx, evaluable.toString());
            }
        }
        return this;
    }


    /*
    This method will try to evaluate this binOp & return the result boolean.
    Return null if failed to do so (eg. binOp contains a variable)
    This method does not modify anything so can be made public allowing conditional branch statements to use
     */
    public Boolean booleanOptimise() {
        Boolean result = null;
        Integer rhsValue = null;
        Integer lhsValue = null;
        if(lhs instanceof BinOpAST) {
            if(((BinOpAST) lhs).returnType.equals("bool")) {
                if (!isNull(lhs)) {
                    lhsValue = ((BinOpAST) lhs).booleanOptimise() ? 1 : 0;
                }
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
                if (!isNull(lhs)) {
                    rhsValue = ((BinOpAST) rhs).booleanOptimise() ? 1 : 0;
                }
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

    /*
    This method will try to evaluate this binOp & return the result constant.
    Return null if failed to do so (eg. containing a variable)
     */
    public Integer constantOptimise() {
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
                    if(rhsValue != 0) {
                        result = lhsValue / rhsValue;
                    }
                    break;
                case "%":
                    if(rhsValue != 0) {
                        result = lhsValue % rhsValue;
                    }
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

    public List<String> getIdents() {
        List<String> idents = new ArrayList<>();

        if (lhs instanceof BinOpAST && rhs instanceof BinOpAST) {
            idents.addAll(((BinOpAST) lhs).getIdents());
            idents.addAll(((BinOpAST) rhs).getIdents());
        } else if (lhs instanceof BinOpAST) {
            idents.addAll(((BinOpAST) lhs).getIdents());
            if (rhs instanceof IdentAST) {
                idents.add(((IdentAST) rhs).getIdent());
            }
        } else if (rhs instanceof BinOpAST) {
            idents.addAll(((BinOpAST) rhs).getIdents());
            if (lhs instanceof IdentAST) {
                idents.add(((IdentAST) lhs).getIdent());
            }
        } else if (lhs instanceof IdentAST && rhs instanceof IdentAST) {
            idents.add(((IdentAST) lhs).getIdent());
            idents.add(((IdentAST) rhs).getIdent());
        } else if (lhs instanceof IdentAST) {
            idents.add(((IdentAST) lhs).getIdent());
        } else if (rhs instanceof IdentAST) {
            idents.add(((IdentAST) rhs).getIdent());
        }
        return idents;
    }
}
