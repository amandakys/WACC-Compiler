package symbol_table;

import AST.ParamlistAST;

import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FUNCTION extends IDENTIFIER {
    TYPE returntype;
    List<TYPE> formals;
    SymbolTable symtab;

    public FUNCTION(SymbolTable ST,TYPE returntype, List<TYPE> formals) {
        this.symtab = ST;
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
        return returntype;
    }
}
