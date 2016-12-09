package back_end;

import back_end.data_type.register.Register;
import back_end.instruction.Directive;
import back_end.instruction.Instruction;
import back_end.instruction.LabelInstr;
import front_end.AST.ExpressionAST.*;
import main.CodeGen;

import java.util.Stack;

public class Utility {
    private static Stack<Integer> jumpSP = new Stack<>();
    private static int NUM_QUOTATION = 2;

    public static int STACK_SIZE = (int) Math.pow(2, 10);

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
        Utility.addData(new LabelInstr(getNextString()));
        CodeGen.numStrings++;
        //discard the "" in a string when finding the string's size
        Utility.addData(new Directive("word", String.valueOf(value.replace
                ("\\", "").length() - NUM_QUOTATION)));
        Utility.addData(new Directive("ascii", value));
    }

    public static void pushToPushData(String value) {
        CodeGen.toPushData.add(new LabelInstr(getNextPlaceholder()));
        CodeGen.numPlaceholders++;
        //discard the "" in a string when finding the string's size
        CodeGen.toPushData.add(new Directive("word", String.valueOf(value.replaceAll("[\\\\\"]", "").length())));
        CodeGen.toPushData.add(new Directive("ascii", value));
    }

    public static String getNextString() {
        return "msg_" + CodeGen.numStrings;
    }

    public static String getNextPlaceholder() {
        int sum = CodeGen.numStrings + CodeGen.numPlaceholders;
        return "msg_" + sum;
    }

    public static String getStringPlaceholder() {
        String msg = isPlaceholder("\"%.*s\\0\"");
        return msg.substring(0, msg.length() - 2);
    }

    public static String getIntPlaceholder() {
        String msg = isPlaceholder("\"%d\\0\"");
        return msg.substring(0, msg.length() - 2);
    }

    public static String getCharPlaceholder() {
        String msg = isPlaceholder("\" %c\\0\"");
        return msg.substring(0, msg.length() - 2);
    }
    public static String getPlaceholder(ExpressionAST expression) {
        String msg = "";
        if (expression instanceof StringLiterAST) {
                msg = isPlaceholder("\"%.*s\\0\"");
            } else if (expression instanceof IntLiterAST) {
                msg = isPlaceholder("\"%d\\0\"");
            }
        //drop the semi colon
        return msg.substring(0, msg.length() - 2);
    }

    public static String getTruePlaceholder() {
        String msg = isPlaceholder("\"true\\0\"");
        return msg.substring(0, msg.length() - 2);
    }

    public static String getFalsePlaceholder() {
        String msg = isPlaceholder("\"false\\0\"");
        return msg.substring(0, msg.length() - 2);
    }

    public static String getPrintlnPlaceholder() {
        String msg = isPlaceholder("\"\\0\"");
        if (msg == null) {
            return null;
        } else {
            return msg.substring(0, msg.length() - 2);
        }
    }

    public static String getReferencePlaceholder() {
        String msg = isPlaceholder("\"%p\\0\"");
        return msg.substring(0, msg.length() - 2);
    }

    /*
        This traverses through only the element in CodeGen.data that starts with ascii directive to see if
        a placeholder has been pushed to CodeGen.data or not. E.g: hasPlaceholder("%d\\0") returns true if
        there is .ascii element that is named "%d\\0"
     */
    public static boolean hasPlaceholder(String placeholder) {
        //4th element in CodeGen.data is 1st element starts with .ascii directive
        //0: msg_ , 1: .word, 2: .ascii
        for (int i = 0; i < CodeGen.placeholders.size(); i ++) {
            if (CodeGen.placeholders.get(i).equals(placeholder)) {
                return true;
            }
        }

        return false;
    }

    public static boolean dataHasPlaceholder(String placeholder) {

        for (int i = 2; i < CodeGen.toPushData.size(); i += 3) {
            if (CodeGen.toPushData.get(i).getValue().equals(placeholder)) {
                return true;
            }
        }
        return false;
    }

    public static String isPlaceholder(String placeholder) {
        for (int i = 2; i < CodeGen.toPushData.size(); i += 3) {
            if (CodeGen.toPushData.get(i).getValue().equals(placeholder)) {
                return CodeGen.toPushData.get(i - 2).toString();
            }
        }
        return null;
    }

    //get the value of the shift of stack pointer

    public static int getJumpSP() {
        if (jumpSP.isEmpty()) {
            jumpSP.push(0);
        }
        return jumpSP.peek();
    }

    // add new JumpSP into the JumpSp stack
    public static void addJumpSP(int jumpSize) {
        jumpSP.push(jumpSize);
    }


    //increase the top JumpSP by an amount
    public static void plusJumpSP(int jumpSize) {
        int top =0;
        if(!jumpSP.isEmpty()) {
            top = jumpSP.pop();
        }
        top += jumpSize;
        jumpSP.push(top);
    }

    //restore the previous JumpSP value
    public static int resetJumpSP() {
        if(!jumpSP.isEmpty()) {
            return jumpSP.pop();
        } else {
            return 0;
        }
    }


}
