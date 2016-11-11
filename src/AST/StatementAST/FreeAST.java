package AST.StatementAST;

import AST.AssignmentAST.ArraylitAST;
import AST.ExpressionAST.ArrayelemAST;
import AST.ExpressionAST.ExpressionAST;
import AST.Utility;
import main.Visitor;
import symbol_table.ARRAY;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;
import symbol_table.TYPE;

import javax.rmi.CORBA.Util;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FreeAST extends StatementAST {
    ExpressionAST expression;

    public FreeAST(ExpressionAST expression) {
        super();
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.check();

        if (!(expression.getType() instanceof PAIR)) {
            Utility.error("free must take a pair type");
        }
//        //TYPE type = expression.getType();
//        IDENTIFIER pairtype = Visitor.ST.lookUpAll("pair");
//        //TODO: test for arrayelem case
//        if (!(expression instanceof ArrayelemAST) && !expression.getType().equals(pairtype.getType())) {
//            Utility.error("free must take an array or pair type");
//        }

    }
}
