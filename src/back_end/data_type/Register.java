package back_end.data_type;

import java.util.Stack;

public class Register extends Operand {
    public static Register sp = new Register(13);
    public static Register lr = new Register(14);
    public static Register pc = new Register(15);

    //initialising notUsedRegisters available in a program
    public static Stack<Register> notUsedRegisters = new Stack<>();
    public static Stack<Register> paramRegister = new Stack<>();

    private final int NUM_RESERVED_REGS = 11;
    private final int NUM_PARAM_REGS = 3;

    private int id;


    private Register(int id) {
        assert id >= 0 && id <= 15: "Only register between 0 and 15 are available";
        this.id = id;
    }

    @Override
    public String toString() {
        if(id == 13) {
            return "sp";
        } else if(id == 14) {
            return "lr";
        } else if(id == 15) {
            return "pc";
        }

        return "r" + id;
    }

    private void initialiseReg() {
        //reg 4-11 are preserved to be used in the program
        for (int i = NUM_RESERVED_REGS; i > 3; i--) {
            notUsedRegisters.add(new Register(i));
        }

        //reg 0-3 and reg 12 are used to save parameters
        for(int j = NUM_PARAM_REGS; j >=0; j--) {
            paramRegister.add(new Register(j));
        }
        paramRegister.add(new Register(12));
    }
}