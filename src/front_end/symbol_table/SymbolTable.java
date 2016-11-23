package front_end.symbol_table;

import java.util.*;

import back_end.data_type.register.ShiftedReg;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by andikoh on 08/11/2016.
 */
public class SymbolTable {
    private SymbolTable encSymbolTable; //ref to enclosing symbol table
    private Map<String, IDENTIFIER> dict;
    private Map<String, ShiftedReg> memoryAddress = new HashMap<>();

    public SymbolTable(SymbolTable st) {
        dict= new LinkedHashMap<>();
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
            if (!(ident instanceof  FUNCTION)) {
                size += ident.getSize();
            }

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

    public int findStackShift(String x) {
        List<String> keys = new ArrayList<String>(dict.keySet());
        int indexOfx = keys.indexOf(x);
        int shift = 4;
        for (int i = 1; i < indexOfx; i++) {
            shift+=dict.get(keys.get(i)).getSize();
        }
        return shift;
    }



   public void addToMemoryAddress(String name, ShiftedReg reg) {
       memoryAddress.put(name, reg);
   }

   public ShiftedReg getAddress(String name) {
       return memoryAddress.get(name);
   }

}


