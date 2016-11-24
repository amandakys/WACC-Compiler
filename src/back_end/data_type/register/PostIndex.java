package back_end.data_type.register;

import back_end.data_type.Expression;
import back_end.data_type.ImmValue;

/**
 * Created by donamphuong on 22/11/2016.
 */
public class PostIndex extends ShiftedReg {
    private Register baseReg;
    private Expression e;

    private Register rm;
    private Shift shift;
    private ImmValue shiftVal;

    public PostIndex(Register baseReg) {
        this.baseReg = baseReg;
    }

    public PostIndex(Register baseReg, Expression e) {
        this.baseReg = baseReg;
        if(!(e instanceof ImmValue && e.toString().equals("#0"))) {
            this.e = e;
        }
    }

    public PostIndex(Register baseReg, Register rm, Shift shift, ImmValue shiftVal) {
        this.baseReg = baseReg;
        this.shift = shift;
        this.shiftVal = shiftVal;
        this.rm = rm;
    }

    @Override
    public String toString() {
        String res = "";

        if(e != null) {
            res += e.toString();
        } else if(rm != null) {
            res +=  rm + ", " + shift + " " + shiftVal;
        }

        return baseReg + ", " + ", " + res;
    }
}
