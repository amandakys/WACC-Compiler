package AST.ExpressionAST;

import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.IDENTIFIER;

/**
 * Created by npd215 on 09/11/16.
 */
public class BoolliterAST extends ExpressionAST{
    String boolVal;

    public BoolliterAST(ParserRuleContext ctx, String boolVal) {
        super(ctx);
        this.boolVal = boolVal;
    }

    @Override
    public void check() {
        checkIfInScope(boolVal);
        identObj = Visitor.ST.lookUpAll("bool");
    }
}
