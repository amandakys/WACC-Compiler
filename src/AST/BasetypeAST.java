package AST;

import main.Visitor;
import symbol_table.IDENTIFIER;

/**
 * Created by andikoh on 09/11/2016.
 */
public class BasetypeAST extends Node {
    String typename;

    public BasetypeAST(String typename) {
        this.typename = typename;
    }

    @Override
    public void check() {
        //check type is in symbol table;
        IDENTIFIER T = Visitor.ST.lookUpAll(typename);
        if (T == null) {
            //type does not exist
            System.err.println("undefined type");
        }
    }
}
