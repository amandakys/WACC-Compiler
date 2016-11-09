package symbol_table;

import AST.ParamlistAST;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FUNCTION extends IDENTIFIER {
    TYPE returntype;
    ParamlistAST formals;
    SymbolTable symtab;
    public FUNCTION(SymbolTable ST,TYPE returntype, ParamlistAST formals) {
        this.symtab = ST;
        this.returntype = returntype;
        this.formals = formals;
    }

    public void setSymbolTable(SymbolTable ST) {
        this.symtab = ST;
    }

    public ParamlistAST getParams() {
        return formals;
    }
    @Override
    public TYPE getType() {
        return returntype;
    }
}
