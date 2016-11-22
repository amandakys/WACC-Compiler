package front_end.symbol_table;

import main.Visitor;

/**
 * Created by andikoh on 08/11/2016.
 */
public class VARIABLE extends IDENTIFIER {
    private TYPE type;
    private int index;

    public VARIABLE(TYPE type) {
        this.type = type;

        for(IDENTIFIER ident : Visitor.ST.getDict().values()) {
            if(ident instanceof VARIABLE) {
                index++;
            }
        }
    }

    public TYPE getType() {
        return this.type;
    }

    @Override
    public int getSize() {
        return type.getSize();
    }

    public int getIndex() {
        return index;
    }
}
