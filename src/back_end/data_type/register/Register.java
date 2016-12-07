package back_end.data_type.register;

import back_end.data_type.Operand;

public class Register implements Operand {
    public static Register R0 = new Register(0);
    public static Register SP = new Register(13);
    public static Register LR = new Register(14);
    public static Register PC = new Register(15);

    private int index;

    public Register(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        if(index == 13) {
            return "sp";
        } else if(index == 14) {
            return "lr";
        } else if(index == 15) {
            return "pc";
        }

        return "r" + index;
    }
}