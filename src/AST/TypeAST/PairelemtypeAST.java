package AST.TypeAST;

import AST.Node;

/**
 * Created by andikoh on 09/11/2016.
 */
public class PairelemtypeAST extends Node {
    String pairtoken;
    Node type;

    public PairelemtypeAST(String pair) {
        super();
        pairtoken = pair;
        type = null;

    }

    public PairelemtypeAST(Node type) {
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
