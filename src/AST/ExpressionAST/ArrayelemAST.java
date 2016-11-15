package AST.ExpressionAST;

import AST.Node;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.ARRAY;
import symbol_table.IDENTIFIER;
import symbol_table.TYPE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class ArrayelemAST extends ExpressionAST {
    String ident;
    List<Node> expressions = new ArrayList<>();

    public ArrayelemAST(ParserRuleContext ctx, String ident, List<Node> expressionNodes) {
        super(ctx);
        this.ident = ident;
        this.expressions = expressionNodes;
    }

    @Override
    public void check() {
        //check idents
        IDENTIFIER N = Visitor.ST.lookUp(ident);
        if (N == null) {
            error("undeclared variable");
        } else {

            for (Node n : expressions) {
                n.checkNode();
                TYPE T = Visitor.ST.lookUpAll("int").getType();
                if (!T.equals(n.getType())) {
                    error("arrayelement only takes integers, actual: " + T.getTypeName());
                }
            }

            identObj = ((ARRAY) N.getType()).getElementType();
        }
    }
}
