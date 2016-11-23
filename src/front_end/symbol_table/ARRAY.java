package front_end.symbol_table;

import main.Visitor;

/**
 * Created by andikoh on 10/11/2016.
 */
public class ARRAY extends TYPE{
    private TYPE type;
    private int elem_size;
    private int SIZE = 4;


    public ARRAY(TYPE type, int size) {
        super("array");
        this.type = type;
        this.elem_size = size;
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

    public int getSize() {
        return SIZE;
    }

    public int getElem_size() {
        return elem_size;
    }
}
