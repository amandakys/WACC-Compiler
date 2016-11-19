package back_end.instruction;

import back_end.instruction.Instruction;

/**
 * Created by npd215 on 18/11/16.
 */
public class Directive implements Instruction {
    private String name;

    public Directive(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String res = name.equals("ltorg") ? "\t" : "";
        return res +  "." + name;
    }
}
