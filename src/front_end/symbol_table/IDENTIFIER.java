package front_end.symbol_table;

import back_end.data_type.register.Register;
import optimisation.IGNode;
import optimisation.InterferenceGraph;

public abstract class IDENTIFIER {
    public abstract TYPE getType();

    public boolean isDeclarable() {
        return true;
    }

    public abstract int getSize();
}
