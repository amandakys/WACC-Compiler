package main;

import back_end.data_type.Register;
import back_end.instruction.Directive;
import back_end.instruction.Instruction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CodeGen {
    //initialising registers available in a program
    public static List<Register> registers = new ArrayList<>();
    private final int NUMBER_REGISTERS = 16;

    public static List<Instruction> data = new ArrayList<>(); // for String
    // variables & any non-primitive
    public static List<Instruction> text = new ArrayList<>();
    public static List<Instruction> globalMain = new ArrayList<>(); //main
    // program

    public CodeGen() {
        for (int i = 0; i < NUMBER_REGISTERS; i++) {
            registers.add(new Register(i));
        }

        data.add(new Directive("data"));


        text.add(new Directive("text"));
        globalMain.add(new Directive("global main"));
    }

    public void writeFile(String name) throws IOException {
        File file = new File(name + ".s");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        if(data.size() != 1){
            for(Instruction instr : data) {
                bw.newLine();
                bw.write(instr.toString() + "\n");
            }
            bw.newLine();
        }

        for(Instruction instr : text) {
            bw.write(instr.toString() + "\n") ;
        }
        bw.newLine();

        for(Instruction instr: globalMain) {
            bw.write(instr.toString() + "\n");
        }

        bw.close();
    }
}
