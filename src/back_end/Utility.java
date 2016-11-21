package back_end;

import back_end.data_type.Register;
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

    /*
        Return the number of the last message stored in data, numbered from 0.
        CodeGen.data.size() - 1 eliminates .data directive in data
     */
    public static String getNumMess() {
        return String.valueOf((CodeGen.data.size() - 1)/3 - 1);
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

    public static Register popParamReg() {
        Register r = CodeGen.paramRegister.pop();
        CodeGen.toPushParamReg.push(r);
        return r;
    }

    public static Register popUnusedReg() {
        Register r = CodeGen.notUsedRegisters.pop();
        CodeGen.toPushUnusedReg.push(r);
        return r;
    }
}
