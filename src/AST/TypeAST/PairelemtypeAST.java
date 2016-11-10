package AST.TypeAST;

import AST.Node;
import main.Visitor;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;
import symbol_table.TYPE;

/**
 * Created by andikoh on 09/11/2016.
 */
public class PairelemtypeAST extends Node {
    String pairtoken;
    TypeAST type;

    public PairelemtypeAST(String pair) {
        super();
        pairtoken = pair;
        type = null;
    }

    public PairelemtypeAST(TypeAST type) {
        pairtoken = null;
        this.type = type;
    }

    @Override
    public void check() {
        if (type != null) {
            type.check();
        }

        IDENTIFIER T;
        if (pairtoken != null) {
            T = Visitor.ST.lookUpAll("pair"); //nested pair
        } else {
            T = Visitor.ST.lookUpAll(type.getType().getTypeName());
        }

        identObj = T;
    }
}
