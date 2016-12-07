package front_end.AST.StatementAST;

import front_end.AST.Node;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class StatementAST extends Node {
    protected static Integer labelCount = 0;

    public StatementAST(ParserRuleContext ctx) {
        super(ctx);
    }
}
