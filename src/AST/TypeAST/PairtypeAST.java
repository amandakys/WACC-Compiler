package AST.TypeAST;

import AST.Node;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;
import symbol_table.TYPE;

/**
 * Created by andikoh on 09/11/2016.
 */
public class PairtypeAST extends TypeAST {
    PairelemtypeAST first;
    PairelemtypeAST second;

    public PairtypeAST(ParserRuleContext ctx, PairelemtypeAST first, PairelemtypeAST second) {
        super(ctx);
        this.first = first;
        this.second = second;
    }

    @Override
    public void check() {
        first.check();
        second.check();

        identObj = new PAIR(first.getType(), second.getType());

    }

    public TYPE typeFirst() {
        return first.getType();
    }

    public TYPE typeSecond() {
        return second.getType();
    }

}
