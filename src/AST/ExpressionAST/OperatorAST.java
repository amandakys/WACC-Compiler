package AST.ExpressionAST;

import AST.Node;
import main.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donamphuong on 10/11/2016.
 */
public class OperatorAST extends Node {
    private String op;
    private String returnType;
    private List<String> expectedElemType;
    private int precedence;

    public OperatorAST(String op) {
        this.op = op;
        expectedElemType = new ArrayList<>();

        switch(op) {
            case "*":
            case "/":
            case "%":
                precedence = 1;
                returnType = "int";
                expectedElemType.add("int");
                break;
            case "+":
            case "-":
                precedence = 2;
                returnType = "int";
                expectedElemType.add("int");
                break;
            case ">":
            case ">=":
            case "<":
            case "<=":
                precedence = 3;
                returnType = "bool";
                expectedElemType.add("int");
                expectedElemType.add("char");
                break;
            case "==":
            case "!=":
                precedence = 4;
                returnType = "bool";
                expectedElemType.add("int");
                expectedElemType.add("bool");
                expectedElemType.add("string");
                expectedElemType.add("char");
                break;
            case "&&":
                precedence = 5;
                returnType = "bool";
                expectedElemType.add("bool");
                break;
            case "||":
                precedence = 6;
                returnType = "bool";
                expectedElemType.add("bool");
                break;
        }
    }

    public int getPrecedence() {
        return precedence;
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll(returnType);
    }
}
