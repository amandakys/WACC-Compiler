package front_end.symbol_table;

/**
 * Created by andikoh on 10/11/2016.
 */
public class PAIR extends TYPE {
    private TYPE first;
    private TYPE second;

    public PAIR(TYPE first, TYPE second) {
        super("pair");
        this.first = first;
        this.second = second;
    }

    @Override
    public TYPE getType() {
        return this;

    }

    public TYPE getFirst() {
        return this.first;
    }

    public TYPE getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "pair(" + first.getTypeName()+ ", " + second.getTypeName() + ")";
    }

    @Override
    public int getSize() {
        //TODO: Implement this
        return 0;
    }
}
