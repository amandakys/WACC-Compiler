package AST.ExpressionAST;

import AST.Node;
import AST.Utility;
import antlr.BasicParser;
import main.Visitor;
import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class ArrayelemAST extends ExpressionAST {
    String ident;
    List<Node> expressions = new ArrayList<>();

    public ArrayelemAST(String ident, List<Node> expressionNodes) {
        super();
        this.ident = ident;
        this.expressions = expressionNodes;
    }

    @Override
    public void check() {
        //check idents
        IDENTIFIER N = Visitor.ST.lookUp(ident);
        if (N == null) {
            Utility.error("undeclared variable");
        } else {
            identObj = N;
        }


        for (Node n : expressions) {
            n.check();
            this.checkType(n);
        }
    }
}
