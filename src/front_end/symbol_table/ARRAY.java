package front_end.symbol_table;

import main.Visitor;

/**
 * Created by andikoh on 10/11/2016.
 */
public class ARRAY extends TYPE{
    private TYPE type;
    private int elem_size;
    private int SIZE;


    public ARRAY(TYPE type, int size) {
        super("array");
        this.type = type;
        this.elem_size = size;
        this.SIZE = type.getSize() * (elem_size + 1);

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

    public int getTotalSize() {
        return SIZE;
    }

    public int getSize() {
        return type.getSize();
    }
}
