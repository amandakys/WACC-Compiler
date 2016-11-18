package front_end.AST.ExpressionAST;

import back_end.instruction.Instruction;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class StringLiterAST extends ExpressionAST{
    private String value;

    public StringLiterAST(ParserRuleContext ctx, String value) {
        super(ctx);
        this.value = value;
    }

    @Override
    public void check() {
        checkIfInScope(value);
        identObj = Visitor.ST.lookUpAll("string");
    }

    @Override
    public void translate() {

    }
}
