package AST;

import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ArraylitAST extends Node {
    private List<Node> arraylits;

    public ArraylitAST(List<Node> arraylits) {
        super();
        this.arraylits = arraylits;
    }


    @Override
    public void check() {
        for(Node a : arraylits) {
            a.check();
        }
    }
}
