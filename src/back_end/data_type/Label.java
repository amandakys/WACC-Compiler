package back_end.data_type;

import back_end.instruction.Instruction;

/**
 * Created by npd215 on 18/11/16.
 */
public class Label extends Expression implements Instruction {
    private String name;

    public Label(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": ";
    }
}
