package back_end.instruction;

import back_end.data_type.register.Register;
import main.CodeGen;

public class POP implements Instruction{
    private Register reg;

    public POP(Register reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return "\tPOP {" + reg + "}";
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