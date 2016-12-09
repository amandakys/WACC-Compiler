package back_end.instruction.load_store;

import back_end.data_type.Expression;
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;
import front_end.symbol_table.IDENTIFIER;
import main.Visitor;

public class LOAD implements Instruction {
    private String condition = "";
    private Register dst;
    private Expression expression;

    public LOAD(Register dst, Expression expression) {
        this.dst = dst;
        this.expression = expression;
    }

    public LOAD(Register dst, Expression expression, IDENTIFIER identifier) {
        this.dst = dst;
        this.expression = expression;
        //if it is a char or boolean
        if(Visitor.ST.lookUpAll("bool").equals(identifier)
                || Visitor.ST.lookUpAll("char").equals(identifier)) {
            condition = "SB";
        }
    }

    public LOAD(String condition, Register dst, Expression expression) {
        this.condition = condition;
        this.dst = dst;
        this.expression = expression;
    }

    @Override
    public String toString() {
        String expr = expression.toString();

        if(expression instanceof ImmValue) {
            expr = "=" + expr.substring(1);
        }

        return "\tLDR" + condition + " " + dst + ", " + expr;
    }

    @Override
    public String getValue() {
        return expression.toString();
    }

    @Override
    public boolean toRemove() {
        return false;
    }

    @Override
    public boolean checkNext() {
        return false;
    }

    public Register getRegister() {
        return dst;
    }

    public Expression getExpression() {
        return expression;
    }
}
