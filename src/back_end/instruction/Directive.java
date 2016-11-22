package back_end.instruction;

public class Directive implements Instruction {
    private String name;
    private String value;

    public Directive(String name) {
        this.name = name;
        value = "";
    }

    public Directive(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        String res = "\t";

        if(name.equals("text") || name.equals("data") || name.equals("global main")) {
          res = "";
        }

        return res +  "." + name + " " + value;
    }

    @Override
    public String getValue() {
        return name;
    }
}
