package back_end.data_type;

public enum Register implements Operand{
    R0,
    R1,
    R2,
    R3,
    R4,
    R5,
    R6,
    R7,
    R8,
    R9,
    R10,
    R11,
    R12,
    SP,
    LR,
    PC;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}