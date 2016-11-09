package AST;

import symbol_table.PARAM;
import symbol_table.SymbolTable;
import symbol_table.TYPE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ParamlistAST extends Node {
    List<Node> parameters;

    public ParamlistAST(List<Node> parameters) {
        super();
        this.parameters = parameters;
    }

    public List<Node> getExpressions() {
        return parameters;
    }

    /*public void check(ParamlistAST params) {
        for (int i = 0; i < params.parameters.size(); i++) {
            if(parameters.get(i).getType() != params.parameters.get(i).getType()) {
                System.err.println("type of func param "+i+" incompatible with declaration");
            }
        }
    }*/

    public List<TYPE> getParamTypes() {
        List<TYPE> paramTypes = new ArrayList<>();
        for(Node n : parameters) {
            paramTypes.add(n.getType());
        }

        return paramTypes;
    }

    @Override
    public void check() {
        for(Node n : parameters) {
            n.check();
        }
    }
}
