package AST.FunctionDecl;

import AST.Node;
import main.Visitor;
import symbol_table.PARAM;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ParamAST extends Node {
    private String type;
    private String ident;

    public ParamAST(String type, String ident) {
        super();
        this.type = type.replaceAll("^[^a-zA-Z0-9\\s]+|[^a-zA-Z0-9\\s]+$", "");
        this.ident = ident;
    }

    @Override
    public void check() {
        identObj = new PARAM((TYPE) Visitor.ST.lookUpAll(type));
    }

    public String getIdent() {
        return this.ident;
    }
}
