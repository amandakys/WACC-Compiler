package back_end.data_type.register;

/**
 * Created by donamphuong on 24/11/2016.
 */
public enum Shift {
    LSL, LSR, ASR, ROR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
