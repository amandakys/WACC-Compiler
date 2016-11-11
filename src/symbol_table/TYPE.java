package symbol_table;

/**
 * Created by andikoh on 08/11/2016.
 */
public class TYPE extends IDENTIFIER {
    private String typeName; // this fields is used to get String for lookUp function

    public TYPE(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public TYPE getType() {
        return this;
    }

    public boolean isReturnable() {
        return true;
    }
    
    public String getTypeName() {
        return typeName;

    }

    @Override
    public boolean equals(Object o) {


        if ((this instanceof PAIR) && (o instanceof PAIR)) {
            //both are pairs
            TYPE thisFirst = ((PAIR)this.getType()).getFirst();
            TYPE thisSecond = ((PAIR) this.getType()).getSecond();
            TYPE objFirst = ((PAIR) o).getFirst();
            TYPE objSecond = ((PAIR) o).getSecond();



            if (((PAIR) this).getFirst().equals(((PAIR) o).getFirst())) {
                //firsts match, check seconds
                if ((((PAIR) this).getSecond()).equals(((PAIR) o).getSecond())) {
                    return true;
                }
            }
        } else if (!((this instanceof PAIR) || (o instanceof PAIR))) {
            //neither are pairs
            return (this.getTypeName().equals(((TYPE) o).getTypeName()));
        } else {
            //one of them is a pair , the other is a primitive
            //automatically flase
        }

        return false;
    }

}
