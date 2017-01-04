package front_end.AST.AssignmentAST;

import antlr.BasicParser;
import front_end.AST.Node;
import main.Visitor;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;

public class AssignlhsAST extends Node {
    private String ident;
    private Node child;

    //case of an Ident
    public AssignlhsAST(ParserRuleContext ctx, String ident) {
        super(ctx);
        this.ident = ident;
        this.child = null;
    }

    //case of a Node
    public AssignlhsAST(ParserRuleContext ctx, Node child) {
        super(ctx);
        this.child = child;

        if(child instanceof ArrayelemAST) {
            this.ident = ((ArrayelemAST) child).getIdent();
        } else if(child instanceof PairelemAST) {
            this.ident = ((PairelemAST) child).getIdent();
        }
    }

    @Override
    public void check() {
        if (child != null) {
            //child is not null so must be a Node
            child.checkNode();
            this.identObj = child.getType();
        } else {
            //lhs is an ident
            IDENTIFIER N = Visitor.ST.lookUpAll(ident);

            if (N == null) {
                //ident is not in symbol table
                error("undefined variable");
            } else {
                this.identObj = N;
            }
        }
    }

    @Override
    public void translate() {
        if (child != null) {
            child.translate();
        }
    }

    @Override
    public void weight() {
        child.weight();
        size = child.getSize();
    }

    @Override
    public void IRepresentation() {
        if(child != null) {
            child.IRepresentation();
            IGNode = child.getIGNode();
        } else {
            IGNode = InterferenceGraph.findIGNode(ident);

            if(IGNode != null && IGNode.getTo() < index) {
                IGNode.setTo(index - 1);
            }
        }
    }

    public Node getChild() {
        return child;
    }

    public String getIdent() {
        return ident;
    }

    @Override
    public void setIGNode(IGNode node) {
        IGNode = node;
        if(child != null) {
            child.setIGNode(node);
        }
    }
}
