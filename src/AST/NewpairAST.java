package AST;

import symbol_table.SCALAR;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class NewpairAST extends Node {
    List<Node> pairelems;

    public NewpairAST(SymbolTable ST, List<Node> pairelems) {
        super(ST);
        this.pairelems = pairelems;
    }

    @Override
    public void check() {
        for(Node p : pairelems) {
            p.check();

        }
    }
}
