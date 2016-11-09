package AST;

import symbol_table.FUNCTION;
import symbol_table.PARAM;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ArglistAST extends Node {
    List<Node> expressions;

    public ArglistAST(SymbolTable ST, List<Node> expressionNodes) {
        super(ST);
        expressions = expressionNodes;
    }

    public List<Node> getExpressions() {
        return expressions;
    }

    public void check(ParamlistAST params) {
        for (int i = 0; i < params.parameters.size(); i++) {
            if(expressions.get(i).getType() != params.parameters.get(i).getType()) {
                System.err.println("type of func param "+i+" incompatible with declaration");

            }
        }
    }

    @Override
    public void check() {

    }
}
