package back_end;

import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.LabelExpr;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.POP;
import back_end.instruction.PUSH;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;

public class PrintUtility {
    public static final int EXIT_CODE = -1;

    public PrintUtility() {
    }

    public static void addToEndFunctions(String s) {
        if (!CodeGen.endFunctions.contains(s)) {
            CodeGen.endFunctions.add(s);
        }
    }

    public static void addToPlaceholders(String s) {
        if (!CodeGen.placeholders.contains(s)) {
            CodeGen.placeholders.add(s);
        }
    }

    public static void throwRuntimeError() {
        addToEndFunctions("p_throw_runtime_error");
        addToPlaceholders("\"%.*s\\0\"");
        addToEndFunctions("p_print_string");
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
                case "p_print_reference":
                    printReference();
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
                case "p_free_pair":
                    p_free_pair();
                    break;
                case "p_free_array":
                    p_free_array();
                    break;
            }
            Utility.pushBackRegisters();
        }
    }

    private void printDefaults() {
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

    public void printReference() {
        Utility.addFunction(new LabelInstr("p_print_reference"));
        Utility.addFunction(new PUSH(Register.LR));
        Utility.addFunction(new MOV(Utility.popParamReg(), Register.R0));
        CodeGen.functions.add(new LOAD(Register.R0, new LabelExpr(Utility.getReferencePlaceholder())));
        printDefaults();
    }

    public void p_check_null_pointer() {
        CodeGen.functions.add(new LabelInstr("p_check_null_pointer"));
        CodeGen.functions.add(new PUSH(Register.LR));
        CodeGen.functions.add(new CMP(Register.R0, new ImmValue(0)));
        CodeGen.functions.add(new LOAD("EQ", Register.R0, new LabelExpr(getErrorMessage(Error.nullReference))));
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
        CodeGen.functions.add(new LabelInstr("p_check_array_bounds"));
        CodeGen.functions.add(new PUSH(Register.LR));

        CodeGen.functions.add(new CMP(Register.R0, new ImmValue(0)));
        CodeGen.functions.add(new LOAD("LT", Register.R0, new LabelExpr(getErrorMessage(Error.arrayOutOfBoundsNegative))));
        CodeGen.functions.add(new Branch("LT", "p_throw_runtime_error"));
        CodeGen.functions.add(new LOAD(Register.R1, new Address(Register.R1)));

        CodeGen.functions.add(new CMP(Register.R0, Register.R1));
        CodeGen.functions.add(new LOAD("CS", Register.R0, new LabelExpr(getErrorMessage(Error.arrayOutOfBoundsLarge))));
        CodeGen.functions.add(new Branch("CS", "p_throw_runtime_error"));

        CodeGen.functions.add(new POP(Register.PC));
    }

    public static void p_divide_by_zero() {
        CodeGen.functions.add(new LabelInstr("p_check_divide_by_zero"));
        CodeGen.functions.add(new PUSH(Register.LR));
        CodeGen.functions.add(new CMP(Utility.popParamReg(), new ImmValue(0)));
        CodeGen.functions.add(new LOAD("EQ", Register.R0, new LabelExpr(getErrorMessage(Error.divideByZero))));
        CodeGen.functions.add(new Branch("LEQ", "p_throw_runtime_error"));
        CodeGen.functions.add(new POP(Register.PC));
    }

    public static void p_integer_overflow() {
        CodeGen.functions.add(new LabelInstr("p_throw_overflow_error"));
        CodeGen.functions.add(new LOAD(Register.R0, new LabelExpr(getErrorMessage(Error.overflow))));
        CodeGen.functions.add(new Branch("L", "p_throw_runtime_error"));
    }

    private static void p_free() {
        CodeGen.functions.add(new LabelInstr("p_free_array"));
        CodeGen.functions.add(new PUSH(Register.LR));
        CodeGen.functions.add(new CMP(Register.R0, new ImmValue(0)));
        CodeGen.functions.add(new LOAD("EQ", Register.R0, new LabelExpr(getErrorMessage(Error.nullReference))));
        CodeGen.functions.add(new Branch("EQ", "p_throw_runtime_error"));
    }

    public static void p_free_pair() {
        p_free();
        CodeGen.functions.add(new PUSH(Register.R0));
        CodeGen.functions.add(new LOAD(Register.R0, new Address(Register.R0)));
        CodeGen.functions.add(new Branch("L", "free"));
        CodeGen.functions.add(new LOAD(Register.R0, new Address(Register.SP)));
        CodeGen.functions.add(new LOAD(Register.R0, new PreIndex(Register.R0, new ImmValue(4))));
        CodeGen.functions.add(new Branch("L", "free"));
        CodeGen.functions.add(new POP(Register.R0));
        CodeGen.functions.add(new Branch("L", "free"));
        CodeGen.functions.add(new POP(Register.PC));
    }

    public static void p_free_array() {
        p_free();
        CodeGen.functions.add(new Branch("L", "free"));
        CodeGen.functions.add(new POP(Register.PC));
    }

    private static String getErrorMessage(String error) {
        for(int i = 2; i < CodeGen.data.size(); i += 3) {
            if (CodeGen.data.get(i).getValue().equals(error)) {
                String msg =  CodeGen.data.get(i - 2).toString();
                return msg.substring(0, msg.length() - 2);
            }
        }

//        for (int i = 2; i < CodeGen.toPushData.size(); i += 3) {
//            if (CodeGen.toPushData.get(i).getValue().equals(error)) {
//                String msg =  CodeGen.toPushData.get(i - 2).toString();
//                return msg.substring(0, msg.length() - 2);
//            }
//        }

        return null;
    }
}