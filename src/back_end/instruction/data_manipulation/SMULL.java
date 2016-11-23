package back_end.instruction.data_manipulation;

import back_end.data_type.register.Register;
import back_end.instruction.Instruction;

public class SMULL implements Instruction{
    private Register rdLo;
    private Register rdHi;
    private Register rm;
    private Register rs;
    //Not sure when needing these 2 fields below
    private String cond;
    private String sflag;

    public SMULL(Register rdLo, Register rdHi, Register rm, Register rs) {
        rdLo = rdLo;
        rdHi = rdHi;
        rm = rm;
        rs = rs;
        sflag = "";
        cond = "";
    }

    @Override
    public String toString() {
        return "\tSMULL" + cond + sflag + " " + rdLo + ", " + rdHi + ", " + rm + ", " + rs;
    }


    @Override
    public String getValue() {
        return rm.toString() + " * " + rs.toString();
    }
}
