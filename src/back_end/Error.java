package back_end;

/**
 * Created by andikoh on 24/11/2016.
 */
public class Error {
    public static final String overflow = "\"OverflowError: the result is too small/large to store in a " +
            "4-byte signed-integer.\\n\"";

    public static final String divideByZero = "\"DivideByZeroError: divide or modulo by zero\\n\\0\"";

    public static final String nullReference = "\"NullReferenceError: dereference a null reference\\n\\0\"";

    public static final String arrayOutOfBoundsNegative = "\"ArrayIndexOutOfBoundsError: negative index\\n\\0\"";

    public static final String arrayOutOfBoundsLarge = "\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"";


}
