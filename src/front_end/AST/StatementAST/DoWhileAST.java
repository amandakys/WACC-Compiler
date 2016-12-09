package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.Expression;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.symbol_table.SymbolTable;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class DoWhileAST extends StatementAST {
    StatementAST statement;
    ExpressionAST condition;
    SymbolTable ST;
    public DoWhileAST(ParserRuleContext ctx, StatementAST statement, ExpressionAST condition, SymbolTable ST) {
        super(ctx);
        this.statement = statement;
        this.condition = condition;
        this.ST = ST;
    }

    @Override
    protected void check() {


        if (condition.getType().equals(Visitor.ST.lookUpAll("bool"))) {
            //check that statement is valid
            statement.checkNode();
        } else {
            error("expression is not of type boolean");
        }

        condition.checkNode();
    }

    @Override
    public void translate() {
        String whileBodyLabel = labelCount.toString();
        labelCount++;
        CodeGen.main.add(new LabelInstr("L" + whileBodyLabel));

        if (ST.findSize() != 0) {
            newScope(statement);
        } else {
            statement.translate();
        }
        condition.translate();

        CodeGen.main.add(new CMP(getRegister(), new ImmValue(1)));
        CodeGen.main.add(new Branch("EQ", "L" + whileBodyLabel));
    }

    @Override
    public void weight() {

    }

    @Override
    public void IRepresentation() {

    }

    private void newScope(StatementAST statement) {
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

        //Utility.addJumpSP(spSize);

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

        //Utility.resetJumpSP();
    }

    @Override
    public boolean determineLoopInvariance() {
        return false;
    }
}
