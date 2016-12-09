package back_end.instruction;

import back_end.data_type.Expression;

public class LabelInstr implements Instruction {
    private String value;

    public LabelInstr(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + ": ";
    }

    @Override
    public String getValue() {
        return value;
    }
}
