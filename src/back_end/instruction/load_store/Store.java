package back_end.instruction.load_store;

import back_end.data_type.Expression;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;
import main.CodeGen;

/*
    STR instructions store a register value into memory
 */
public class STORE implements Instruction {
    private Register dst;
    private Expression expression;
    private String type;

    public STORE(Register dst, Expression expression, int size) {
        this.dst = dst;
        this.expression = expression;

        if(size == 1) {
            type = "B";
        } else {
            type = "";
        }

        CodeGen.notUsedRegisters.push(dst);
    }

    @Override
    public String toString() {
        return "\tSTR" + type + " " + dst + ", " + expression ;
    }

    @Override
    public String getValue() {
        return expression.toString();
    }
}
