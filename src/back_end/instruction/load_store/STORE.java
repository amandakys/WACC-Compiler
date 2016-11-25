package back_end.instruction.load_store;

import back_end.Utility;
import back_end.data_type.Expression;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;
import front_end.symbol_table.FUNCTION;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;
import main.CodeGen;
import main.Visitor;

/*
    STR instructions store a register value into memory
 */
public class STORE implements Instruction {
    private Register dst;
    private Expression expression;
    private String type = "";


    public STORE(Register dst, Expression expression, int size) {
        this.dst = dst;
        this.expression = expression;

        if (size == 1) {
            this.type = "SB";
        }

        if(dst != Register.R0) {
            Utility.pushRegister(dst);
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
}
