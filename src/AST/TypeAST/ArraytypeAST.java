package AST.TypeAST;

import AST.Node;
import symbol_table.ARRAY;

/**
 * Created by andikoh on 09/11/2016.
 */
public class ArraytypeAST extends TypeAST {
    TypeAST type;
    int arrayDepth;
    public ArraytypeAST(TypeAST type, int arrayDepth){
        this.type = type;
        this.arrayDepth = arrayDepth;
    }
    @Override
    public void check() {

        identObj = new ARRAY(type.getType(), 0);
    }
}
