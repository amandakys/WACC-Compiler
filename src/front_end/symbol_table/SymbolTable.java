package front_end.symbol_table;

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

    public SymbolTable getEncSymbolTable() {
        return this.encSymbolTable;
    }

    public void add (String name, IDENTIFIER object) {
        dict.put(name, object);
    }

    public boolean containsValue(IDENTIFIER ident) {
        return dict.containsValue(ident);
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
            S = S.getEncSymbolTable();
        }
        return null; 
    }

    public Map<String, IDENTIFIER> getDict() {
        return dict;
    }

    public int findSize() {
        int size = 0;

        for(IDENTIFIER ident : dict.values()) {
            size += ident.getSize();
        }

        return size;
    }

    public int findSizeType(Class c) {
        int size = 0;

        for (IDENTIFIER iden : dict.values()) {
            if(iden.getClass().equals(c)) {
                size++;
            }
        }

        return size;
    }
}


