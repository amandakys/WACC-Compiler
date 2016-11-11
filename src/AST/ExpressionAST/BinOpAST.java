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

    private ExpressionAST rhs;
    private ExpressionAST lhs;

    public BinOpAST(String op, ExpressionAST rhs, ExpressionAST lhs) {
        this.op = op;
        this.rhs = rhs;
        this.lhs = lhs;
        initialise();
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll(returnType);

        lhs.check();
        rhs.check();

        String firstType = lhs.getType().getTypeName();

        if(expectedElemType.contains(firstType)) {
            if(!rhs.getType().equals(lhs.getType())) {
                Utility.error("not the same type");
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
                expectedElemType.add("pair");
                returnType = "bool";
            case "&&":
            case "||":
                expectedElemType.add("bool");
                returnType = "bool";
                break;
        }
    }
}
