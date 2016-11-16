package AST.TypeAST;

import AST.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.ARRAY;
import symbol_table.TYPE;

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

    public TYPE getelementType() {
        return type.getType();
    }
}
