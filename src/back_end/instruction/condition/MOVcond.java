package back_end.instruction.condition;

import back_end.data_type.Operand;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;

/**
 * Created by andikoh on 21/11/2016.
 */
public class MOVcond implements Instruction {
    private String condition;
    private Register dst;
    private Operand rhs;

    public MOVcond(String condition, Register dst, Operand rhs) {
        this.condition = condition;
        this.rhs = rhs;
        this.dst = dst;
    }

    @Override
    public String toString() {
        return "\tMOV" + condition.toUpperCase() + " " + dst + ", " + rhs;
    }

    @Override
    public String getValue() {
        return rhs.toString();
    }
}
