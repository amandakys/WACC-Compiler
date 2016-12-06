package optimisation;

public class IGRegister {
    private int index;

    public IGRegister(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "R" + index;
    }
}
