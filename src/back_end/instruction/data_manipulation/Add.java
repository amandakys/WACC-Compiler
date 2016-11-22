package back_end.instruction.data_manipulation;

import back_end.data_type.Operand;
import back_end.data_type.Register;
import back_end.instruction.Instruction;

/*
    The ADD instruction adds the values in Rn and Operand2.
 */
public class Add implements Instruction {
    private Register dest;
    private Register lhs;
    private Operand rhs;

    public Add(Register dest, Register lhs, Operand rhs) {
        this.dest = dest;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "\tADD " + dest + ", " + lhs + ", " + rhs;
    }

    @Override
    public String getValue() {
        return lhs.toString() + " + " + rhs.toString();
    }
}
