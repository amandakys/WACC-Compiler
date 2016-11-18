package back_end.instruction.data_manipulation;

import back_end.data_type.Operand;
import back_end.data_type.Register;
import back_end.instruction.Instruction;

/**
 * Created by npd215 on 18/11/16.
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
        return "\tadd " + dest + " " + lhs + " " + rhs;
    }
}
