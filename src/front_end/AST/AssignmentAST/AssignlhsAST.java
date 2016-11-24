package front_end.AST.AssignmentAST;

import front_end.AST.Node;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;

/**
 * Created by andikoh on 08/11/2016.
 */
public class AssignlhsAST extends Node {
    private String ident;
    private Node child;

    public AssignlhsAST(ParserRuleContext ctx, String ident) {
        super(ctx);
        this.ident = ident;
        this.child = null;
    }

    public AssignlhsAST(ParserRuleContext ctx, Node child) {
        super(ctx);
        this.ident = null;
        this.child = child;
    }

    @Override
    public void check() {
        if (ident != null) {
            //lhs is an ident
            IDENTIFIER N = Visitor.ST.lookUpAll(ident);

            if (N == null) {
                //ident is not in symbol table
                error("undefined variable");
            } else {
                this.identObj = N;
            }
        } else {
            child.checkNode();
            this.identObj = child.getType();
        }
    }

    @Override
    public void translate() {
        if (child != null) {
            child.translate();
        }
    }

    public Node getChild() {
        return child;
    }

    public String getIdent() {
        return ident;
    }
}
