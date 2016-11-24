package back_end.data_type;

import back_end.data_type.register.ShiftedReg;

public class Offset {
    private ImmValue value;
    private ShiftedReg register;

    public Offset(ImmValue value) {
        this.value = value;
    }

    public Offset(ShiftedReg register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return value != null ? value.toString() : register.toString();
    }
}
