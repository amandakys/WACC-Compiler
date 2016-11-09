package AST;

import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;

/**
 * Created by andikoh on 08/11/2016.
 */
public class AssignlhsAST extends Node{
    String ident;
    Node child;

    public AssignlhsAST(SymbolTable ST, String ident) {
        super(ST);
        this.ident = ident;
        this.child = null;
    }

    public AssignlhsAST(SymbolTable ST, Node child) {
        super(ST);
        this.ident = null;
        this.child = child;
    }


    @Override
    public void check() {
        if (ident != null) {
            //lhs is an ident
            IDENTIFIER N = ST.lookUp(ident);

            if (N == null) {
                //ident is not in symbol table
                System.err.println("undefined variable");
            } else {
                this.identObj = N;
            }
        } else {
            child.check();
        }
    }
}
