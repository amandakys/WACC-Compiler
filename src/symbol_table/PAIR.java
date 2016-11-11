package symbol_table;

/**
 * Created by andikoh on 10/11/2016.
 */
public class PAIR extends TYPE{
    private TYPE first;
    private TYPE second;

    public PAIR(TYPE first, TYPE second) {
        super("pair");
        this.first = first;
        this.second = second;
    }

    @Override
    public TYPE getType() {
        return this;

    }

    public TYPE getFirst() {
        return this.first;
    }

    public TYPE getSecond() {
        return this.second;
    }
    //maybe take two identifiers

//
//    public PAIR() {
//        super("pair");
//        this.first = null;
//        this.second = null;
//    }
//    public PAIR(TYPE first, TYPE second) {
//        super("pair");
//        this.first = first;
//        this.second = second;
//    }
//
//    @Override
//    public TYPE getType() {
//        return this;
//    }
//    @Override
//    public boolean equals(Object o) {
//        if (o == null) {//notsure
//            return true;
//        }else if (!(o instanceof PAIR)) {
//            return false;
//        } else {
//            //both are pairs
//            TYPE thisFirst = this.getFirst();
//            TYPE thisSecond = this.getSecond();
//            TYPE objFirst = this.getFirst();
//            TYPE objSecond = this.getSecond();
//
//            if (objFirst == null) {
//                if (objSecond == null) {
//                } else {
//                    return thisSecond.equals(objSecond);
//                }
//            } else {
//                if (thisFirst.equals(objFirst)) {
//                    if (objSecond == null) {
//                        //pass
//                    } else {
//                        return thisSecond.equals(objFirst);
//                    }
//                } else {
//                    return false;
//                }
//            }
////            if (!this.getFirst().equals(((PAIR) o).getFirst())) {
////                return false;
////            } else if (!this.getSecond().equals(((PAIR) o).getSecond()))  {
////                return false;
////            }
//
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "pair(" + first.getTypeName()+ ", " + second.getTypeName() + ")";
    }
}
