package back_end.instruction;

/**
 * Created by donamphuong on 19/11/2016.
 */
public class Branch implements Instruction {
    private String label;

    public Branch(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BL " + label;
    }
}
