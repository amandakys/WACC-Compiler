package AST.ExpressionAST;

import main.Visitor;

import java.util.List;

/**
 * Created by npd215 on 09/11/16.
 */
public class BoolliterAST extends ExpressionAST{
    String boolVal;

    public BoolliterAST(String boolVal) {
        this.boolVal = boolVal;
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll("bool");
    }
}
