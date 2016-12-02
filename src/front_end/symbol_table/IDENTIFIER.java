package front_end.symbol_table;

public abstract class IDENTIFIER {
    public abstract TYPE getType();

    public boolean isDeclarable() {
        return true;
    }

    public abstract int getSize();
}
