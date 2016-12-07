package front_end.AST;

import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.*;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.data_manipulation.SUB;
import back_end.instruction.load_store.LOAD;
import front_end.AST.FunctionDecl.FunctionDeclAST;
import front_end.AST.StatementAST.StatementAST;
import main.CodeGen;
import main.Visitor;
import optimisation.GraphColour;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

import static back_end.Utility.*;

public class ProgramAST extends Node {
    //aid adding the appropriate print functions when necessary
    private final PrintUtility printUtility = new PrintUtility();
    //represents functions that is not specified by users in the program, eg: print_int function
    private List<FunctionDeclAST> functions;
    private StatementAST statement;

    //the next address available (e.g after pushing an int, if the total size is 5, the next address should
    //be at 5-4 =1)
    public static int nextAddress = 0;

    public ProgramAST(ParserRuleContext ctx, List<FunctionDeclAST> functions, StatementAST statement) {
        super(ctx);
        this.functions = functions;
        this.statement = statement;
    }

    @Override
    public void check() {

    }

    @Override
    public void translate() {
        for(FunctionDeclAST func : functions) {
            func.translate();
        }

        Utility.addMain(new LabelInstr("main"));
        Utility.addMain(new PUSH(Register.LR));

        //open a new scope for the statement
        newScope(statement);

        Utility.addMain(new MOV(getRegister(), new ImmValue(0)));
        Utility.addMain(new POP(Register.PC));

        //push placeholders to data
        for (String s : CodeGen.placeholders) {
            if (!dataHasPlaceholder(s)) {
                Utility.pushToPushData(s);
            }
        }

        //add appropriate print functions
        printUtility.ioFunctions();
    }

    @Override
    public void weight() {
        for(FunctionDeclAST func : functions) {
            func.weight();
            size += func.getSize();
        }

        statement.weight();
        size += statement.getSize();
    }

    @Override
    public void IRepresentation() {
        //find IRepresentation of all functions
        for(FunctionDeclAST func : functions) {
            func.IRepresentation();
        }

        //find IRepresentation of the statement & set the register used by the program
        //to be the same as the first register in a statement
        statement.IRepresentation();
        IGNode = statement.IGNode;

        Visitor.ST.checkLiveness();
        GraphColour.colouringGraph();
    }

    public static void newScope(StatementAST statement) {
        //find how many address available in the program
        Visitor.ST.size();

        boolean hasChanged = false;
        int saved_Size = Visitor.ST.findSize();

        if(saved_Size != 0) {
            int spSize = saved_Size;

            if(spSize > STACK_SIZE ) {
                Utility.addMain(new SUB(Register.SP, Register.SP, new ImmValue(STACK_SIZE)));
                while(spSize > STACK_SIZE) {
                    //decrement stack pointer
                    spSize = (int) (spSize - STACK_SIZE);
                    Utility.addMain(new SUB(Register.SP, Register.SP, new ImmValue(spSize)));
                }
            } else {
                Utility.addMain(new SUB(Register.SP, Register.SP, new ImmValue(spSize)));
            }

            hasChanged = true;
        }

        statement.translate();

        if(hasChanged) {
            int spSize = saved_Size;

            if(spSize > STACK_SIZE) {
                Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(STACK_SIZE)));
                while(spSize > STACK_SIZE) {
                    //increment stack pointer
                    spSize = spSize - STACK_SIZE;
                    Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(spSize)));
                }
            } else {
                Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(spSize)));
            }
        }
    }
}


