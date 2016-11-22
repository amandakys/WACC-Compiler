package front_end.AST.StatementAST;

import front_end.symbol_table.IDENTIFIER;

import back_end.data_type.ImmValue;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.condition.CMP;
import back_end.data_type.register.Register;
import front_end.AST.ExpressionAST.ExpressionAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public class IfAST extends StatementAST {
    ExpressionAST expression;
    StatementAST then;
    StatementAST elseSt;

    public IfAST(ParserRuleContext ctx, ExpressionAST expr, StatementAST then, StatementAST elseSt) {
        super(ctx);
        this.expression = expr;
        this.then = then;
        this.elseSt = elseSt;
    }

    @Override
    public void check() {
        //check expressions is valid
        expression.checkNode();
        IDENTIFIER T = Visitor.ST.lookUpAll("bool");
        if (!expression.getType().equals(T)) {
            error("If condition type mismatch");
        }
        //check statements are valid
        then.checkNode();
        elseSt.checkNode();
    }

    @Override
    public void translate() {

        Register result = CodeGen.notUsedRegisters.peek();
        expression.translate();
        //jump to label if false
        CodeGen.main.add(new CMP(CodeGen.notUsedRegisters.peek(), new ImmValue(0)));
        String l0 = CodeGen.labelCount.toString();
        CodeGen.main.add(new Branch("EQ", "L" + l0));
        CodeGen.labelCount ++;
        then.translate();
        String l1 = CodeGen.labelCount.toString();
        CodeGen.main.add(new Branch("", "L" + l1));
        CodeGen.main.add(new LabelInstr("L" + l0));
        elseSt.translate();
        CodeGen.main.add(new LabelInstr("L" + l1));

        //TODO: unsure what to do with result Register
    }
}
