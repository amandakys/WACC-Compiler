package back_end.data_type;

public class Address extends Expression {
    private Register r;
    private Offset offset;

    public Address(Register r) {
        this.r = r;
    }

    public Address(Register r, Offset offset) {
        this.r = r;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "[" + r.toString() + "]";
    }
}
