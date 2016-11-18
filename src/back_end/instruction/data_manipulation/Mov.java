package back_end.instruction.data_manipulation;

import back_end.data_type.Operand;
import back_end.data_type.Register;
import back_end.instruction.Instruction;

/**
 * Created by npd215 on 18/11/16.
 */
public class Mov implements Instruction {
    private Register dst;
    private Operand rhs;

    public Mov(Register dst, Operand rhs) {
        this.dst = dst;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "\tmov " + dst + " " + rhs;
    }
}
