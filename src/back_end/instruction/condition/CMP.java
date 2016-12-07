package back_end.instruction.condition;

import back_end.data_type.Operand;
import back_end.instruction.Instruction;

public class CMP implements Instruction {
    private Operand one;
    private Operand two;

    public CMP(Operand one, Operand two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String toString() {
        return "\tCMP " + one.toString() + ", " + two.toString();
    }
}
