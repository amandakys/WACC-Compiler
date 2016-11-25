package front_end.AST.ExpressionAST;

import back_end.PrintUtility;
import back_end.Utility;
import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Error.overflow;

public class IntSignAST extends Node {

    public IntSignAST(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public void check() {
        //no checked needed
    }

    @Override
    public void translate() {
        //Adding integer overflow runtime error message
        if(!BinOpAST.hasErrorOverflow) {
            Utility.pushData(overflow);
            PrintUtility.addToEndFunctions("p_integer_overflow");
            if(!BinOpAST.hasErrorDivByZero) {
                PrintUtility.throwRuntimeError();
            }
            BinOpAST.hasErrorOverflow = true;
        }
    }
}
