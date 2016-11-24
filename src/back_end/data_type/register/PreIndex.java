package back_end.data_type.register;

import back_end.data_type.Expression;
import back_end.data_type.ImmValue;

public class PreIndex extends ShiftedReg {
    public PreIndex(Register baseReg) {
        super(baseReg);
    }

    public PreIndex(Register baseReg, ImmValue shiftVal) {
        super(baseReg, shiftVal);
    }

    public PreIndex(Register baseReg, ImmValue shiftVal, boolean jump) {
        super(baseReg, shiftVal, jump);
    }

    public PreIndex(Register baseReg, Register rm, Shift shift, ImmValue shiftVal) {
        super(baseReg, rm, shift, shiftVal);
    }

    @Override
    public String toString() {
        String res = baseReg.toString();

        if(shiftVal != null && Integer.parseInt(shiftVal.getValue()) != 0) {
            if (rm == null) {
                res += ", " + shiftVal;
            } else if (shiftVal != null) {
                res += rm + ", " + shift + " " + shiftVal;
            }
        }

        return "[" + res + "]" + (jump ? "!" : "");
    }

    @Override
    public PreIndex addToShiftVal(int newVal) {
        PreIndex reg = new PreIndex(getBaseReg(), getRm(), getShift(), getShiftVal());
        int val = reg.getShiftVal() != null ? Integer.parseInt(reg.getShiftVal().getValue()) : 0;
        reg.setShiftVal(new ImmValue(String.valueOf(val + newVal)));
        return reg;
    }
}
