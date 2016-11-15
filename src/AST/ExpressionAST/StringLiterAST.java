package AST.ExpressionAST;

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
        identObj = Visitor.ST.lookUpAll("string");
    }
}
