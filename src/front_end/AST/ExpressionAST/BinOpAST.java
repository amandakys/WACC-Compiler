package front_end.AST.ExpressionAST;

import back_end.data_type.Register;
import back_end.instruction.condition.AND;
import back_end.instruction.condition.CMP;
import back_end.instruction.condition.ORR;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

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

    public BinOpAST(ParserRuleContext ctx, String op, ExpressionAST lhs, ExpressionAST rhs) {
        super(ctx);
        this.op = op;
        this.rhs = rhs;
        this.lhs = lhs;
        initialise();
    }

    @Override
    public void check() {
        identObj = Visitor.ST.lookUpAll(returnType);

        lhs.checkNode();
        rhs.checkNode();

        String firstType = lhs.getType().getTypeName();

        if(expectedElemType.contains(firstType)) {
            if(!rhs.getType().getTypeName().equals(lhs.getType().getTypeName())) {
                error("not the same type");
            }
        } else {
            error("not expected type");
        }
    }

    @Override
    public void translate() {
        Register lhsResult = CodeGen.notUsedRegisters.peek();
        lhs.translate();
        Register rhsResult = CodeGen.notUsedRegisters.peek();
        rhs.translate();
        switch(op) {
            case "*":
            case "/":
            case "%":
            case "+":
                CodeGen.main.add(new ADD(lhsResult, lhsResult, rhsResult));
            case "-":
                CodeGen.main.add(new SUB(lhsResult, lhsResult, rhsResult));
                //TODO: make function in Utility to throw overflow error
            case ">":
            case ">=":
            case "<":
            case "<=":
            case "==":
            case "!=":
                CodeGen.main.add(new CMP(lhsResult, rhsResult));
                break;
            case "&&":
                CodeGen.main.add(new AND(lhsResult, lhsResult, rhsResult));
                break;
            case "||":
                CodeGen.main.add(new ORR(lhsResult, lhsResult, rhsResult));
                break;
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
