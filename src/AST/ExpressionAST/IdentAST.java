package AST.ExpressionAST;

import main.Visitor;

/**
 * Created by tsd15 on 10/11/16.
 */
public class IdentAST extends ExpressionAST {
    String ident;
    public IdentAST(String ident) {
        this.ident = ident;
        identObj = Visitor.ST.lookUpAll(ident);
    }
}
