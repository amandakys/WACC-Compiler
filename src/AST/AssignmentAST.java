package AST;

import symbol_table.SymbolTable;

/**
 * Created by andikoh on 08/11/2016.
 */
public class AssignmentAST extends Node {
    Node lhs;
    Node rhs;

    public AssignmentAST(SymbolTable ST, Node lhs, Node rhs) {
        super(ST);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void check() {
        lhs.check();
        rhs.check();
        lhs.checkType(rhs);
    }
}
