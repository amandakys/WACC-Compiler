package front_end.AST.TypeAST;

import back_end.Utility;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.TYPE;

public class ArraytypeAST extends TypeAST {
    private TypeAST type;
    private int arrayDepth;

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

    }

    @Override
    public void weight() {
        type.weight();
        size = type.getSize();
    }

    @Override
    public void IRepresentation() {

    }

    public TYPE getelementType() {
        return type.getType();
    }

    public int getArrayDepth() {
        return arrayDepth;
    }
}
