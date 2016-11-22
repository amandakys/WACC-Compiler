package back_end.instruction.condition;

import back_end.data_type.register.Register;
import back_end.instruction.Instruction;

/**
 * Created by andikoh on 21/11/2016.
 */
public class ORR implements Instruction{
    private Register dst;
    private Register lhs;
    private Register rhs;

    public ORR(Register dst, Register lhs, Register rhs) {
        this.dst = dst;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "\tORR " + dst.toString() + ", " + lhs.toString() + ", " + rhs.toString();
    }

    @Override
    public String getValue() {
        return null;
    }
}
