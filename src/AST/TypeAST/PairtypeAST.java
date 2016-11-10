package AST.TypeAST;

import AST.Node;
import main.Visitor;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;

/**
 * Created by andikoh on 09/11/2016.
 */
public class PairtypeAST extends TypeAST {
    PairelemtypeAST first;
    PairelemtypeAST second;

    public PairtypeAST(PairelemtypeAST first, PairelemtypeAST second) {
        super();
        this.first = first;
        this.second = second;
    }

    @Override
    public void check() {
        first.check();
        second.check();

        IDENTIFIER T = Visitor.ST.lookUpAll("pair");
        identObj = T;
    }


}
