package front_end.symbol_table;

import java.util.*;

import back_end.Utility;
import back_end.data_type.register.ShiftedReg;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private SymbolTable encSymbolTable; //ref to enclosing symbol table
    private Map<String, IDENTIFIER> dict;
    private Map<String, ShiftedReg> memoryAddress;
    private int size;

    public SymbolTable(SymbolTable st) {
        dict = new LinkedHashMap<>();
        encSymbolTable = st;
        memoryAddress = new HashMap<>();
        size = 0;
    }

    public SymbolTable getEncSymbolTable() {
        return this.encSymbolTable;
    }

    public void add(String name, IDENTIFIER object) {
        dict.put(name, object);
    }



    //find the identifier by the name in the symbol table
    public IDENTIFIER lookUp(String name) {
        return dict.get(name);
    }


    //find the identifier by the name in the whole program scope
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

    public int findSize() {
        int size = 0;

        for (IDENTIFIER ident : dict.values()) {
            if (!(ident instanceof FUNCTION) && !(ident == null)) {
                size += ident.getSize();
            }

        }

        return size;
    }


    // Calculate the shift of Stack Pointer due to parameters of function
    public int findStackShift(String x) {
        List<String> keys = new ArrayList<String>(dict.keySet());
        int indexOfx = keys.indexOf(x);
        int shift = 4; //name of the function itself take size 4
        for (int i = 1; i < indexOfx; i++) {
            shift += dict.get(keys.get(i)).getSize();
        }
        return shift;
    }

    public void addToMemoryAddress(String name, ShiftedReg reg) {
        memoryAddress.put(name, reg);
    }

    public ShiftedReg getAddress(String name) {
        SymbolTable S = this;
        int offset = 0;

        while (!S.getMemoryAddress().containsKey(name)) {
            offset += S.findSize();
            S = S.getEncSymbolTable();
        }

        //jumpSP take care of cases where the sp really jump to different position
        //using LDR sp, [sp, #4]! JumpSp is = 0 by default and is set back to 0 after use.
        return S.getMemoryAddress().get(name).addToShiftVal(offset + Utility.getJumpSP());
    }

    public Map<String, ShiftedReg> getMemoryAddress() {
        return memoryAddress;
    }
}
