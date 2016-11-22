package back_end.instruction.load_store;

import back_end.data_type.Expression;
import back_end.data_type.ImmValue;
import back_end.data_type.Register;
import back_end.instruction.Instruction;

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
        String expr = expression.toString();

        if(expression instanceof ImmValue) {
            expr = "=" + expr.substring(1);
        }

        return "\tLDR " + dst + ", " + expr;
    }

    @Override
    public String getValue() {
        return expression.toString();
    }
}
