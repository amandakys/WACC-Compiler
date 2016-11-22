package back_end.data_type;

public class Offset {
    private ImmValue value;
    private ShiftedReg register;

    @Override
    public String toString() {
        return value != null ? value.toString() : register.toString();
    }
}
