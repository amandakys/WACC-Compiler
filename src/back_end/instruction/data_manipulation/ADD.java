package back_end.instruction.data_manipulation;

import back_end.Utility;
import back_end.data_type.Operand;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;
import main.CodeGen;

/*
    The Add instruction adds the values in Rn and Operand2.
 */
public class ADD implements Instruction {
    private Register dest;
    private Register lhs;
    private Operand rhs;

    private String sFlag = "";

    public ADD(Register dest, Register lhs, Operand rhs) {
        this.dest = dest;
        this.lhs = lhs;
        this.rhs = rhs;

        if (rhs instanceof Register) {
            sFlag = "S";
        }
    }

    public ADD(Register dest, Operand rhs) {
        this.dest = dest;
        this.rhs = rhs;

        if (rhs instanceof Register) {
            sFlag = "S";
        }
    }

    @Override
    public String toString() {
        String res = "";

        if(lhs == null) {
            res = rhs.toString();
        } else {
            res = lhs + ", " + rhs;
        }

        return "\tADD" + sFlag + " " + dest + ", " + res;
    }

    @Override
    public String getValue() {
        return lhs.toString() + " + " + rhs.toString();
    }
}
