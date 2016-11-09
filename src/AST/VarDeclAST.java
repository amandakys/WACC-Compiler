package AST;

import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;
import symbol_table.TYPE;
import symbol_table.VARIABLE;

/**
 * Created by dtv15 on 09/11/16.
 */
public class VarDeclAST extends Node {
    String ident;
    Node type;
    Node rhs;
    VARIABLE varObj;

    public VarDeclAST(SymbolTable ST, Node type, String ident, Node rhs) {
        super(ST);
        this.ident = ident;
        this.type = type;
        this.rhs = rhs;
    }

    @Override
    public void check() {
        String typeName = type.getType().getTypeName();
        IDENTIFIER T = ST.lookUpAll(typeName);
        IDENTIFIER V = ST.lookUp(ident);
        if(T == null) {
            System.err.println("unknown type " + typeName);
        } else if (!(T instanceof TYPE)) {
            System.err.println(typeName + " is not a type");
        } else if (!(T.isDeclarable())) {
            System.err.println("cannot declare " + typeName + " objects");
        } else if (!(V == null)) {
            System.err.println(ident + " is already declared");
        } else {
            varObj = new VARIABLE((TYPE) T);
            ST.add(ident, varObj);
            //NOT SURE about THESE 2 LAST LINES
            rhs.check();
            type.checkType(rhs);
        }
    }
}
