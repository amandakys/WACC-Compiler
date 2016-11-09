package AST;

import symbol_table.FUNCTION;
import symbol_table.PARAM;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ArglistAST extends Node {
    List<ExpressionAST> expressions;

    public ArglistAST(List<ExpressionAST> expressionNodes) {
        super();
        expressions = expressionNodes;
    }

    public List<ExpressionAST> getExpressions() {
        return expressions;
    }

    public void check(ParamlistAST params) {
        for (int i = 0; i < params.parameters.size(); i++) {
            expressions.get(i).checkType(params.parameters.get(i));
        }
    }

    @Override
    public void check() {
        for(Node e : expressions) {
            e.check();
        }
    }
}
