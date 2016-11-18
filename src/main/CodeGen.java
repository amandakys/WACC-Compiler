package main;

import back_end.instruction.Instruction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CodeGen {
    public static List<Instruction> data = new ArrayList<>();
    public static List<Instruction> text = new ArrayList<>();
    public static List<Instruction> globalMain = new ArrayList<>();
    private List<Instruction> result = new ArrayList<>();

    public CodeGen() {
        result.addAll(data);
        result.addAll(text);
        result.addAll(globalMain);
    }

    public void writeFile(String name) throws IOException {
        File file = new File(name + ".s");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        String content = "";
        for(Instruction instr : result) {
            content += instr.toString() + "\n";
        }

        bw.write(content);
        bw.close();
    }
}
