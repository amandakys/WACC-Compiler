package back_end.instruction.data_manipulation;

import back_end.OptimisationUtility;
import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.Operand;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;

public class MOV implements Instruction {
    private String condition;
    private Register dst;
    private Operand rhs;
    private boolean toRemove = false;
    private boolean checkNext = true;

    public MOV(Register dst, Operand rhs) {
        this.dst = dst;
        this.rhs = rhs;
        this.condition = "";

        if(rhs instanceof Register && !dst.equals(rhs) && rhs != Register.R0) {
            Utility.pushRegister((Register) rhs);

        }

        //set ZERO_FLAG if the value is 0, unset if it is 1
        if(rhs instanceof ImmValue) {
            if (((ImmValue) rhs).getValue().equals("0")) {
                OptimisationUtility.setZeroFlag();
            } else if (((ImmValue) rhs).getValue().equals("1")) {
                OptimisationUtility.unSetZeroFlag();
            }
        }

        if (rhs instanceof Register) {
            if (dst.equals(rhs)) {
                toRemove = true;
                checkNext = false;
            }
        }

    }

    public MOV(String condition, Register dst, Operand rhs) {
        this.condition = condition;
        this.rhs = rhs;
        this.dst = dst;

        if(rhs instanceof Register && !dst.equals(rhs) && rhs != Register.R0) {
            Utility.pushRegister((Register) rhs);

        }

    }

    @Override
    public String toString() {
        return "\tMOV" + condition + " " + dst + ", " + rhs;
    }

    @Override
    public String getValue() {
        return rhs.toString();
    }

    @Override
    public boolean toRemove() {
        return toRemove;
    }

    @Override
    public boolean checkNext() {
        return checkNext;
    }

    public Register getDst() {
        return dst;
    }

    public Operand getRhs() {
        return rhs;
    }
}
