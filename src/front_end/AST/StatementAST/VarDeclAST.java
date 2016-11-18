package front_end.AST.StatementAST;

import back_end.instruction.Instruction;
import front_end.AST.AssignmentAST.AssignrhsAST;
import front_end.AST.AssignmentAST.CallAST;
import front_end.AST.TypeAST.ArraytypeAST;
import front_end.AST.TypeAST.PairtypeAST;
import front_end.AST.TypeAST.TypeAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.*;

import java.util.List;

/**
 * Created by dtv15 on 09/11/16.
 */

public class VarDeclAST extends StatementAST{
    private String ident;
    private TypeAST type;
    private AssignrhsAST rhs;

    public VarDeclAST(ParserRuleContext ctx, TypeAST type, String ident, AssignrhsAST rhs) {
        super(ctx);
        this.ident = ident;
        this.type = type;
        this.rhs = rhs;
    }

    @Override
    public void check() {
        type.checkNode();

        if (type instanceof PairtypeAST) {
            TYPE f = ((PairtypeAST) type).typeFirst();
            TYPE s = ((PairtypeAST) type).typeSecond();
            IDENTIFIER T = new PAIR(f, s);
            IDENTIFIER V = Visitor.ST.lookUp(ident);
            if (V != null) {
                error(ident + " is already declared");
            } else {
                Visitor.ST.add(ident, T);
            }
        } else if (type instanceof ArraytypeAST) {
            //assumes that this means rhs MUST be an arraylit
            if (rhs.getType() instanceof ARRAY) {
                TYPE elementType = ((ArraytypeAST) type).getelementType();
                //i need to fix this asap
                //int arraysize = ((ArraylitAST) rhs).getSize();


                IDENTIFIER V = Visitor.ST.lookUp(ident);
                if (V != null) {
                    error(ident + " is already declared");
                } else {
                    IDENTIFIER T = new ARRAY(elementType, 0);
                    Visitor.ST.add(ident, T);
                }
            } else {
                error("declared type and given type do not match");
            }

        } else {
            String typeName = type.getType().getTypeName();
            IDENTIFIER T = Visitor.ST.lookUpAll(typeName);
            IDENTIFIER V = Visitor.ST.lookUp(ident);

            if(T == null) {
                error("unknown type " + typeName);
            } else if (!(T instanceof TYPE)) {
                error(typeName + " is not a type");
            } else if (!(T.isDeclarable())) {
                error("cannot declare " + typeName + " objects");
            } else if (V != null && !(V instanceof FUNCTION)) {
                error(ident + " is already declared");
            } else {
                identObj = new VARIABLE((TYPE) T);
                Visitor.ST.add(ident, identObj);

                //Checking rhs
                rhs.checkNode();

                if(!(rhs instanceof CallAST) ||
                        (rhs instanceof CallAST && ((CallAST) rhs).isDeclared())) {
                    type.checkType(rhs);
                }
            }
        }
    }

    @Override
    public void translate() {

    }
}
