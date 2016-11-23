package back_end.instruction;

import back_end.data_type.register.Register;

public class Push implements Instruction{
    private Register reg;

    public Push(Register reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return "\tPush {" + reg + "}";
    }

    @Override
    public String getValue() {
        return reg.toString();
    }
}
