package AST.ExpressionAST;

import main.Visitor;

import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class StringLiterAST extends ExpressionAST{
    private String value;

    public StringLiterAST(String value) {
        this.value = value;
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll("string");
    }
}
