package back_end.instruction.load_store;

import back_end.data_type.Expression;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;

public class LOAD implements Instruction {
    private Register dst;
    private Expression expression;

    public LOAD(Register dst, Expression expression) {
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
