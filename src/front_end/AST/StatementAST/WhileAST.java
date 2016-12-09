package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
import front_end.AST.ExpressionAST.BinOpAST;
import front_end.AST.ExpressionAST.BoolliterAST;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ProgramAST;
import front_end.symbol_table.SymbolTable;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class WhileAST extends StatementAST {
    ExpressionAST expression;
    StatementAST statement;
    SymbolTable ST;

    public WhileAST(ParserRuleContext ctx, ExpressionAST expression, StatementAST statement, SymbolTable ST) {
        super(ctx);
        this.expression = expression;
        this.statement = statement;
        this.ST = ST;

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

    private boolean evaluateFalse() {
        if (expression instanceof BoolliterAST) {
            return ((BoolliterAST) expression).getBoolVal().equals("false");
        } else if (expression instanceof BinOpAST) {
            if (((BinOpAST) expression).booleanOptimise() != null) {
                return !((BinOpAST) expression).booleanOptimise();
            }
        }
        return false;
    }

    /*while (expression)
    * if the expression is "false" or is evaluated to "false" then the while
    * loop is ignored. Therefore the sourcecode for while loop will not be
    * printed out*/

    @Override
    public void translate() {
        if(!evaluateFalse()) {
            String conditionLabel = labelCount.toString();
            CodeGen.main.add(new Branch("", "L" + conditionLabel));
            labelCount++;
            String whileBodyLabel = labelCount.toString();
            labelCount++;
            CodeGen.main.add(new LabelInstr("L" + whileBodyLabel));
            Register result = CodeGen.notUsedRegisters.peek();
            //Visitor.ST = ST;
            if (ST.findSize() != 0) {
                newScope(statement);
            } else {
                statement.translate();
            }
            Utility.pushBackRegisters();
            //Visitor.ST = Visitor.ST.getEncSymbolTable();
            Utility.pushRegister(result);
            CodeGen.main.add(new LabelInstr("L" + conditionLabel));

            result = CodeGen.notUsedRegisters.peek();
            expression.translate();
            Utility.pushBackRegisters();

            CodeGen.main.add(new CMP(result, new ImmValue(1)));

            Utility.pushRegister(result);
            CodeGen.main.add(new Branch("EQ", "L" + whileBodyLabel));
        }

    }

    @Override
    public void weight() {
        expression.weight();
        statement.weight();

        size += expression.getSize();
        size += statement.getSize();
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

<<<<<<< HEAD
=======
    }

    @Override
    public boolean determineLoopInvariance() {
        return false;
>>>>>>> b254bb1a8196684f77b37759ce994e5fdbb3cbb0
    }
}