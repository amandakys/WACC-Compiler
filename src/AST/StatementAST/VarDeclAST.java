package AST.StatementAST;

import AST.AssignmentAST.AssignrhsAST;
import AST.TypeAST.TypeAST;
import AST.Utility;
import main.Visitor;
import symbol_table.FUNCTION;
import symbol_table.IDENTIFIER;
import symbol_table.TYPE;
import symbol_table.VARIABLE;

/**
 * Created by dtv15 on 09/11/16.
 */

public class VarDeclAST extends StatementAST{
    private String ident;
    private TypeAST type;
    private AssignrhsAST rhs;

    public VarDeclAST(TypeAST type, String ident, AssignrhsAST rhs) {
        super();
        this.ident = ident;
        this.type = type;
        this.rhs = rhs;
    }

    @Override
    public void check() {
        type.check();
        String typeName = type.getType().getTypeName();
        IDENTIFIER T = Visitor.ST.lookUpAll(typeName);
        IDENTIFIER V = Visitor.ST.lookUp(ident);

        if(T == null) {
            Utility.error("unknown type " + typeName);
        } else if (!(T instanceof TYPE)) {
            Utility.error(typeName + " is not a type");
        } else if (!(T.isDeclarable())) {
            Utility.error("cannot declare " + typeName + " objects");
        } else if (V != null && !(V instanceof FUNCTION)) {
            Utility.error(ident + " is already declared");
        } else {
            if(V == null) {
                identObj = new VARIABLE((TYPE) T);
                Visitor.ST.add(ident, identObj);
            }

            //Checking rhs
            rhs.check();
            type.checkType(rhs);
        }
    }
}
