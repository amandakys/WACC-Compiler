package back_end.data_type;

/*
    ImmValue is used to describe values of primitive types - int, char,...
 */
public class ImmValue extends Expression {
    private String value;
    private boolean isLetter;

    public ImmValue(int value) {
        this.value = String.valueOf(value);
        isLetter = false;
    }

    public ImmValue(String value) {
        this.value = value;
        isLetter = Character.isLetter(value.charAt(0));
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return isLetter ? "#\'" + value + "\'" : "#" + value;
    }
}
