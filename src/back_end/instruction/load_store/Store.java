package back_end.instruction.load_store;

import back_end.data_type.Expression;
import back_end.data_type.Register;
import back_end.instruction.Instruction;

/*
    STR instructions store a register value into memory
 */
public class Store implements Instruction {
    private Register dst;
    //TODO: chage it to expression that includes shifted register & address & label
    private Expression expression;

    public Store(Register dst, Expression expression) {
        this.dst = dst;
        this.expression = expression;
    }

    @Override
    public String toString() {
        //TODO: It's not STRB all the time
        return "\tSTRB " + dst + ", " + expression ;
    }

    @Override
    public String getValue() {
        return expression.toString();
    }
}
