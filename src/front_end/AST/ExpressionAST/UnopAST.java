package front_end.AST.ExpressionAST;

import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public class UnopAST extends ExpressionAST {
    private String expectedElemType;
    private String returnType;

    private ExpressionAST expression;
    private String unop;

    public UnopAST(ParserRuleContext ctx, ExpressionAST expression, String unop) {
        super(ctx);
        this.expression = expression;
        this.unop = unop;
        initialise();
    }
    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll(returnType);
        if (!expectedElemType.equals(expression.getType().getTypeName())) {
            error(unop +" received an unexpected type\nexpects: " + expectedElemType + "\nactual: "+ expression.getType().getTypeName());
        }
    }

    @Override
    public void translate() {
    }

    private void initialise() {
        switch(unop) {
            case "!": returnType = "bool";
                expectedElemType = "bool";
                break;
            case "-": returnType = "int";
                expectedElemType = "int";
                break;
            case "len": returnType = "int";
                expectedElemType = "array";
                break;
            case "ord": returnType = "int";
                expectedElemType = "char";
                break;
            case "chr": returnType = "char";
                expectedElemType = "int";
                break;

        }
    }
}
