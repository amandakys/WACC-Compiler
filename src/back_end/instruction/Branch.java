package back_end.instruction;

public class Branch implements Instruction {
    private String type;
    private String label;

    public Branch(String label) {
        this.type = "BL";
        this.label = label;
    }

    public Branch(String type, String label) {
        this.type = type;
        this.label = label;
    }

    @Override
    public String toString() {
        return "\t" + type + " " + label;
    }

    @Override
    public String getValue() {
        return label;
    }
}
