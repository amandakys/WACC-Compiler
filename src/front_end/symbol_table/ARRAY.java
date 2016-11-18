package front_end.symbol_table;

/**
 * Created by andikoh on 10/11/2016.
 */
public class ARRAY extends TYPE{
    private TYPE type;
    private int size;

    public ARRAY(TYPE type, int size) {
        super("array");
        this.type = type;
        this.size = size;
    }


    @Override
    public TYPE getType() {
        return this;
    }
    @Override
    public String toString() {
        return type.toString() + "[]";
    }
    public TYPE getElementType() {
        return type;
    }
}