package AST.ExpressionAST;

import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.swing.text.html.parser.Parser;
import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class CharLitAST extends ExpressionAST {
    String charac;

    public CharLitAST(ParserRuleContext ctx, String charac) {
        super(ctx);
        this.charac = charac;
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll("char");
    }
}
