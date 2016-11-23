package back_end;

import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.LabelExpr;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.POP;
import back_end.instruction.PUSH;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.ExpressionAST.*;
import main.CodeGen;

public class PrintUtility {
    public static final int EXIT_CODE = -1;

    public PrintUtility() {
    }

    public void ioFunctions() {
        for (String s : CodeGen.endFunctions) {
            switch (s) {
                case "p_print_bool":
                    printBool();
                    break;
                case "p_print_string":
                    printString();
                    break;
                case "p_print_int":
                    printInt();
                    break;
                case "p_print_ln":
                    printlnInstr();
                    break;
                case "p_read_int":
                    read("int", Utility.getIntPlaceholder());
                    break;
                case "p_read_char":
                    read("char", Utility.getCharPlaceholder());
                    break;
                case "p_check_array_bounds":
                    p_check_array_bounds();
                    break;
                case "p_check_null_pointer":
                    p_check_null_pointer();
                    break;
                case "p_divide_by_zero":
                    p_divide_by_zero();
                    break;
                case "p_integer_overflow":
                    p_integer_overflow();
                    break;
                case "p_throw_runtime_error":
                    p_throw_runtime_error();
                    break;
            }
            Utility.pushBackRegisters();
        }
    }

    public void printDefaults() {
        Utility.addFunction(new ADD(Register.R0, Register.R0, new ImmValue(4)));
        Utility.addFunction(new Branch("L", "printf"));
        Utility.addFunction(new MOV(Register.R0, new ImmValue(0)));
        Utility.addFunction(new Branch("L", "fflush"));
        Utility.addFunction(new POP(Register.PC));
    }

    public void printBool() {
        Utility.addFunction(new LabelInstr("p_print_bool"));
        Utility.addFunction(new PUSH(Register.LR));
        Utility.addFunction(new CMP(Register.R0, new ImmValue(0)));
        Utility.addFunction(new LOAD("NE", Register.R0, new LabelExpr(Utility.getTruePlaceholder())));
        Utility.addFunction(new LOAD("EQ", Register.R0, new LabelExpr(Utility.getFalsePlaceholder())));
        printDefaults();
    }

    public void printString() {
        Utility.addFunction(new LabelInstr("p_print_string"));
        Utility.addFunction(new PUSH(Register.LR));
        Utility.addFunction(new LOAD(Utility.popParamReg(), new Address(Register.R0)));
        Utility.addFunction(new ADD(Utility.popParamReg(), Register.R0, new ImmValue(4)));
        Utility.addFunction(new LOAD(Register.R0, new LabelExpr(Utility.getStringPlaceholder())));
        printDefaults();
    }

    public void printInt() {
        Utility.addFunction(new LabelInstr("p_print_int"));
        Utility.addFunction(new PUSH(Register.LR));
        Utility.addFunction(new MOV(Utility.popParamReg(), Register.R0));
        Utility.addFunction(new LOAD(Register.R0, new LabelExpr(Utility.getIntPlaceholder())));
        printDefaults();
    }

    public void read(String type, String placeholder) {
        Utility.addFunction(new LabelInstr("p_read_" + type));
        Utility.addFunction(new PUSH(Register.LR));
        Utility.addFunction(new MOV(Utility.popParamReg(), Register.R0));
        Utility.addFunction(new LOAD(Register.R0, new LabelExpr(placeholder)));

        Utility.addFunction(new ADD(Register.R0, Register.R0, new ImmValue(4)));
        Utility.addFunction(new Branch("L", "scanf"));
        Utility.addFunction(new POP(Register.PC));
    }

