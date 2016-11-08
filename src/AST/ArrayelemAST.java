package AST;

import antlr.BasicParser;
import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class ArrayelemAST extends Node {
    String ident;
    List<Node> expressions = new ArrayList<>();

    public ArrayelemAST(SymbolTable ST, String ident, List<Node> expressionNodes) {
        super(ST);
        this.ident = ident;
        this.expressions = expressionNodes;
    }

    @Override
    public void check() {
        //check idents
        IDENTIFIER N = ST.lookUp(ident);
        if (N == null) {
            System.err.println("undeclared variable");
        } else {
            identObj = N;
        }


        for (Node n : expressions) {
            n.check();
            this.checkType(n);
        }
    }
}
