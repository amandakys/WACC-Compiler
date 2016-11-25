package front_end.symbol_table;

public class PAIR extends TYPE {
    private TYPE first;
    private TYPE second;
    private int typeSize = 4; // size of a pair itself without fst and snd is 4

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
        return typeSize ;
    }
}
