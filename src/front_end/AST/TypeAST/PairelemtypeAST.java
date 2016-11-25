package front_end.AST.TypeAST;

import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.load_store.STORE;
import front_end.AST.AssignmentAST.AssignlhsAST;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import front_end.symbol_table.TYPE;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.PAIR;

public class PairelemtypeAST extends Node {
    private String pairtoken;
    private TypeAST type;

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
            T = new ARRAY(((ArraytypeAST) type).getelementType(), ((ArraytypeAST) type).getArrayDepth());
        } else {
            T = Visitor.ST.lookUpAll(type.getType().getTypeName());
        }

        identObj = T;
    }

    @Override
    public void translate() {
        Register r = CodeGen.notUsedRegisters.peek();
        if(type == null) {
            CodeGen.main.add(new STORE(Utility.popUnusedReg(), new Address(Register.R0), identObj.getSize()));
            CodeGen.main.add(new STORE(Register.R0, new PreIndex(r, new ImmValue(identObj.getSize())),
                    ProgramAST.nextAddress));
        } else {
            type.translate();
        }
    }
}
