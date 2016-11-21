package front_end.symbol_table;

/**
 * Created by andikoh on 08/11/2016.
 */
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
