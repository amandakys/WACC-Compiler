package front_end.symbol_table;

public class SCALAR extends TYPE {
    private int SIZE;

    public SCALAR(String typeName) {
        super(typeName);
    }

    public void setSize(int size) {
        this.SIZE = size;
    }

    public int getSize() {
        return SIZE;
    }
}
