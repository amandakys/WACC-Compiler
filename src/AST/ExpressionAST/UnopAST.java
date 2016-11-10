package AST.ExpressionAST;

import java.util.List;

/**
 * Created by andikoh on 10/11/2016.
 */
public class UnopAST extends ExpressionAST {
    ExpressionAST expression;
    String unop;
    public UnopAST(List<ExpressionAST> expressions, String unop) {
        this.expression = expressions.get(0);
        this.unop = unop;
    }
    @Override
    public void check() {

    }
}
