package AST.ExpressionAST;

import main.Visitor;

import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class CharLitAST extends ExpressionAST {
    String charac;

    public CharLitAST(String charac) {
        this.charac = charac;
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll("char");
    }
}
