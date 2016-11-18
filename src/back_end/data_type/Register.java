package back_end.data_type;

/**
 * Created by npd215 on 18/11/16.
 */
public class Register extends Operand {
    private int id;

    public Register(int id) {
        assert id >= 0 && id <= 15: "Only register between 0 and 15 are available";
        this.id = id;
    }

    @Override
    public String toString() {
        if(id==13) {
            return "sp";
        } else if(id == 14) {
            return "lr";
        } else if(id == 15) {
            return "pc";
        }

        return "r" + id;
    }
}