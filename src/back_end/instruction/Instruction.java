package back_end.instruction;

public interface Instruction {
    @Override
    String toString();

    String getValue();

    boolean toRemove();

    boolean checkNext();
}
