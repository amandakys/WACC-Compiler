package AST.ExpressionAST;

import AST.ExpressionAST.ExpressionAST;
import main.Visitor;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;

/**
 * Created by andikoh on 11/11/2016.
 */
public class PairliterAST extends ExpressionAST {
    String nullStr;
    public PairliterAST(String text) {
        super();
        nullStr = text;
//        IDENTIFIER pair = Visitor.ST.lookUpAll("pair");
//        identObj = pair;
    }

    @Override
    public void check() {
        identObj = new PAIR(null, null);
    }
}
