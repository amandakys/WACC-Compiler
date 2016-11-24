package back_end.data_type.register;

import back_end.data_type.Expression;
import back_end.data_type.ImmValue;
import javafx.geometry.Pos;

public class PostIndex extends ShiftedReg {
    public PostIndex(Register baseReg) {
        super(baseReg);
    }

    public PostIndex(Register baseReg, ImmValue shiftVal) {
        super(baseReg, shiftVal);
    }

    public PostIndex(Register baseReg, ImmValue shiftVal, boolean jump) {
        super(baseReg, shiftVal, jump);
    }

    public PostIndex(Register baseReg, Register rm, Shift shift, ImmValue shiftVal) {
        super(baseReg, rm, shift, shiftVal);
    }

    @Override
    public PostIndex addToShiftVal(int newVal) {
        PostIndex reg = new PostIndex(getBaseReg(), getRm(), getShift(), getShiftVal());
        reg.setShiftVal(new ImmValue(reg.getShiftVal().getValue() + newVal));
        return reg;
    }

    @Override
    public String toString() {
        String res = "";

        if(Integer.parseInt(shiftVal.getValue()) != 0) {
            if (rm == null && shiftVal != null) {
                res += shiftVal;
            } else if (rm != null && shiftVal != null) {
                res += rm + ", " + shift + " " + shiftVal;
            }
        }

        return baseReg + ", " + ", " + res;
    }
}
