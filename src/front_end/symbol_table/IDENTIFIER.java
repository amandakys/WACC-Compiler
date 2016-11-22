package front_end.symbol_table;

import main.Visitor;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class IDENTIFIER {
    public abstract TYPE getType();

    public boolean isDeclarable() {
        return true;
    }

    public abstract int getSize();
}
