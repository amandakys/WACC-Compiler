package back_end.instruction.data_manipulation;

import back_end.data_type.ImmValue;
import back_end.data_type.Register;
import back_end.instruction.Instruction;

/*
    RSBS instruction substracts the value in Rn from zero, producing the arithmetic negative of the value, and places
    the result in the Rd and updates the N, Z, C, V flags
 */
public class RSBS implements Instruction {
    private Register rdst;
    private Register rn;
    private final String ZERO = "0";

    public RSBS(Register rdst, Register rn) {
        this.rdst = rdst;
        this.rn = rn;
    }

    @Override
    public String toString() {
        return "\tRSBS " + rdst + ", " + rn + ", " + new ImmValue(ZERO);
    }

    @Override
    public String getValue() {
        return rn.toString();
    }
}
