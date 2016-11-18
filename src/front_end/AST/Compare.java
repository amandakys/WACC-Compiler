package front_end.AST;

import front_end.symbol_table.ARRAY;
import front_end.symbol_table.PAIR;
import front_end.symbol_table.TYPE;

/**
 * Created by andikoh on 11/11/2016.
 */
public class Compare{

    public static boolean types (TYPE t1, TYPE t2) {
        if ((t1 instanceof PAIR) && (t2 instanceof  PAIR)) {
            return pairs((PAIR) t1,(PAIR)t2);
        } else if ((t1 instanceof ARRAY) && (t2 instanceof ARRAY)) {
            return arrays((ARRAY) t1, (ARRAY) t2);
        } else {
            //primitive type
            return t1.equals(t2);
        }
    }

    public static boolean pairs(PAIR p1, PAIR p2) {
        if (p1.getFirst() == null || p2.getFirst() == null) {
            if (p1.getSecond() == null || p2.getSecond() == null) {
                return true;
            } else {
                //compare types of second elements
                return types(p1.getSecond(), p2.getSecond());
            }
        } else{
            //both first elementes are not null
            if (p1.getSecond() == null || p2.getSecond() == null) {
                return types(p1.getFirst(), p2.getFirst());
            } else {
                return types(p1.getFirst(), p2.getFirst()) && types (p1.getSecond(), p2.getSecond());
            }
        }
    }

    public static boolean arrays(ARRAY a1, ARRAY a2) {
            return types(a1.getElementType(), a2.getElementType());
    }




}