    public void printInstr(ExpressionAST e) {
        int exprSize = e.getType().getSize();
        String type = e.getType().getTypeName();
        if (!Utility.hasFunction("p_print_" + type)) {
            //a char can be printed using "putchar" function
            if (!(e instanceof CharLitAST)) {

                Utility.addFunction(new LabelInstr("p_print_" + type));
                Utility.addFunction(new PUSH(Register.LR));

                if (e instanceof BoolliterAST) {
                    Utility.addFunction(new CMP(Register.R0, new ImmValue(0)));
                    Utility.addFunction(new LOAD("NE", Register.R0, new LabelExpr(Utility.getTruePlaceholder())));
                    Utility.addFunction(new LOAD("EQ", Register.R0, new LabelExpr(Utility.getFalsePlaceholder())));
                } else if (e instanceof StringLiterAST) {
                    //functions that print string, bool, int have to be specified separately
                    Utility.addFunction(new LOAD(Utility.popParamReg(), new Address(Register.R0)));
                    Utility.addFunction(new ADD(Utility.popParamReg(), Register.R0, new ImmValue(exprSize)));
                    Utility.addFunction(new LOAD(Register.R0, new LabelExpr(Utility.getPlaceholder(e))));
                } else if (e instanceof IntLiterAST) {
                    Utility.addFunction(new MOV(Utility.popParamReg(), Register.R0));
                    Utility.addFunction(new LOAD(Register.R0, new LabelExpr(Utility.getPlaceholder(e))));
                }
                //addFunction(new LOAD(Register.R0, new LabelExpr(getPlaceholder(e))));


                //TODO: Why adding r0 to 4?
                Utility.addFunction(new ADD(Register.R0, Register.R0, new ImmValue(4)));

                Utility.addFunction(new Branch("L", "printf"));
                Utility.addFunction(new MOV(Register.R0, new ImmValue(0)));
                Utility.addFunction(new Branch("L", "fflush"));

                Utility.addFunction(new POP(Register.PC));
            }
        }
    }

    public void printlnInstr() {
        Utility.addFunction(new LabelInstr("p_print_ln"));
        Utility.addFunction(new PUSH(Register.LR));
        Utility.addFunction(new LOAD(Register.R0, new LabelExpr(Utility.getPrintlnPlaceholder())));
        Utility.addFunction(new ADD(Register.R0, Register.R0, new ImmValue(4)));
        Utility.addFunction(new Branch("L", "puts"));
        Utility.addFunction(new MOV(Register.R0, new ImmValue(0)));
        Utility.addFunction(new Branch("L", "fflush"));
        Utility.addFunction(new POP(Register.PC));
    }

    public void p_check_null_pointer() {
        CodeGen.functions.add(new LabelInstr("p_check_null_pointer"));
        CodeGen.functions.add(new PUSH(Register.LR));
        CodeGen.functions.add(new CMP(Register.R0, new ImmValue(0)));
        CodeGen.functions.add(new LOAD("EQ", Register.R0, new LabelExpr("msg_0")));
        CodeGen.functions.add(new Branch("LEQ", "p_throw_runtime_error"));
        CodeGen.functions.add(new POP(Register.PC));
    }

    public static void p_throw_runtime_error() {
        CodeGen.functions.add(new LabelInstr("p_throw_runtime_error"));
        CodeGen.functions.add(new Branch("L", "p_print_string"));
        CodeGen.functions.add(new MOV(Register.R0, new ImmValue(EXIT_CODE)));
        CodeGen.functions.add(new Branch("L", "exit"));
    }

    public static void p_check_array_bounds() {
        CodeGen.functions.add(new PUSH(Register.LR));

        CodeGen.functions.add(new CMP(Register.R0, new ImmValue(0)));
        CodeGen.functions.add(new LOAD("LT", Register.R0, new LabelExpr("msg_0")));
        CodeGen.functions.add(new Branch("LT", "p_throw_runtime_error"));
        CodeGen.functions.add(new LOAD(Register.R1, new Address(Register.R1)));

        CodeGen.functions.add(new CMP(Register.R0, Register.R1));
        CodeGen.functions.add(new LOAD("CS", Register.R0, new LabelExpr("msg_1")));
        CodeGen.functions.add(new Branch("CS", "p_throw_runtime_error"));

        CodeGen.functions.add(new POP(Register.PC));
    }

    public static void p_divide_by_zero() {
        Register res = Utility.popUnusedReg();

        CodeGen.functions.add(new LabelInstr("p_check_divide_by_zero"));
        CodeGen.functions.add(new PUSH(Register.LR));
        CodeGen.functions.add(new CMP(res, new ImmValue(0)));
        CodeGen.functions.add(new LOAD("EQ", res, new LabelExpr("msg_1")));
        CodeGen.functions.add(new Branch("LEQ", "p_throw_runtime_error"));
        CodeGen.functions.add(new POP(Register.PC));
    }

    public static void p_integer_overflow() {
        CodeGen.main.add(new Branch("LNE", "p_throw_overflow_error"));
        CodeGen.functions.add(new LabelInstr("p_throw_overflow_error"));
        CodeGen.functions.add(new LOAD(Register.R0, new LabelExpr("msg_0")));
        CodeGen.functions.add(new Branch("L", "p_throw_runtime_error"));
    }
}