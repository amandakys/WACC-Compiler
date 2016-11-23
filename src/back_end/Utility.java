package back_end;

import back_end.data_type.register.Register;
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

    public static void pushData(String value) {
        Utility.addData(new LabelInstr(getLastMessage()));
        CodeGen.numMessage++;
        //discard the "" in a string when finding the string's size
        Utility.addData(new Directive("word", String.valueOf(value.replaceAll("[\\\\\"]", "").length())));
        Utility.addData(new Directive("ascii", value));
    }

    public static void pushToPushDatat(String value) {
        CodeGen.toPushData.add(new LabelInstr(getLastMessage()));
        CodeGen.numMessage++;
        //discard the "" in a string when finding the string's size
        CodeGen.toPushData.add(new Directive("word", String.valueOf(value.replaceAll("[\\\\\"]", "").length())));
        CodeGen.toPushData.add(new Directive("ascii", value));
    }

    public static String getLastMessage() {
        return "msg_" + CodeGen.numMessage;
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

    /*
        This traverses through only the element in CodeGen.data that starts with ascii directive to see if
        a placeholder has been pushed to CodeGen.data or not. E.g: hasPlaceholder("%d\\0") returns true if
        there is .ascii element that is named "%d\\0"
     */
    public static boolean hasPlaceholder(String placeholder) {
        //4th element in CodeGen.data is 1st element starts with .ascii directive
        //0: msg_ , 1: .word, 2: .ascii
        for (int i = 2; i < CodeGen.toPushData.size(); i += 3) {
            if (CodeGen.toPushData.get(i).getValue().equals(placeholder)) {
                return true;
            }
        }

        return false;
    }

    /*
       This traverses through functions list, find if an instruction has already been defined by having
       label. E.g hasFunction("hello") find out whether label "hello" has been put in CodeGen.function or not
    */
    public static boolean hasFunction(String function) {
        for (Instruction instr : CodeGen.functions) {
            if ((instr instanceof LabelInstr) && instr.getValue().equals(function)) {
                return true;
            }
        }
        return false;
    }

    public static Register getBefore(Register r) {
        int index = Character.getNumericValue(r.toString().charAt(r.toString().length() - 1)) - 1;
        return Register.values()[index];
    }
}
