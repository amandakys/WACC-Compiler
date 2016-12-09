package front_end.AST.ExpressionAST;

import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.EOR;
import back_end.instruction.data_manipulation.RSBS;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Error.overflow;

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
        //Get reference to the Register holding value of expression translated
        expression.translate();
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

        if(unop.equals("-")) {
            print_stringIR();
        }
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
