package AST;

import main.Visitor;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ReadAST extends Node {
    Node expression;

    public ReadAST(Node expression) {
        super();
        this.expression = expression;
    }
    @Override
    public void check() {
        expression.check();
        TYPE t = expression.getType(); //check that expresison is of a type acceptable to read

        TYPE intType = Visitor.ST.lookupAll("int").getType();

        TYPE charType = Visitor.ST.lookUpAll("char").getType();

        if (t != intType && t != charType) {
            //error
        }


    }

}
