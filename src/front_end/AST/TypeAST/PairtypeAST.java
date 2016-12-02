package front_end.AST.TypeAST;

import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;
import front_end.symbol_table.TYPE;

public class PairtypeAST extends TypeAST {
    private PairelemtypeAST first;
    private PairelemtypeAST second;

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

    @Override
    public void translate() {
    }

    public TYPE typeFirst() {
        return first.getType();
    }

    public TYPE typeSecond() {
        return second.getType();
    }

}