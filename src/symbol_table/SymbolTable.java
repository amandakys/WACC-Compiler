package symbol_table;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andikoh on 08/11/2016.
 */
public class SymbolTable {
    private SymbolTable encSymbolTable; //ref to enclosing symbol table
    private Map<String, IDENTIFIER> dict;

    public SymbolTable(SymbolTable st) {
        dict= new HashMap<>();
        encSymbolTable = st;
    }

    public void add (String name, IDENTIFIER object) {
        dict.put(name, object);
    }

    public IDENTIFIER lookUp(String name) {
        return dict.get(name);
    }

    public IDENTIFIER lookUpAll(String name) {
        SymbolTable S = this;
        while (S != null) {
            IDENTIFIER obj = S.lookUp(name);
            if (obj != null) {
                return obj;
            }
            S = encSymbolTable;
        }
        return null; 
    }
}


