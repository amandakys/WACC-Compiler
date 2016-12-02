package front_end.symbol_table;

public class ARRAY extends TYPE{
    private TYPE type;
    private int elem_size;
    private int SIZE = 4; //size of the array TYPE is 4, not the whole array


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

    //Return the type of the elements inside the array
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
