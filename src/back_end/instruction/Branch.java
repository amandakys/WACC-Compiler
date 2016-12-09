package back_end.instruction;

public class Branch implements Instruction {
    private String condition;
    private String label;

    public Branch(String condition, String label) {
        this.condition = condition;
        this.label = label;
    }

    @Override
    public String toString() {
        return "\tB" + condition + " " + label;
    }

    @Override
    public String getValue() {
        return label;
    }

    @Override
    public boolean toRemove() {
        return false;
    }

    @Override
    public boolean checkNext() {
        return false;
    }
}
