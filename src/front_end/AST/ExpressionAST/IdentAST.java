package front_end.AST.ExpressionAST;

import back_end.data_type.register.Register;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;

/**
 * Created by tsd15 on 10/11/16.
 */
public class IdentAST extends ExpressionAST {
    String ident;

    public IdentAST(ParserRuleContext ctx, String ident) {
        super(ctx);
        this.ident = ident;
        IDENTIFIER identType = Visitor.ST.lookUpAll(ident);

        if (identType == null) {
            error("is an undefined expression");
        } else {
            identObj = identType;
        }
    }

    @Override
    public void check() {
        IDENTIFIER I = Visitor.ST.lookUpAll(ident);
        if(I == null) {
            error("cannot find identifier: "+ ident);
        }
    }

    @Override
    public void translate() {
        Register result = CodeGen.notUsedRegisters.pop();
        if(identObj.getType().getTypeName().equals("bool")) {
            CodeGen.main.add(new LOAD("SB", result, Visitor.ST.getAddress(ident)));
        } else {
            CodeGen.main.add(new LOAD(result, Visitor.ST.getAddress(ident)));
        }
    }

    public String getIdent() {
        return this.ident;
    }
}
