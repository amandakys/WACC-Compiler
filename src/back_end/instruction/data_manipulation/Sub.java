package back_end.instruction.data_manipulation;

import back_end.data_type.Operand;
import back_end.data_type.Register;
import back_end.instruction.Instruction;

/*
    The SUB instruction subtracts the value of Operand2 from the value in Rn
 */
public class Sub implements Instruction {
    private Register dst;
    private Register lhs;
    private Operand rhs;

    public Sub(Register dst, Register lhs, Operand rhs) {
        this.dst = dst;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "\tSUB " + dst + ", " + lhs + ", " + rhs;
    }

    @Override
    public String getValue() {
        return lhs.toString() + " - " + rhs.toString();
    }
}
