package front_end.symbol_table;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class IDENTIFIER {
    public abstract TYPE getType();
    public boolean isDeclarable() {
        return true;
    }
}
