package front_end.symbol_table;

import main.Visitor;

/**
 * Created by andikoh on 08/11/2016.
 */
public abstract class TYPE extends IDENTIFIER {
    private String typeName; // this fields is used to get String for lookUp function

    public TYPE(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public TYPE getType() {
        return this;
    }

    public boolean isReturnable() {
        return true;
    }
    
    public String getTypeName() {
        return typeName;

    }
    @Override
    public String toString() {
        return typeName;
    }

    @Override
    public abstract int getSize();
}
