package back_end.instruction.directives;

import back_end.instruction.Instruction;

/**
 * Created by npd215 on 18/11/16.
 */
public class LTORG implements Instruction {
    @Override
    public String toString() {
        return "\t.ltorg";
    }
}
