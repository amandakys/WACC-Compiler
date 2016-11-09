package AST;

import antlr.BasicParser;
import main.Visitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import symbol_table.SymbolTable;

import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class IntLiterAST extends Node {

    Node child; //intsign

    public IntLiterAST( Node intsign, List<TerminalNode> digit) {
        super();

        identObj = Visitor.ST.lookUpAll("int");
    }

    @Override
    public void check() {
        child.check();
        //no checks needed

    }
}
