package symbol_table;

import main.Visitor;

/**
 * Created by donamphuong on 11/11/2016.
 */
public class STRING extends ARRAY {
    public STRING() {
        super((TYPE) Visitor.ST.lookUpAll("char"), 2147483647);
    }

    @Override
    public String getTypeName() {
        return "string";
    }
}
