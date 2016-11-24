package back_end.data_type.register;

import back_end.data_type.Expression;
import back_end.data_type.ImmValue;

/**
 * Created by donamphuong on 22/11/2016.
 */
public class PreIndex extends ShiftedReg {
    private Register baseReg;
    private Expression e;
    private Register rm;
    private ImmValue shift;
    private boolean jump;

    public PreIndex(Register baseReg) {
        this.baseReg = baseReg;
    }

    public PreIndex(Register baseReg, Expression e) {
        this(baseReg, e, false);
    }

    public PreIndex(Register baseReg, Expression e, boolean jump) {
        this.baseReg = baseReg;

        if(!(e instanceof ImmValue && e.toString().equals("#0"))) {
            this.e = e;
        }
        this.jump = jump;
    }

    @Override
    public String toString() {
        String res = baseReg.toString();

        if(e != null) {
            res += ", " + e.toString();
        } else if(rm != null) {
            res += ", " + rm + ", " + shift;
        }

        return "[" + res + "]" + (jump ? "!" : "");
    }
}
