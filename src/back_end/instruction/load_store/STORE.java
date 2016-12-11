package back_end.instruction.load_store;

import back_end.data_type.Expression;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;

/*
    STR instructions store a register value into memory
 */
public class STORE implements Instruction {
    private Register dst;
    private Expression expression;
    private String type = "";
    private boolean checkNext = false;

    public STORE(Register dst, Expression expression, int size) {
        this.dst = dst;
        this.expression = expression;

        //all the size encapsulated inside an IDENTIFIER are byte_size
        if(size == 1) {
            this.type = "B";
        }
    }

    @Override
    public String toString() {
        return "\tSTR" + type + " " + dst + ", " + expression ;
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
        if (expression instanceof PreIndex) {
            checkNext = true;
        }
        return checkNext;
    }

    public Register getRegister() {
        return dst;
    }

    public Expression getExpression() {
        return expression;
    }
}
