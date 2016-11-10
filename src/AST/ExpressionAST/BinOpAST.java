package AST.ExpressionAST;

import AST.Utility;
import main.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class BinOpAST extends ExpressionAST {
    private String op;
    private List<String> expectedElemType = new ArrayList<>();
    private String returnType;
    private List<ExpressionAST> expr;

    public BinOpAST(String op, List<ExpressionAST> expr) {
        this.op = op;
        this.expr = expr;
        initialise();
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll(returnType);
        String firstType = expr.get(0).getType().toString();
        if(expectedElemType.contains(firstType)) {
            for(int i = 0; i < expr.size() - 1; i++) {
                if(!expr.get(i).getType().equals(expr.get(i+1).getType())) {
                    Utility.error("not the same type");
                }
            }
        } else {
            Utility.error("not expected type");
        }
    }

    private void initialise() {
        switch (op) {
            case "*":
            case "/":
            case "%":
            case "+":
            case "-":
                expectedElemType.add("int");
                returnType = "int";
                break;
            case ">":
            case ">=":
            case "<":
            case "<=":
                expectedElemType.add("int");
                expectedElemType.add("char");
                returnType = "bool";
                break;
            case "==":
            case "!=":
                expectedElemType.add("bool");
                expectedElemType.add("int");
                expectedElemType.add("char");
                expectedElemType.add("string");
                returnType = "bool";
            case "&&":
            case "||":
                expectedElemType.add("bool");
                returnType = "bool";
                break;
        }
    }
}
