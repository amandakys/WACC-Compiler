package back_end.instruction;

import back_end.data_type.register.Register;
import main.CodeGen;

public class Pop implements Instruction{
    private Register reg;

    public Pop(Register reg) {
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
}