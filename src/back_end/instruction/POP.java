package back_end.instruction;

import back_end.data_type.register.Register;
import main.CodeGen;

public class POP implements Instruction{
    private Register[] reg;

    public POP(Register... reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return "\tPOP {" + getValue() + "}";
    }

    @Override
    public String getValue() {
        String regString = "";

        for(int i = 0; i < reg.length; i++) {
            regString += reg[i].toString();

            if (i != reg.length - 1) {
                regString += ", ";
            }
        }

        return regString;
    }
}