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
    private Map<String, ShiftedReg> memoryAddress;
    private int jumpSP = 0;

    public SymbolTable(SymbolTable st) {
        dict = new LinkedHashMap<>();
        encSymbolTable = st;
        memoryAddress = new HashMap<>();
    }

    public SymbolTable getEncSymbolTable() {
        return this.encSymbolTable;
    }

    public void add(String name, IDENTIFIER object) {
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

    public int findSize() {
        int size = 0;

        for (IDENTIFIER ident : dict.values()) {
            if (!(ident instanceof FUNCTION)) {
                size += ident.getSize();
            }

        }

        return size;
    }

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
            S = S.getEncSymbolTable();

            if (S == null) {
                break;
            }


           offset++;
       }
       //jumpSP take care of cases where the sp really jump to different position
       //using LDR sp, [sp, #4]! JumpSp is = 0 by default and is set back to 0 after use.
       return S == null ? null : S.getMemoryAddress().get(name).addToShiftVal(offset+jumpSP);
   }

    public Map<String, ShiftedReg> getMemoryAddress() {
        return memoryAddress;
    }

    public int getJumpSP() {
        return jumpSP;
    }

    public void addJumpSP(int jumpSize) {
        jumpSP += jumpSize;
    }

    public void resetJumpSP() {
        jumpSP = 0;
    }

    public int nextAvailableAdd(int index) {
        int result = 0;
        Iterator<Map.Entry<String, IDENTIFIER>> dictIter = dict.entrySet().iterator();

        for(int i = 0; i < index; i++) {
            result += dictIter.next().getValue().getSize();
        }

        return result + 1;
    }
}


