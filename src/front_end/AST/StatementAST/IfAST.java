package front_end.AST.StatementAST;

import back_end.Utility;
import front_end.AST.ProgramAST;
import front_end.symbol_table.IDENTIFIER;

import back_end.data_type.ImmValue;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.condition.CMP;
import back_end.data_type.register.Register;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.symbol_table.SymbolTable;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public class IfAST extends StatementAST {
    // count for generic label names
    //public static Integer labelCount = 0;
    private ExpressionAST expression;
    private StatementAST then;
    private StatementAST elseSt;

    private SymbolTable thenST;
    private SymbolTable elseST;

    public IfAST(ParserRuleContext ctx, ExpressionAST expr, StatementAST then, StatementAST elseSt, SymbolTable thenST, SymbolTable elseST) {
        super(ctx);
        this.expression = expr;
        this.then = then;
        this.elseSt = elseSt;
        this.thenST = thenST;
        this.elseST = elseST;
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
        CodeGen.main.add(new CMP(result, new ImmValue(0)));
        Utility.pushRegister(result);
        String l0 = labelCount.toString();

        CodeGen.main.add(new Branch("EQ", "L" + l0));
        labelCount ++;
        result = CodeGen.notUsedRegisters.peek();
        Visitor.ST = thenST;
        ProgramAST.newScope(then);
        //then.translate();
        Utility.pushBackRegisters();
        Visitor.ST = Visitor.ST.getEncSymbolTable();
        //CodeGen.notUsedRegisters.push(result);

        String l1 = labelCount.toString();
        CodeGen.main.add(new Branch("", "L" + l1));

        CodeGen.main.add(new LabelInstr("L" + l0));
        result = CodeGen.notUsedRegisters.peek();
        Visitor.ST = elseST;
        ProgramAST.newScope(elseSt);
        //elseSt.translate();
        Utility.pushBackRegisters();
        Visitor.ST = Visitor.ST.getEncSymbolTable();
        //CodeGen.notUsedRegisters.push(result);
        CodeGen.main.add(new LabelInstr("L" + l1));

    }
}
