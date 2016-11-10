package AST.ExpressionAST;

import AST.Utility;

import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class BinOpAST extends ExpressionAST {
    private OperatorAST op;
    private List<ExpressionAST> expr;

    public BinOpAST(String operator, List<ExpressionAST> expr) {
        this.op = new OperatorAST(operator);
        this.expr = expr;
    }

    @Override
    public void check() {
        identObj = op.getType();

        for(int i = 0; i < expr.size(); i++) {
            if(!expr.get(i).getType().equals(expr.get(i+1).getType())) {
                Utility.error("not the same size");
            } else if(!expr.get(i).getType().equals(getType())) {
                Utility.error(expr.get(i).toString() + " " + "must be of type " + getType());
            }
        }
    }
}
