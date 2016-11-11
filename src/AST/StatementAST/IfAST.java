package AST.StatementAST;

import AST.ExpressionAST.ExpressionAST;
import AST.Utility;
import main.Visitor;
import symbol_table.IDENTIFIER;

/**
 * Created by andikoh on 10/11/2016.
 */
public class IfAST extends StatementAST {
    ExpressionAST expression;
    StatementAST then;
    StatementAST elseSt;
    public IfAST(ExpressionAST expr, StatementAST then, StatementAST elseSt) {
        this.expression = expr;
        this.then = then;
        this.elseSt = elseSt;
    }

    @Override
    public void check() {
        //check expressions is valid
        expression.check();
        IDENTIFIER T = Visitor.ST.lookUpAll("bool");
        if (!expression.getType().equals(T)) {
            Utility.error("If condition type mismatch");
        }
        //check statements are valid
        then.check();
        elseSt.check();
    }
}
