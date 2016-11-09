package symbol_table;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PARAM extends IDENTIFIER {
    TYPE type;

    @Override
    public TYPE getType() {
        return type;
    }
}
