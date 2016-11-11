package AST.StatementAST;

import AST.AssignmentAST.AssignrhsAST;
import AST.TypeAST.PairtypeAST;
import AST.TypeAST.TypeAST;
import AST.Utility;
import main.Visitor;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;
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

        if (type instanceof PairtypeAST) {
            TYPE f = ((PairtypeAST) type).typeFirst();
            TYPE s = ((PairtypeAST) type).typeSecond();
            IDENTIFIER T = new PAIR(f, s);
            IDENTIFIER V = Visitor.ST.lookUp(ident);
            if (V != null) {
                Utility.error(ident + "is already declared");
            } else {
                Visitor.ST.add(ident, T);
            }
        } else {
        String typeName = type.getType().getTypeName();
        IDENTIFIER T = Visitor.ST.lookUpAll(typeName);
        IDENTIFIER V = Visitor.ST.lookUp(ident);

            if(T == null) {
                Utility.error("unknown type " + typeName);
            } else if (!(T instanceof TYPE)) {
                Utility.error(typeName + " is not a type");
            } else if (!(T.isDeclarable())) {
                Utility.error("cannot declare " + typeName + " objects");
            } else if (V != null) {
                Utility.error(ident + " is already declared");
            } else {
                VARIABLE varObj = new VARIABLE((TYPE) T);
                Visitor.ST.add(ident, varObj);
            }
        }
        rhs.check();
        type.checkType(rhs);
    }
}
