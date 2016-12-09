package front_end.AST.ExpressionAST;

import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.Expression;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.EOR;
import back_end.instruction.data_manipulation.RSBS;
import back_end.instruction.load_store.LOAD;
import front_end.symbol_table.ARRAY;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Error.overflow;

/**
 * Created by andikoh on 10/11/2016.
 */
public class UnopAST extends ExpressionAST {
    private String expectedElemType;
    private String returnType;

    private ExpressionAST expression;
    private String unop;

    public UnopAST(ParserRuleContext ctx, ExpressionAST expression, String unop) {
        super(ctx);
        this.expression = expression;
        this.unop = unop;
        initialise();
    }
    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll(returnType);
        //throwing unexpected type
        if (!expectedElemType.equals(expression.getType().getTypeName())) {
            error(unop +" received an unexpected type\nexpects: " + expectedElemType + "\nactual: "+ expression.getType().getTypeName());
        }
    }

    @Override
    public void translate() {
        expression.translate();
        //Get reference to the Register holding value of expression translated
        Register op = expression.getRegister();

        switch (unop) {
            case "!":
                //Using Exclusive Or with Immvalue 1
                CodeGen.main.add(new EOR(op, op, new ImmValue(1)));
                break;
            case "-":
                CodeGen.main.add(new RSBS(op, op));
                CodeGen.main.add(new Branch("LVS", "p_throw_overflow_error"));
                 /*
                Error messages & functions will only be added if they are not yet declared
                 */
                if(!BinOpAST.hasErrorOverflow) {
                    Utility.pushData(overflow);
                    PrintUtility.addToEndFunctions("p_integer_overflow", getRegister());
                    if(!BinOpAST.hasErrorDivByZero) {
                        PrintUtility.throwRuntimeError();
                    }
                    BinOpAST.hasErrorOverflow = true;
                }
                break;
            case "len":
                CodeGen.main.add(new LOAD(op, new Address(op)));
                break;
            case "ord":
                //Do nothing
                break;
            case "chr":
                //Do nothing
                break;
        }
    }

    @Override
    public void weight() {
        expression.weight();
        size = expression.getSize();
    }

    @Override
    public void IRepresentation() {
        expression.IRepresentation();
        IGNode = expression.getIGNode();

        if (unop.equals("-")) {
            print_stringIR();
        }
    }

    //Extension: Trying evaluation
    public ExpressionAST tryEvaluation() {
        if(returnType.equals("bool")) { //unOp must be '!'
            Boolean evaluable = booleanOptimise(); //try evaluate & get boolean value
            if(evaluable != null) {
                return new BoolliterAST(ctx, evaluable.toString());
            }
        } else if(returnType.equals("int")) {
            Integer evaluable = constantOptimise(); //try evaluate & get result constant
            if(evaluable != null) {
                String sign = evaluable < 0 ? "-" : "";
                String value = evaluable.toString().replace("-", "");
                return new IntLiterAST(ctx, sign, value);
            }
        } else { // return type is char
            Character evaluable = chrOptimise(); //try evaluate & get result char
            if(evaluable != null) {
                return new CharLitAST(ctx, evaluable.toString());
            }
        }
        return this;
    }

    /*
    This medthod is called only when return type is bool & therefore unOp must be "!"
     */
    private Boolean booleanOptimise() {
        Boolean result = null;
        //Expected type can only be bool
        if(expression instanceof BoolliterAST) {
            result = !(((BoolliterAST) expression).getBoolVal().equals("true"));
        } else if(expression instanceof UnopAST) {
            result = !(((UnopAST) expression).booleanOptimise());
        } else if(expression instanceof BinOpAST) {
            result = !(((BinOpAST) expression).booleanOptimise()); // calling binOp.booleanOptimise()
        }
        return result;
    }

    /*
    This medthod is called only when return type is int & therefore unOp can be "-" , "len" or "ord"
    This method does not modify anything so can be made public allowing other classes to use
     */
    public Integer constantOptimise() {
        Integer result = null;
        //Expected types can only be int, array or char.
        if(expression instanceof IntLiterAST) {
            result = -(((IntLiterAST) expression).getValue());
        } else if(expression instanceof CharLitAST) {
            result = ((CharLitAST) expression).getCodePoint();
        } else if(expression.getType() instanceof ARRAY) {
            result = ((ARRAY) expression.getType()).getElem_size();
        } else if(expression instanceof UnopAST){
            result = -(((UnopAST) expression).constantOptimise());
        } else if(expression instanceof BinOpAST) {
            result = -(((BinOpAST) expression).constantOptimise());
        }

        return result;
    }

    /*
    This medthod is called only when return type is char & therefore unOp must be "chr"
    This method does not modify anything so can be made public allowing other classes to use
     */
    public Character chrOptimise() {
        Character result = null;
        //Expected type can only be int.
        if(expression instanceof IntLiterAST) {
            result = (char) ((IntLiterAST) expression).getValue();
        } else if(expression instanceof UnopAST) {
            result = (char)(int) ((UnopAST) expression).constantOptimise();
        } else if(expression instanceof BinOpAST) {
            result = (char)(int) ((BinOpAST) expression).constantOptimise();
        }
        return result;
    }

    private void initialise() {
        switch(unop) {
            case "!": returnType = "bool";
                expectedElemType = "bool";
                break;
            case "-": returnType = "int";
                expectedElemType = "int";
                break;
            case "len": returnType = "int";
                expectedElemType = "array";
                break;
            case "ord": returnType = "int";
                expectedElemType = "char";
                break;
            case "chr": returnType = "char";
                expectedElemType = "int";
                break;

        }
    }
}
