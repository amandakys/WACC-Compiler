package front_end.AST.StatementAST;

import antlr.BasicParser;
import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
import front_end.AST.AssignmentAST.AssignmentAST;
import front_end.AST.ExpressionAST.BinOpAST;
import front_end.AST.ExpressionAST.BoolliterAST;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.symbol_table.SymbolTable;
import main.CodeGen;
import main.Visitor;

import java.util.List;

/**
 * Created by zuyvu on 08/12/2016.
 */
public class ForAST extends StatementAST {
    List<StatementAST> statements;
    ExpressionAST expression;
    SymbolTable ST;

    public ForAST(BasicParser.ForContext ctx, List<StatementAST> statements, ExpressionAST expr, SymbolTable st) {
        super(ctx);
        this.statements = statements;
        this.expression = expr;
        this.ST = st;
    }

    @Override
    public void check() {

        if(expression.getType().equals(Visitor.ST.lookUpAll("bool")) &&
                statements.size() == 3 && statements.get(0) instanceof VarDeclAST &&
                statements.get(1) instanceof AssignmentAST) {
            //check that statement is valid
            ((VarDeclAST) statements.get(0)).checkNode();
//        for(StatementAST statementAST : statements) {
//                statementAST.checkNode(); //TODO:Verify 1st statement
//            }
            ((AssignmentAST) statements.get(1)).checkNode();
            statements.get(2).checkNode();
            //check that expression is valid
            expression.checkNode();
        } else {
            error("for condition block is invalid");
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


    @Override
    public void translate() {
//            String conditionLabel = labelCount.toString();
//            statements.get(0).translate(); //TODO: fix negative stackpointer & get rid of i when leaving for
//            CodeGen.main.add(new Branch("", "L" + conditionLabel));
//            labelCount++;
//            String forBodyLabel = labelCount.toString();
//            labelCount++;
//            CodeGen.main.add(new LabelInstr("L" + forBodyLabel));
//            Register result = CodeGen.notUsedRegisters.peek();
//            //Visitor.ST = ST;
//            if (ST.findSize() != 0) {
//                newScope(statements);
//            } else {
//                statements.get(2).translate();
//                statements.get(1).translate();
//            }
//            Utility.pushBackRegisters();
//            //Visitor.ST = Visitor.ST.getEncSymbolTable();
//            Utility.pushRegister(result);
//            CodeGen.main.add(new LabelInstr("L" + conditionLabel));
//
//            result = CodeGen.notUsedRegisters.peek();
//            expression.translate(); //have to be a binOp
//            Utility.pushBackRegisters();
//
//            CodeGen.main.add(new CMP(result, new ImmValue(1)));
//
//            Utility.pushRegister(result);
//            CodeGen.main.add(new Branch("EQ", "L" + forBodyLabel));
    }

    @Override
    public void weight() {

    }

    @Override
    public void IRepresentation() {

    }

    private void newScope(List<StatementAST> statements) {
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

        statements.get(2).translate();
        statements.get(1).translate();

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


}
