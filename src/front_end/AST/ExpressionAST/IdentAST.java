package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
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
        //check has already been performed inside the constructor
    }

    @Override
    public void translate() {
        String typeName = identObj.getType().getTypeName();

        if(typeName.equals("bool") || typeName.equals("char")) {
            //LDRSB only for signed byte ie bool & char
            CodeGen.main.add(new LOAD("SB", getRegister(), Visitor.ST.getAddress(ident)));
        } else {
            //normal LDR
            CodeGen.main.add(new LOAD(getRegister(), Visitor.ST.getAddress(ident)));
        }
    }

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {
        IGNode = Visitor.ST.findIGNode(ident);

        if(IGNode != null && IGNode.getTo() < index) {
            IGNode.setTo(index - 1);
        }
    }

    public String getIdent() {
        return this.ident;
    }
}
