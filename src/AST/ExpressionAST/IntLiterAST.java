package AST.ExpressionAST;

import main.Visitor;

/**
 * Created by andikoh on 08/11/2016.
 */
public class IntLiterAST extends ExpressionAST {

    String intsign; //intsign
    String value;

    public IntLiterAST(String intsign, String value) {
        this.value = value;
        this.intsign = intsign;
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll("int");
    }
}
