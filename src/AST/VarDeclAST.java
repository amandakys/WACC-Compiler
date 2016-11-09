package AST;

import main.Visitor;
import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;
import symbol_table.TYPE;
import symbol_table.VARIABLE;

/**
 * Created by dtv15 on 09/11/16.
 */

public class VarDeclAST extends Node {
    private String ident;
    private Node type;
    private Node rhs;

    public VarDeclAST(Node type, String ident, Node rhs) {
        super();
        this.ident = ident;
        this.type = type;
        this.rhs = rhs;
    }

    @Override
    public void check() {
        type.check();
        String typeName = type.getType().getTypeName();
        IDENTIFIER T = Visitor.ST.lookUpAll(typeName);
        IDENTIFIER V = Visitor.ST.lookUp(ident);
        if(T == null) {
            System.err.println("unknown type " + typeName);
        } else if (!(T instanceof TYPE)) {
            System.err.println(typeName + " is not a type");
        } else if (!(T.isDeclarable())) {
            System.err.println("cannot declare " + typeName + " objects");
        } else if (!(V == null)) {
            System.err.println(ident + " is already declared");
        } else {
            VARIABLE varObj = new VARIABLE((TYPE) T);
            Visitor.ST.add(ident, varObj);
            //Checking rhs
            rhs.check();
            type.checkType(rhs);
        }
    }
}
