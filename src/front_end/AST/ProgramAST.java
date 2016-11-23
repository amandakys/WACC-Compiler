package front_end.AST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.*;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
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
import static back_end.Utility.addFunction;

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
        Utility.addMain(new PUSH(Register.LR));

        if(size != 0) {
            //decrement stack pointer
            Utility.addMain(new SUB(Register.SP, Register.SP, operSize));
        }

        statement.translate();

        if(size == 0) {
            //increment stack pointer
            Utility.addMain(new ADD(Register.SP, Register.SP, operSize));
        }

        Utility.addMain(new LOAD(Register.R0, new ImmValue(0)));
        Utility.addMain(new POP(Register.PC));
        Utility.addMain(new Directive("ltorg"));

        for (String s : CodeGen.placeholders) {
            if (!dataHasPlaceholder(s)) {
                Utility.pushToPushData(s);
            }
        }

        for (ExpressionAST e: CodeGen.printedExpressions) {
            printInstr(e);
        }

        if (getPrintlnPlaceholder() != null) {//println instructions exist
            printlnInstr();
        }
    }

    private void printInstr(ExpressionAST e) {
        int exprSize = e.getType().getSize();
        String type = e.getType().getTypeName();
        if (!hasFunction("p_print_" + type)) {
            //a char can be printed using "putchar" function
            if (!(e instanceof CharLitAST)) {

                addFunction(new LabelInstr("p_print_" + type));
                addFunction(new PUSH(Register.LR));

                if (e instanceof BoolliterAST) {
                    addFunction(new CMP(Register.R0, new ImmValue(0)));

                    addFunction(new LOAD("NE", Register.R0, new LabelExpr(getTruePlaceholder())));
                    addFunction(new LOAD("EQ", Register.R0, new LabelExpr(getFalsePlaceholder())));
                } else {
                    //functions that print string, bool, int have to be specified separately
                    if (e instanceof StringLiterAST) {
                        addFunction(new LOAD(popParamReg(), new Address(Register.R0)));
                        addFunction(new ADD(popParamReg(), Register.R0, new
                                ImmValue(exprSize)));
                    } else if (e instanceof IntLiterAST) {
                        addFunction(new MOV(popParamReg(), Register.R0));
                    }
                    addFunction(new LOAD(Register.R0, new LabelExpr(getPlaceholder(e))));
                }

                //TODO: Why adding r0 to 4?
                addFunction(new ADD(Register.R0, Register.R0, new ImmValue(4)));

                addFunction(new Branch("L", "printf"));
                addFunction(new MOV(Register.R0, new ImmValue(0)));
                addFunction(new Branch("L", "fflush"));

                addFunction(new POP(Register.PC));
            }
        }
    }

    private void printlnInstr() {
        if(!Utility.hasFunction("p_print_ln"))  {
//            addMain(new Branch("L", "p_print_ln"));

            addFunction(new LabelInstr("p_print_ln"));
            addFunction(new PUSH(Register.LR));
            addFunction(new LOAD(Register.R0, new LabelExpr(getPrintlnPlaceholder())));
            addFunction(new ADD(Register.R0, Register.R0, new ImmValue(4)));
            addFunction(new Branch("L", "puts"));
            addFunction(new MOV(Register.R0, new ImmValue(0)));
            addFunction(new Branch("L", "fflush"));

            addFunction(new POP(Register.PC));
        }
    }

}


