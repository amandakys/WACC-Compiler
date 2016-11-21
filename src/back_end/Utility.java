package back_end;

import back_end.instruction.Directive;
import back_end.instruction.Instruction;
import back_end.instruction.LabelInstr;
import main.CodeGen;

/**
 * Created by donamphuong on 20/11/2016.
 */
public class Utility {

    public static void addMain(Instruction instr) {
        CodeGen.main.add(instr);
    }

    public static void addData(Instruction instr) {
        CodeGen.data.add(instr);
    }

    public static void addFunction(Instruction instr) {
        CodeGen.functions.add(instr);
    }

    public static String getNumMess() {
        return String.valueOf((CodeGen.data.size() - 1)/3);
    }

    public static String getLastMessage() {
        return "msg_" + Utility.getNumMess();
    }

    public static boolean dataContains(Instruction instr) {
        return CodeGen.data.contains(instr);
    }

    public static void pushData(String value) {
        String label = "msg_" + (CodeGen.data.size() - 1)/3;

        Utility.addData(new LabelInstr(label));
        //TODO: discard the "" in a string when finding the string's size
        Utility.addData(new Directive("word", String.valueOf(value.replaceAll("[\"\\ ]", "").length())));
        Utility.addData(new Directive("ascii", value));
    }
}
