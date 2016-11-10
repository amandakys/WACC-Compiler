package AST.FunctionDecl;

import AST.Node;
import main.Visitor;
import symbol_table.PARAM;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ParamAST extends Node {
    String type;
    String ident;

    public ParamAST(String type, String ident) {
        super();
        this.type = type;
        this.ident = ident;
    }

    @Override
    public void check() {
        identObj = new PARAM((TYPE) Visitor.ST.lookUpAll(type));
    }
}
