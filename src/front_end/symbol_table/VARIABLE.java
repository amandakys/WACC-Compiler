package front_end.symbol_table;

/**
 * Created by andikoh on 08/11/2016.
 */
public class VARIABLE extends IDENTIFIER {
    TYPE type;

    public VARIABLE(TYPE type) {
        this.type = type;
    }

    public TYPE getType() {
        return this.type;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
