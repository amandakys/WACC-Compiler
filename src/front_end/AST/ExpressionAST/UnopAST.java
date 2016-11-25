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
        if (!expectedElemType.equals(expression.getType().getTypeName())) {
            error(unop +" received an unexpected type\nexpects: " + expectedElemType + "\nactual: "+ expression.getType().getTypeName());
        }
    }

    @Override
    public void translate() {
        Register op = CodeGen.notUsedRegisters.peek();
        expression.translate();

        switch (unop) {
            case "!":
                //Using Exclusive Or with Immvalue 1
                CodeGen.main.add(new EOR(op, op, new ImmValue(1)));
                break;
            case "-":
                CodeGen.main.add(new RSBS(op, op));
                CodeGen.main.add(new Branch("LVS", "p_throw_overflow_error"));
                if(!BinOpAST.hasErrorOverflow) {
                    Utility.pushData(overflow);
                    PrintUtility.addToEndFunctions("p_integer_overflow");
                    if(!BinOpAST.hasErrorDivByZero) {
                        PrintUtility.throwRuntimeError();
                    }
                    BinOpAST.hasErrorOverflow = true;
                }

                PrintUtility.throwRuntimeError();
                break;
            case "len":
                //TODO: you can't put a register into load without popping it off the unusedRegs list
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
