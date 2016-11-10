package AST.ExpressionAST;

import main.Visitor;

import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class CharLit extends ExpressionAST {
    String charac;

    public CharLit(String charac) {
        this.charac = charac;
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll("char");
    }
}
