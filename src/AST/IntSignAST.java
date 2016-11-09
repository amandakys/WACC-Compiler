package AST;

import symbol_table.SymbolTable;

/**
 * Created by andikoh on 08/11/2016.
 */
public class IntSignAST extends Node {

    public IntSignAST(SymbolTable ST) {
        super(ST);
    }

    @Override
    public void check() {
        //no checked needed
    }
}
