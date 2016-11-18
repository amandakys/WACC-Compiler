package back_end.data_type;

/**
 * Created by npd215 on 18/11/16.
 */
public class Address extends Expression {
    private int add;

    public Address(int add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "=" + add;
    }
}
