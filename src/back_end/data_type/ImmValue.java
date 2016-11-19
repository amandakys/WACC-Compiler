package back_end.data_type;

/**
 * Created by npd215 on 18/11/16.
 */
public class ImmValue extends Operand {
    private int value;

    public ImmValue(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return "=" + value;
    }
}
