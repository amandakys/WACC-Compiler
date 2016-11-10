package AST.ExpressionAST;

import AST.Utility;
import main.Visitor;
import java.util.List;

/**
 * Created by andikoh on 10/11/2016.
 */
public class UnopAST extends ExpressionAST {
    private String expectedElemType;
    private String returnType;

    private ExpressionAST expression;
    private String unop;

    public UnopAST(List<ExpressionAST> expressions, String unop) {
        this.expression = expressions.get(0);
        this.unop = unop;
        initialise();
    }
    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll(returnType);
        if (!expectedElemType.equals(expression.getType().getTypeName())) {
            Utility.error(unop +" received an unexpected type\nexpects: " + expectedElemType + "\nactual: "+ expression.getType().getTypeName());
        }
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
