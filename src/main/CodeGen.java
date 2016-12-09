package main;

import back_end.OptimisationUtility;
import back_end.instruction.LabelInstr;
import back_end.data_type.register.Register;
import back_end.instruction.Directive;
import back_end.instruction.Instruction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class CodeGen {
    // for String
    public static List<Instruction> data = new ArrayList<>();
    public static int numStrings = 0;
    //for placeholders
    public static List<String> placeholders = new ArrayList<>();
    public static List<Instruction> toPushData = new ArrayList<>();
    public static int numPlaceholders = 0;
    public static Map<String, Register> endFunctions = new HashMap<>();


    // variables & any non-primitive
    public static List<Instruction> text = new ArrayList<>();
    //main
    public static List<Instruction> main = new ArrayList<>();
    //functions used in main
    public static List<Instruction> functions = new ArrayList<>();

    public static List<Instruction> optText = new ArrayList<>();
    public static List<Instruction> optMain = new ArrayList<>();
    public static List<Instruction> optFunctions = new ArrayList<>();

    public CodeGen() {
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

        OptimisationUtility.optimiseInstructions();
        List<Instruction> optimised = OptimisationUtility.getOptimised();
        for (Instruction instr: optimised) {
            bw.write(instr.toString() + "\n");
        }

//        for(Instruction instr : text) {
//            bw.write(instr.toString() + "\n") ;
//        }
//        bw.newLine();
//
//        for(Instruction instr : main) {
//            bw.write(instr.toString() + "\n");
//        }
//        for(Instruction instr : functions) {
//            bw.write(instr.toString() + "\n");
//        }

        bw.close();
    }
}
