package AST.FunctionDecl;

import AST.Node;
import AST.TypeAST.TypeAST;
import main.Visitor;
import symbol_table.*;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ParamAST extends Node {
    private TypeAST type;
    private String ident;

    public ParamAST(TypeAST type, String ident) {
        super();
        this.type = type;
        this.ident = ident;
        IDENTIFIER paramType;
        if (type.getType() instanceof ARRAY) {
            paramType = new PARAM(new ARRAY(((ARRAY) type.getType()).getElementType(), 0));
        } else if (type.getType() instanceof PAIR) {
            paramType = new PARAM(new PAIR(((PAIR) type.getType()).getFirst(), ((PAIR) type.getType()).getSecond()));

        } else {
            IDENTIFIER T = Visitor.ST.lookUpAll(type.getType().getTypeName());
            paramType = new PARAM((TYPE) T);
            //paramType = T;
        }

        identObj = paramType;
    }

    @Override
    public void check() {

    }

    public String getIdent() {
        return this.ident;
    }
}
