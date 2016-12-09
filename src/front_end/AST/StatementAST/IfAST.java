package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
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
import optimisation.GraphColour;
import org.antlr.v4.runtime.ParserRuleContext;

import static main.Visitor.ST;

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

        expression.translate();

        //jump to label if false
        CodeGen.main.add(new CMP(expression.getRegister(), new ImmValue(0)));
        String l0 = labelCount.toString();

        CodeGen.main.add(new Branch("EQ", "L" + l0));
        labelCount ++;
        if (thenST.findSize() != 0) {
            //new variables are declared
            newScope(thenST, then);
        } else {
            then.translate();
        }

        String l1 = labelCount.toString();
        labelCount++;
        CodeGen.main.add(new Branch("", "L" + l1));

        CodeGen.main.add(new LabelInstr("L" + l0));
        if (elseST.findSize() != 0) {
            //new variables are declared
            newScope(elseST, elseSt);
        } else {
            elseSt.translate();
        }

        CodeGen.main.add(new LabelInstr("L" + l1));

    }

    @Override
    public void weight() {
        expression.weight();
        then.weight();
        elseSt.weight();

        size += expression.getSize();
        size += then.getSize();
        size += elseSt.getSize();
    }

    @Override
    public void IRepresentation() {
        expression.IRepresentation();
        IGNode = expression.getIGNode();

        then.IRepresentation();

        elseSt.IRepresentation();
    }

    public static void newScope(SymbolTable ST, StatementAST statement) {
        int spSize = ST.findSize();

        if(spSize > Utility.STACK_SIZE ) {
            Utility.addMain(new SUB(Register.SP, Register.SP, new ImmValue(Utility.STACK_SIZE)));
            while(spSize > Utility.STACK_SIZE) {
                //decrement stack pointer
                spSize = (spSize - Utility.STACK_SIZE);
                Utility.addMain(new SUB(Register.SP, Register.SP, new ImmValue(spSize)));

            }
        } else {
            Utility.addMain(new SUB(Register.SP, Register.SP, new ImmValue(spSize)));
        }


        Utility.addJumpSP(spSize+Utility.getJumpSP());

        statement.translate();

        if(spSize > Utility.STACK_SIZE) {
            Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(Utility.STACK_SIZE)));
            while(spSize > Utility.STACK_SIZE) {
                //increment stack pointer
                spSize = (int) (spSize - Utility.STACK_SIZE);
                Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(spSize)));
            }
        } else {
            Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(spSize)));
        }

        Utility.resetJumpSP();
    }
}
