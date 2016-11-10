package AST.ExpressionAST;

import AST.Utility;
import main.Visitor;
import symbol_table.IDENTIFIER;

/**
 * Created by tsd15 on 10/11/16.
 */
public class IdentAST extends ExpressionAST {
    String ident;
    public IdentAST(String ident) {
        this.ident = ident;
        IDENTIFIER lookup = Visitor.ST.lookUpAll(ident);
        if (lookup == null) {
            Utility.error("undefined expression: " + ident);
        } else {
            identObj = lookup;
        }
    }

    @Override
    public void check() {
        IDENTIFIER I = Visitor.ST.lookUp(ident);
        if(I == null) {
            Utility.error("cannot find identifier: "+ ident);
        }
    }
}
