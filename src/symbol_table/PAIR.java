package symbol_table;

/**
 * Created by andikoh on 10/11/2016.
 */
public class PAIR extends TYPE{
    private TYPE first;
    private TYPE second;

    //maybe take two identifiers


    public PAIR(TYPE first, TYPE second) {
        super("pair");
        this.first = first;
        this.second = second;
    }

    @Override
    public TYPE getType() {
        //TODO: implement later
        return null;
    }
}
