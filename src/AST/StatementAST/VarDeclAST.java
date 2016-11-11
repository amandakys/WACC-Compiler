package AST.StatementAST;

import AST.AssignmentAST.ArraylitAST;
import AST.AssignmentAST.AssignrhsAST;
import AST.TypeAST.ArraytypeAST;
import AST.TypeAST.PairtypeAST;
import AST.TypeAST.TypeAST;
import AST.Utility;
import main.Visitor;
import symbol_table.*;

/**
 * Created by dtv15 on 09/11/16.
 */

public class VarDeclAST extends StatementAST{
    private String ident;
    private TypeAST type;
    private AssignrhsAST rhs;
    private boolean isChecked;

    public VarDeclAST(TypeAST type, String ident, AssignrhsAST rhs) {
        super();
        this.ident = ident;
        this.type = type;
        this.rhs = rhs;
        isChecked = false;
    }

    @Override
    public void check() {
        if(isChecked) return;
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
        } else if (type instanceof ArraytypeAST) {
            //assumes that this means rhs MUST be an arraylit
            if (rhs instanceof ArraylitAST) {
                TYPE elementType = ((ArraytypeAST) type).getelementType();
                int arraysize = ((ArraylitAST) rhs).getSize();
                IDENTIFIER V = Visitor.ST.lookUp(ident);
                if (V != null) {
                    Utility.error(ident + " is already declared");
                } else {
                    IDENTIFIER T = new ARRAY(elementType, arraysize);
                    Visitor.ST.add(ident, T);
                }
            } else {
                Utility.error("declared type and given type do not match");
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
            isChecked = true;
            }
        }
    }
}
