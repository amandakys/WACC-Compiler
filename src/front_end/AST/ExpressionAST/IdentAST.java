package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;

public class IdentAST extends ExpressionAST {
    String ident; //name

    public IdentAST(ParserRuleContext ctx, String ident) {
        super(ctx);
        this.ident = ident;
        IDENTIFIER identType = Visitor.ST.lookUpAll(ident); //Type

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

        Register result = Utility.popUnusedReg();
        String typeName = identObj.getType().getTypeName();

        if(typeName.equals("bool") || typeName.equals("char")) {
            //LDRSB only for signed byte ie bool & char
            CodeGen.main.add(new LOAD("SB", result, Visitor.ST.getAddress(ident)));
        } else {
            //normal LDR
            CodeGen.main.add(new LOAD(result, Visitor.ST.getAddress(ident)));
        }
    }

    public String getIdent() {
        return this.ident;
    }
}
