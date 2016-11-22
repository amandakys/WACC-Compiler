package back_end.instruction.data_manipulation;

import back_end.data_type.Operand;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;
import main.CodeGen;

public class MOV implements Instruction {
    private String condition;
    private Register dst;
    private Operand rhs;

    public MOV(Register dst, Operand rhs) {
        this.dst = dst;
        this.rhs = rhs;
        this.condition = "";

        if(rhs instanceof Register && !dst.equals(rhs)) {
            CodeGen.notUsedRegisters.push((Register) rhs);
            CodeGen.notUsedRegisters.remove(dst);
        }


    }

    public MOV(String condition, Register dst, Operand rhs) {
        this.condition = condition;
        this.rhs = rhs;
        this.dst = dst;
    }

    @Override
    public String toString() {
        return "\tMOV" + condition + " " + dst + ", " + rhs;
    }

    @Override
    public String getValue() {
        return rhs.toString();
    }
}
