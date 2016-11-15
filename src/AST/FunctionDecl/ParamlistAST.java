package AST.FunctionDecl;

import AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.TYPE;

import java.util.ArrayList;
import java.util.List;

public class ParamlistAST extends Node {
    List<ParamAST> parameters;

    public ParamlistAST(ParserRuleContext ctx, List<ParamAST> parameters) {
        super(ctx);
        this.parameters = parameters;
    }

    public List<TYPE> getParamTypes() {
        List<TYPE> paramTypes = new ArrayList<>();
        for(ParamAST n : parameters) {
            paramTypes.add(n.getType());
        }

        return paramTypes;
    }

    public List<ParamAST> getParams() {
        return parameters;
    }

    @Override
    public void check() {
        for(ParamAST n : parameters) {
            n.check();
        }
    }
}
