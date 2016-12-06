package front_end.AST.FunctionDecl;

import front_end.AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.TYPE;

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
        for(ParamAST p : parameters) {
            p.check();
        }
    }

    @Override
    public void translate() {
        for (ParamAST p: parameters) {
            p.translate();
        }
    }

    @Override
    public void weight() {
        for (ParamAST p : parameters) {
            p.weight();
            size += p.getSize();
        }
    }

    @Override
    public void IRepresentation() {

    }
}
