package AST.StatementAST;

import AST.AssignmentAST.AssignlhsAST;
import AST.AssignmentAST.AssignmentAST;
import AST.Utility;
import main.Visitor;
import symbol_table.TYPE;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ReadAST extends StatementAST {
    private AssignlhsAST expression;

    public ReadAST(AssignlhsAST expression) {
        super();
        this.expression = expression;
    }
    @Override
    public void check() {
        expression.check();
        TYPE t = expression.getType(); //check that expresison is of a type acceptable to read

        TYPE intType = Visitor.ST.lookUpAll("int").getType();

        TYPE charType = Visitor.ST.lookUpAll("char").getType();

        if (!t.equals(intType) && !t.equals(charType)){
            //error
            Utility.error("read only takes int or char types");
        }


    }

}
