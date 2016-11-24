package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.condition.CMP;
import front_end.AST.ExpressionAST.ExpressionAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public class WhileAST extends StatementAST {
    ExpressionAST expression;
    StatementAST statement;

    public WhileAST(ParserRuleContext ctx, ExpressionAST expression, StatementAST statement) {
        super(ctx);
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public void check() {
        //check that expression is valid
        expression.checkNode();

        if (expression.getType().equals(Visitor.ST.lookUpAll("bool"))) {
            //check that statement is valid
            statement.checkNode();
        } else {
            error("expression is not of type boolean");
        }
    }

    @Override
    public void translate() {

        String conditionLabel = labelCount.toString();
        CodeGen.main.add(new Branch("", "L" + conditionLabel));
        labelCount++;
        String whileBodyLabel = labelCount.toString();
        CodeGen.main.add(new LabelInstr("L" + whileBodyLabel));
        Register result = CodeGen.notUsedRegisters.peek();
        statement.translate();
        Utility.pushBackRegisters();
        CodeGen.notUsedRegisters.push(result);
        CodeGen.main.add(new LabelInstr("L" + conditionLabel));

        result = CodeGen.notUsedRegisters.peek();
        expression.translate();
        Utility.pushBackRegisters();

        CodeGen.main.add(new CMP(result, new ImmValue(1)));
        CodeGen.notUsedRegisters.push(result);
        CodeGen.main.add(new Branch("EQ", "L" + whileBodyLabel));
    }
}