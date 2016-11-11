package AST;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class Utility {
    public static void error(String message) {
        System.err.println(message);
        System.exit(200);
    }

    public static void error(ParserRuleContext ctx, String message) {
        System.err.println("line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine()
                + " " + ctx.start.getText() + " " + message);
        System.exit(200);
    }
}
