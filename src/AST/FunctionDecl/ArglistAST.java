package AST.FunctionDecl;

import AST.ExpressionAST.ExpressionAST;
import AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.TYPE;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ArglistAST extends Node {
    private List<ExpressionAST> expressions;

    public ArglistAST(ParserRuleContext ctx, List<ExpressionAST> expressionNodes) {
        super(ctx);
        expressions = expressionNodes;
    }

    public List<ExpressionAST> getExpressions() {
        return expressions;
    }

    /* getType takes an index, and return the type of the expression at that index in
    * List<ExpressionAST> expressions
    */
    public TYPE getType(int x) {
        return expressions.get(x).getType();
    }

    public ExpressionAST getExpression(int i) {
        return expressions.get(i);
    }

    public int size() {
        return expressions.size();
    }

    @Override
    public void check() {
        for(Node e : expressions) {
            e.check();
        }
    }
}
