package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class NewpairAST extends Node {
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
    }
}
