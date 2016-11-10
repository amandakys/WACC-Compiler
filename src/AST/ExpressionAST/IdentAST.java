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
        identObj = Visitor.ST.lookUpAll(ident);
    }

    @Override
    public void check() {
        IDENTIFIER I = Visitor.ST.lookUp(ident);
        if(I == null) {
            Utility.error("cannot find identifier");
        }
    }
}