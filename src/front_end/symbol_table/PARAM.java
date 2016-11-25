package front_end.symbol_table;

public class PARAM extends IDENTIFIER {
    TYPE type;

    public PARAM(TYPE type) {
        super();
        this.type = type;
    }

    @Override
    public TYPE getType() {
        return type;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
