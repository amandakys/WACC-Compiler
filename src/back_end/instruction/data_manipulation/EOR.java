package back_end.instruction.data_manipulation;

import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;

/**
 * Created by npd215 on 23/11/16.
 */
public class EOR implements Instruction {
    private Register dst;
    private Register op;
    private ImmValue n;
    private String cond;
    private String sFlag;

    public EOR(Register dst, Register op, ImmValue n) {
        this.dst = dst;
        this.op = op;
        this.n = n;
        cond = "";
        sFlag = "";
    }

    @Override
    public String toString() {
        return "\tEOR" + cond + sFlag + " " + dst + ", " + op + ", " + n;
    }


    @Override
    public String getValue() {
        return op + "^" + n.getValue();
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
