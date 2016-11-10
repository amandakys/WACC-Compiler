package symbol_table;

/**
 * Created by andikoh on 10/11/2016.
 */
public class PAIR extends IDENTIFIER {
    TYPE first;
    TYPE second;

    //maybe take two identifiers


    public PAIR(TYPE first, TYPE second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public TYPE getType() {
        //TODO: implement later
        return null;
    }
}
