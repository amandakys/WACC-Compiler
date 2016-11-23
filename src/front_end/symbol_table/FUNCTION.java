package front_end.symbol_table;

import main.Visitor;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FUNCTION extends IDENTIFIER {
    private TYPE returntype;
    private List<TYPE> formals;
    private SymbolTable symtab;
    public int nextAdress;
    public int size;

    public FUNCTION(SymbolTable ST,TYPE returntype, List<TYPE> formals) {
        symtab = ST;
        this.returntype = returntype;
        this.formals = formals;

    }

    public int getNextAdress() {
        return nextAdress;
    }

    public void setNextAdress(int nextAdress) {
        this.nextAdress = nextAdress;
    }

    public void setSymbolTable(SymbolTable ST) {
        this.symtab = ST;
    }

    public List<TYPE> getParamList() {
        return formals;
    }

    @Override
    public int getSize() {
        return symtab.findSize();
    }

    @Override
    public TYPE getType() {
        //returns null to void being assigned values that match its return type
        return null;
    }


    public TYPE getReturntype() {
        return this.returntype;
    }

    public SymbolTable getSymtab() {
        return symtab;
    }
}
