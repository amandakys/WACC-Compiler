package back_end.instruction;

import back_end.data_type.register.Register;

public class PUSH implements Instruction{
    private Register reg;

    public PUSH(Register reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return "\tPUSH {" + reg + "}";
    }

    @Override
    public String getValue() {
        return reg.toString();
    }

    @Override
    public boolean toRemove() {
        return false;
    }

    @Override
    public boolean checkNext() {
        return false;
    }
}
