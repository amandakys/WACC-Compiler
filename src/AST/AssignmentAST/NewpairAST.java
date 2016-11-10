package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;
import main.Visitor;
import symbol_table.IDENTIFIER;
<<<<<<< HEAD
=======
import symbol_table.PAIR;
>>>>>>> semanticsV2

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class NewpairAST extends AssignrhsAST {
    List<ExpressionAST> pairelems;

    public NewpairAST(List<ExpressionAST> pairelems) {
        super();
        this.pairelems = pairelems;
    }

    @Override
    public void check() {
        for(Node p : pairelems) {
            p.check();
        }
        IDENTIFIER type = Visitor.ST.lookUpAll("pair");
        identObj = type;
    }
}
