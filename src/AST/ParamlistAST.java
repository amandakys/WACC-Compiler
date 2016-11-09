package AST;

import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ParamlistAST extends Node {
    List<Node> parameters;

    public ParamlistAST(SymbolTable ST, List<Node> parameters) {
        super(ST);
        this.parameters = parameters;
    }

    public List<Node> getExpressions() {
        return parameters;
    }

    public void check(ParamlistAST params) {
        for (int i = 0; i < params.parameters.size(); i++) {
            if(parameters.get(i).getType() != params.parameters.get(i).getType()) {
                System.err.println("type of func param "+i+" incompatible with declaration");
            }
        }
    }

    @Override
    public void check() {

    }
}
