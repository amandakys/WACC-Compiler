package AST.AssignmentAST;

import AST.Node;
import symbol_table.SymbolTable;

/**
 * Created by andikoh on 08/11/2016.
 */
public class AssignrhsAST extends Node {
    Node child;

    public AssignrhsAST(Node child) {
        super();
        this.child = child;
    }

    @Override
    public void check() {
        child.check();
    }
}
