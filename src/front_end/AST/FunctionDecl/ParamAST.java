package front_end.AST.FunctionDecl;

import back_end.data_type.Register;
import front_end.AST.Node;
import front_end.AST.TypeAST.TypeAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.*;

import java.util.Stack;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ParamAST extends Node {
    private TypeAST type;
    private String ident;

    public ParamAST(ParserRuleContext ctx, TypeAST type, String ident) {
        super(ctx);
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

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {

    }

    public String getIdent() {
        return this.ident;
    }
}
