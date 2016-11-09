package symbol_table;

/**
 * Created by andikoh on 08/11/2016.
 */
public class TYPE extends IDENTIFIER {
    String typeName; // this fields is used to get String for lookUp function

    public TYPE(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public TYPE getType() {
        return this; // WHAT DOES THIS DO ?
    }

    public String getTypeName() {
        return typeName;
    }

}
