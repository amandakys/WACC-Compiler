package AST;

import antlr.BasicParser;
import org.antlr.v4.runtime.tree.TerminalNode;
import symbol_table.IDENTIFIER;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class ExpressionAST extends Node {

    public ExpressionAST(SymbolTable ST, String ident) {
        super(ST);
        IDENTIFIER N = ST.lookUp(ident);
        identObj = N;

    }

    public ExpressionAST(SymbolTable ST, Node child) {
        super(ST);
    }

    @Override
    public void check() {

    }
}
