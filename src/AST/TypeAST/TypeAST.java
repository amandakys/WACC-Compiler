package AST.TypeAST;

import AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by npd215 on 09/11/16.
 */
public abstract class TypeAST extends Node {
    public TypeAST(ParserRuleContext ctx) {
        super(ctx);
    }
}
