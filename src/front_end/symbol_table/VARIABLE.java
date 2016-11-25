package front_end.symbol_table;

public class VARIABLE extends IDENTIFIER {
    private TYPE type;

    public VARIABLE(TYPE type) {
        this.type = type;
    }

    public TYPE getType() {
        return this.type;
    }

    @Override
    public int getSize() {
        return type.getSize();
    }
}
