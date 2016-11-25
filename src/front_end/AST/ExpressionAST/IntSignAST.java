package front_end.AST.ExpressionAST;

import back_end.PrintUtility;
import back_end.Utility;
import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Error.overflow;

/**
 * Created by andikoh on 08/11/2016.
 */
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
