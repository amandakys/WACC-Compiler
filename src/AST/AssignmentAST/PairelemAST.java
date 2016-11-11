package AST.AssignmentAST;

import AST.ExpressionAST.ExpressionAST;
import AST.ExpressionAST.IdentAST;
import AST.Utility;
import main.Visitor;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PairelemAST extends AssignrhsAST{
    String token;
    ExpressionAST expression;

    public PairelemAST(String token, ExpressionAST expression) {
        super();
        this.token = token;
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();

        //expression is an ident - referencing a pair
        IDENTIFIER pair = Visitor.ST.lookUp(((IdentAST) expression).getIdent());

        if (!expression.getType().equals(pair.getType())) {
            Utility.error("fst/snd can only take pair types, actual: " + expression.getType().getTypeName());
        } else {
            //expression is a pair

            switch(token) {
                case "fst": identObj = ((PAIR) pair).getFirst();
                    break;
                case "snd": identObj = ((PAIR) pair).getSecond();
                    break;
            }

        }

    }

}
