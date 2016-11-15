package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.ExpressionAST.IdentAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PairelemAST extends AssignrhsAST{
    String token;
    ExpressionAST expression;

    public PairelemAST(ParserRuleContext ctx, String token, ExpressionAST expression) {
        super(ctx);
        this.token = token;
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.checkNode();

        //expression is an ident - referencing a pair
        IDENTIFIER type = Visitor.ST.lookUp(((IdentAST) expression).getIdent());

        if (!(type instanceof PAIR)) {
            error("fst/snd can only take pair types, actual: " + expression.getType().getTypeName());
        } else {
            //expression is a pair

            switch(token) {
                case "fst": identObj = ((PAIR) type).getFirst();
                    break;
                case "snd": identObj = ((PAIR) type).getSecond();
                    break;
            }

        }

    }

}
