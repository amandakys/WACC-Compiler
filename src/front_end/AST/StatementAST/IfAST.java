package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
import front_end.AST.ExpressionAST.BinOpAST;
import front_end.AST.ExpressionAST.BoolliterAST;
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

    private boolean evaluateTrue() {
        if (expression instanceof BoolliterAST) {
            return ((BoolliterAST) expression).getBoolVal().equals("true");
        } else if (expression instanceof BinOpAST) {
            if (((BinOpAST) expression).booleanOptimise() != null) {
                return ((BinOpAST) expression).booleanOptimise();
            }
        }
        return false;
    }

    /*if (expresion)
    * if the expression is "false" or is evaluated to "false" then only the
    * "else" clause is needed. Therefore the "then" clause will not be
    * printed in the sourcecode
    * Similar with the expression "true", only the "then" clause is needed,
    * so the "else" clause will not be printed.*/
    @Override
    public void translate() {
        //can not be evaluated to "true" or "false"
        if(!(evaluateFalse() || evaluateTrue())) {
            Register result = CodeGen.notUsedRegisters.peek();
            expression.translate();
            //jump to label if false
            CodeGen.main.add(new CMP(result, new ImmValue(0)));
            Utility.pushRegister(result);
            String l0 = labelCount.toString();

            CodeGen.main.add(new Branch("EQ", "L" + l0));
            labelCount++;
            if (thenST.findSize() != 0) {
                //new variables are declared
                newScope(thenST, then);
            } else {
                then.translate();
            }
            Utility.pushBackRegisters();

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
            Utility.pushBackRegisters();

            CodeGen.main.add(new LabelInstr("L" + l1));

        } else if (evaluateFalse()) {
            if (elseST.findSize() != 0) {
                //new variables are declared
                newScope(elseST, elseSt);
            } else {
                elseSt.translate();
            }
            Utility.pushBackRegisters();
        } else if (evaluateTrue()){
            if (thenST.findSize() != 0) {
                //new variables are declared
                newScope(thenST, then);
            } else {
                then.translate();
            }
            Utility.pushBackRegisters();
        }
        
    }

    private void newScope(SymbolTable ST, StatementAST statement) {
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
