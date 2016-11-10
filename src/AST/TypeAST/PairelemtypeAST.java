package AST.TypeAST;

import AST.Node;
import symbol_table.PAIR;

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
        identObj = new PAIR(null, null); //loss of type info for nested pairs

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
    }
}
