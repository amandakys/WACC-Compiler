package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class NewpairAST extends AssignrhsAST {
    List<ExpressionAST> pairelems;

    public NewpairAST(ParserRuleContext ctx, List<ExpressionAST> pairelems) {
        super(ctx);
        this.pairelems = pairelems;
    }

    @Override
    public void check() {
        for(Node p : pairelems) {
            p.checkNode();
        }

        identObj = new PAIR(pairelems.get(0).getType(), pairelems.get(1).getType());
    }
}
