package front_end.AST.TypeAST;

import back_end.data_type.Register;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.TYPE;

import java.util.Stack;

/**
 * Created by andikoh on 09/11/2016.
 */
public class ArraytypeAST extends TypeAST {
    TypeAST type;
    int arrayDepth;
    public ArraytypeAST(ParserRuleContext ctx, TypeAST type, int arrayDepth){
        super(ctx);
        this.type = type;
        this.arrayDepth = arrayDepth;
    }
    @Override
    public void check() {

        identObj = new ARRAY(type.getType(), 0);
    }

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {

    }

    public TYPE getelementType() {
        return type.getType();
    }
}
