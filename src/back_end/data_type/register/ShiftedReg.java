package back_end.data_type.register;

import back_end.data_type.Expression;
import back_end.data_type.ImmValue;

/**
 * Created by donamphuong on 21/11/2016.
 */
public abstract class ShiftedReg extends Expression {
    protected Register baseReg;

    protected Register rm;
    protected ImmValue shiftVal;
    protected Shift shift;
    protected boolean jump;

    public ShiftedReg(Register baseReg) {
        this.baseReg = baseReg;
    }

    public ShiftedReg(Register baseReg, ImmValue shiftVal) {
        this(baseReg, shiftVal, false);
    }

    public ShiftedReg(Register baseReg, ImmValue shiftVal, boolean jump) {
        this.baseReg = baseReg;
        this.shiftVal = shiftVal;
        this.jump = jump;
    }

    public ShiftedReg(Register baseReg, Register rm, Shift shift, ImmValue shiftVal) {
        this.baseReg = baseReg;
        this.rm = rm;
        this.shift = shift;
        this.shiftVal = shiftVal;
        this.jump = false;
    }

    public ShiftedReg addToShiftVal(int newVal) {
        this.shiftVal = new ImmValue(newVal);
        return this;
    }
}
