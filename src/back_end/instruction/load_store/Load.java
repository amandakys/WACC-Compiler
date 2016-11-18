package back_end.instruction.load_store;

import back_end.data_type.Expression;
import back_end.data_type.Operand;
import back_end.data_type.Register;
import back_end.instruction.Instruction;

/**
 * Created by npd215 on 18/11/16.
 */
public class Load implements Instruction {
    private Register dst;
    //TODO: chage it to expression that includes shifted register & address & label
    private Expression expression;

    public Load(Register dst, Expression expression) {
        this.dst = dst;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "\tLDR " + dst + ", " + expression ;
    }
}
