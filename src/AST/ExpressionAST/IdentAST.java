package AST.ExpressionAST;

import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.IDENTIFIER;

/**
 * Created by tsd15 on 10/11/16.
 */
public class IdentAST extends ExpressionAST {
    String ident;
    public IdentAST(ParserRuleContext ctx, String ident) {
        super(ctx);
        this.ident = ident;
        IDENTIFIER identType = Visitor.ST.lookUpAll(ident);
        if (identType == null) {
            error("undefined expression: " + ident);
        } else {
//            if (ident == "null") {
//                identObj = new PAIR(null, null);
//            } else {
                identObj = identType;
//            }
        }
    }

    @Override
    public void check() {
        IDENTIFIER I = Visitor.ST.lookUp(ident);
        if(I == null) {
            error("cannot find identifier: "+ ident);
        }
    }

    public String getIdent() {
        return this.ident;
    }
}
