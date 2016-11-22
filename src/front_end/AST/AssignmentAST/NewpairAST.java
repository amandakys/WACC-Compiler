package front_end.AST.AssignmentAST;

import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

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
//        IDENTIFIER type = Visitor.ST.lookUpAll("pair");
//        identObj = type;
        identObj = new PAIR(pairelems.get(0).getType(), pairelems.get(1).getType());
    }

    @Override
    public void translate() {

    }
}
