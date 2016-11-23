package main;

import back_end.instruction.LabelInstr;
import back_end.data_type.register.Register;
import back_end.instruction.Directive;
import back_end.instruction.Instruction;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.Node;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class CodeGen {

    //initialising notUsedRegisters available in a program
    public static Stack<Register> notUsedRegisters = new Stack<>();
    public static Stack<Register> paramRegister = new Stack<>();

    //registers that are popped are saved to be pushed back after a function ended
    public static Stack<Register> toPushParamReg = new Stack<>();
    public static Stack<Register> toPushUnusedReg = new Stack<>();

    private final int NUM_RESERVED_REGS = 11;
    private final int NUM_PARAM_REGS = 3;

    // for String
    public static List<Instruction> data = new ArrayList<>();
    public static int numStrings = 0;
    //for placeholders
    public static List<String> placeholders = new ArrayList<>();
    public static List<ExpressionAST> printedExpressions = new ArrayList<>();
    public static List<Instruction> toPushData = new ArrayList<>();
    public static int numPlaceholders = 0;
    public static List<String> endFunctions = new ArrayList<>();


    // variables & any non-primitive
    public static List<Instruction> text = new ArrayList<>();
    //main
    public static List<Instruction> main = new ArrayList<>();
    //functions used in main
    public static List<Instruction> functions = new ArrayList<>();

    public CodeGen() {
        initialiseReg();

        data.add(new Directive("data"));
        text.add(new Directive("text"));
        main.add(new Directive("global main"));
    }

    public void writeFile(String name) throws IOException {
        File file = new File(name + ".s");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        data.addAll(toPushData);
        if(data.size() != 1){
            for(Instruction instr : data) {
                if(instr instanceof LabelInstr) {
                    bw.newLine();
                }

                bw.write(instr.toString() + "\n");
            }
            bw.newLine();
        }

        for(Instruction instr : text) {
            bw.write(instr.toString() + "\n") ;
        }
        bw.newLine();

        for(Instruction instr: main) {
            bw.write(instr.toString() + "\n");
        }
        for(Instruction instr: functions) {
            bw.write(instr.toString() + "\n");
        }

        bw.close();
    }

    private void initialiseReg() {
        //an iterator to traverse through all components in enum class Register
        List<Register> allRegs = new ArrayList<>(EnumSet.allOf(Register.class));

        for(int i = NUM_RESERVED_REGS; i >= 1; i--) {

            if(i <= NUM_PARAM_REGS || i == 12) {
                //reg 1-3 and reg 12 are used to save parameters
                paramRegister.push(allRegs.get(i));
            } else {
                //reg 4-11 are preserved to be used in the program
                notUsedRegisters.push(allRegs.get(i));
            }
        }
    }
}
