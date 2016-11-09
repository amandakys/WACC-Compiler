package AST;

import symbol_table.SymbolTable;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ParamAST extends Node {
    String type;
    String ident;

    public ParamAST(SymbolTable ST, String type, String ident) {
        super(ST);
        this.type = type;
        this.ident = ident;
    }

    @Override
    public void check() {

    }
}