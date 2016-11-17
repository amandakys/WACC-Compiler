package AST.TypeAST;

import AST.Node;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.ARRAY;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;
import symbol_table.TYPE;

/**
 * Created by andikoh on 09/11/2016.
 */
public class PairelemtypeAST extends Node {
    String pairtoken;
    TypeAST type;

    public PairelemtypeAST(ParserRuleContext ctx, String pair) {
        super(ctx);
        pairtoken = pair;
        type = null;
    }

    public PairelemtypeAST(ParserRuleContext ctx, TypeAST type) {
        super(ctx);
        pairtoken = null;
        this.type = type;
    }

    @Override
    public void check() {
        if (type != null) {
            type.checkNode();
        }

        IDENTIFIER T;
        if (pairtoken != null) {
            //nested pair
            T = new PAIR(null, null);
            //TODO: do we need to add nested pair to symbol table
        } else if(type instanceof ArraytypeAST) {
            T = new ARRAY(((ArraytypeAST) type).getelementType(), ((ArraytypeAST) type).arrayDepth);
        } else {
            T = Visitor.ST.lookUpAll(type.getType().getTypeName());
        }

        identObj = T;
    }
}
