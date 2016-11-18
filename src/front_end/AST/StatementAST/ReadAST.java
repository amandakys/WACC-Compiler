package front_end.AST.StatementAST;

import back_end.instruction.Instruction;
import front_end.AST.AssignmentAST.AssignlhsAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.TYPE;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class ReadAST extends StatementAST {
    private AssignlhsAST expression;

    public ReadAST(ParserRuleContext ctx, AssignlhsAST expression) {
        super(ctx);
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
            error("read only takes int or char types");
        }


    }

    @Override
    public void translate() {

    }

}
