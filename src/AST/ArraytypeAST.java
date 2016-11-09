package AST;

/**
 * Created by andikoh on 09/11/2016.
 */
public class ArraytypeAST extends Node {
    Node type;
    int arrayDepth;
    public ArraytypeAST(Node type, int arrayDepth){
        this.type = type;
        this.arrayDepth = arrayDepth;
    }
    @Override
    public void check() {

    }
}
