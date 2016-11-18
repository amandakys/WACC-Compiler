package back_end.instruction;

import back_end.data_type.Register;

/**
 * Created by npd215 on 18/11/16.
 */
public class Pop implements Instruction{
    private Register reg;

    public Pop(Register reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return "\tPOP {" + reg + "}";
    }
}