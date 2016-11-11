package AST.AssignmentAST;

import AST.Node;
import AST.Utility;
import main.Visitor;
import symbol_table.IDENTIFIER;

/*
    AssignlhsAST represents an AST Node which points to a its value as an identifier,
    pair element or an array element
 */
public class AssignlhsAST extends Node {

    String ident;
    AssignlhsAST child;

    public AssignlhsAST(String ident) {
        super();
        this.ident = ident;
        this.child = null;
    }

    @Override
    public void check() {
        if (ident != null) {
            //lhs is an ident
            IDENTIFIER N = Visitor.ST.lookUpAll(ident);

            if (N == null) {
                //ident is not in symbol table
                Utility.error("undefined variable");
            } else {
                this.identObj = N;
            }
        } else {
            child.check();
            this.identObj = child.getType();
        }
    }
}
