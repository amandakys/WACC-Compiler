package AST.ExpressionAST;

import AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.SymbolTable;

/**
 * Created by andikoh on 08/11/2016.
 */
public class IntSignAST extends Node {

    public IntSignAST(ParserRuleContext ctx,) {
        super(ctx);
    }

    @Override
    public void check() {
        //no checked needed
    }
}
