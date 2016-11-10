package symbol_table;

/**
 * Created by andikoh on 10/11/2016.
 */
public class ARRAY extends IDENTIFIER{
    TYPE type;
    int size;

    public ARRAY(TYPE type, int size) {
        this.type = type;
        this.size = size;
    }


    @Override
    public TYPE getType() {
        return type;
    }
}
