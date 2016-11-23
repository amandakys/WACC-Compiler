package front_end.AST.TypeAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.Mov;
import back_end.instruction.load_store.Load;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.TYPE;

/**
 * Created by andikoh on 09/11/2016.
 */
public class ArraytypeAST extends TypeAST {
    private TypeAST type;
    private int arrayDepth;
    private static int count = 0;

    public ArraytypeAST(ParserRuleContext ctx, TypeAST type, int arrayDepth){
        super(ctx);
        this.type = type;
        this.arrayDepth = arrayDepth;
    }
    @Override
    public void check() {
        identObj = new ARRAY(type.getType(), arrayDepth);
    }

    @Override
    public void translate() {
        CodeGen.main.add(new Branch("L", "malloc"));
        CodeGen.main.add(new Mov(Utility.popUnusedReg(), Register.R0));
    }

    public TYPE getelementType() {
        return type.getType();
    }

    public int getArrayDepth() {
        return arrayDepth;
    }
}
