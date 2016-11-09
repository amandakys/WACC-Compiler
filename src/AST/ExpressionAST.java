package AST;

import antlr.BasicParser;
import main.Visitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class ExpressionAST extends Node {
    Node baseExpression;
    List<Node> expressions;
    public ExpressionAST(String ident) {
        super();
        IDENTIFIER N = Visitor.ST.lookUp(ident);
        identObj = N;
        this.baseExpression = null;
        this.expressions = null;

    }

    public ExpressionAST(Node child) {
        super();
        baseExpression = child;
        expressions = null;
    }

    public ExpressionAST(List<Node> expressions) {
        super();
        this.expressions = expressions;
        this.baseExpression = null;

    }

    @Override
    public void check() {

    }
}
