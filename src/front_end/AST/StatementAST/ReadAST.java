package front_end.AST.StatementAST;

import back_end.data_type.Register;
import front_end.AST.AssignmentAST.AssignlhsAST;
import front_end.symbol_table.TYPE;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

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
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {

    }

}
