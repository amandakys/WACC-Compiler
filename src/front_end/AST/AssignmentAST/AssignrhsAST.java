package front_end.AST.AssignmentAST;

import front_end.AST.Node;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class AssignrhsAST extends Node {
    protected String ident;

    public AssignrhsAST(ParserRuleContext ctx) {
        super(ctx);
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getIdent() {
        return ident;
    }

    public void IRepresentation() {
        IGNode = findIGNode(ident);
    }
}
