package back_end.instruction.data_manipulation;

import back_end.Utility;
import back_end.data_type.Operand;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;
import com.sun.org.apache.regexp.internal.RE;
import main.CodeGen;

public class MOV implements Instruction {
    private String condition;
    private Register dst;
    private Operand rhs;
    private boolean isRedundant = false;

    public MOV(Register dst, Operand rhs) {
        this("", dst, rhs);
    }

    public MOV(String condition, Register dst, Operand rhs) {
        this.condition = condition;
        this.rhs = rhs;
        this.dst = dst;

        if(rhs instanceof Register) {
            if(((Register) rhs).getIndex() == dst.getIndex()) {
                isRedundant = true;
            }
        }
    }

    @Override
    public String toString() {
        return isRedundant ? "" : "\tMOV" + condition + " " + dst + ", " + rhs;
    }

    @Override
    public String getValue() {
        return rhs.toString();
    }
}
