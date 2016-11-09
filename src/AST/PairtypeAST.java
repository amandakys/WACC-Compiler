package AST;

/**
 * Created by andikoh on 09/11/2016.
 */
public class PairtypeAST extends TypeAST {
    Node first;
    Node second;

    public PairtypeAST(Node first, Node second) {
        super();
        this.first = first;
        this.second = second;
    }

    @Override
    public void check() {
        first.check();
        second.check();
        first.checkType(second);
    }
}
