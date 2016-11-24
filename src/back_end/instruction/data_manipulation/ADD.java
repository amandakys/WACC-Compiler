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
            Utility.pushRegister((Register) rhs); //to pass incFunction
        }
        //CodeGen.notUsedRegisters.push(lhs);
        CodeGen.notUsedRegisters.remove(dest); //to pass incFunction

    }

    @Override
    public String toString() {
        return "\tADD" + sFlag + " " + dest + ", " + lhs + ", " + rhs;
    }

    @Override
    public String getValue() {
        return lhs.toString() + " + " + rhs.toString();
    }
}
