package front_end.symbol_table;

import main.Visitor;

public class STRING extends ARRAY {
    public STRING() {
        super((TYPE) Visitor.ST.lookUpAll("char"), (int) Math.pow(2, 31) - 1);
    }

    @Override
    public String getTypeName() {
        return "string";
    }
}
