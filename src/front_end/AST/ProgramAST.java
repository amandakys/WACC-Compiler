package front_end.AST;

import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.*;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
import back_end.instruction.load_store.LOAD;
import front_end.AST.ExpressionAST.*;
import front_end.AST.FunctionDecl.FunctionDeclAST;
import front_end.AST.StatementAST.StatementAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

import static back_end.Utility.*;

public class ProgramAST extends Node {
    private final PrintUtility printUtility = new PrintUtility();
    private List<FunctionDeclAST> functions;
    private StatementAST statement;

    //the next address available (e.g after pushing an int, if the size is 5, the next address should
    //be at 5-4 =1)
    public static int nextAddress = 0;
    //specifies how many VARIABLE there are in current symbol table
    public static int size;
    private static int STACK_SIZE = (int) Math.pow(2, 10);

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

        newScope(statement);

        Utility.addMain(new LOAD(Register.R0, new ImmValue(0)));
        Utility.addMain(new POP(Register.PC));
        Utility.addMain(new Directive("ltorg"));

        for (String s : CodeGen.placeholders) {
            if (!dataHasPlaceholder(s)) {
                Utility.pushToPushData(s);
            }
        }

        printUtility.ioFunctions();
    }

    public static void newScope(StatementAST statement) {
        size = Visitor.ST.findSize();
        boolean hasChanged = false;
        int saved_Size = size;

        if(size != 0) {
            int spSize = size;

            if(spSize > STACK_SIZE) {
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
                Utility.addMain(new SUB(Register.SP, Register.SP, new ImmValue(STACK_SIZE)));
                while(spSize > STACK_SIZE) {
                    //increment stack pointer
                    spSize = (int) (spSize - STACK_SIZE);
                    Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(spSize)));
                }
            } else {
                Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(spSize)));
            }
        }
    }
}


