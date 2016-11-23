package front_end.AST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.*;
import back_end.instruction.data_manipulation.Add;
import back_end.instruction.data_manipulation.Sub;
import back_end.instruction.load_store.Load;
import front_end.AST.FunctionDecl.FunctionDeclAST;
import front_end.AST.StatementAST.StatementAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class ProgramAST extends Node {
    private List<FunctionDeclAST> functions;
    private StatementAST statement;

    //the next address available (e.g after pushing an int, if the size is 5, the next address should
    //be at 5-4 =1)
    public static int nextAddress = 0;
    //specifies how many VARIABLE there are in current symbol table
    public static int size;
    private boolean hasInitialised = false;

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
        //initialise size if it has not been initialised
        if(!hasInitialised) {
            size = Visitor.ST.findSize();
            hasInitialised = true;
        }
        Operand operSize = new ImmValue(size);

        for(FunctionDeclAST func : functions) {
            func.translate();
        }

        Utility.addMain(new LabelInstr("main"));
        Utility.addMain(new Push(Register.LR));

        if(size != 0) {
            //decrement stack pointer
            Utility.addMain(new Sub(Register.SP, Register.SP, operSize));
        }

        statement.translate();

        if(size != 0) {
            //increment stack pointer
            Utility.addMain(new Add(Register.SP, Register.SP, operSize));
        }

        Utility.addMain(new Load(Register.R0, new ImmValue(0)));
        Utility.addMain(new Pop(Register.PC));
        Utility.addMain(new Directive("ltorg"));
    }
}
