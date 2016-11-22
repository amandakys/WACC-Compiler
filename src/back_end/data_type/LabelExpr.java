package back_end.data_type;

/**
 * Created by donamphuong on 21/11/2016.
 */
public class LabelExpr extends Expression {
    private String label;

    public LabelExpr(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "=" + label;
    }
}

