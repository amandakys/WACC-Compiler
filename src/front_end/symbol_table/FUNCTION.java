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

    public FUNCTION(SymbolTable ST,TYPE returntype, List<TYPE> formals) {
        symtab = ST;
        this.returntype = returntype;
        this.formals = formals;

    }

    public void setSymbolTable(SymbolTable ST) {
        this.symtab = ST;
    }

    public List<TYPE> getParamList() {
        return formals;
    }

    @Override
    public TYPE getType() {
        //returns null to void being assigned values that match its return type
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    public TYPE getReturntype() {
        return this.returntype;
    }
}
