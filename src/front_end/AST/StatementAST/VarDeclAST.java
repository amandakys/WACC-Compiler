package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;

import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.load_store.STORE;
import front_end.AST.AssignmentAST.AssignrhsAST;
import front_end.AST.AssignmentAST.CallAST;
import front_end.AST.TypeAST.ArraytypeAST;
import front_end.AST.TypeAST.PairtypeAST;
import front_end.AST.TypeAST.TypeAST;
import front_end.symbol_table.*;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by dtv15 on 09/11/16.
 */

public class VarDeclAST extends StatementAST{
    private String ident;
    private TypeAST type;
    private AssignrhsAST rhs;

    protected static int nextAddress;
    protected static int size;

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
                // TODO:i need to fix this asap
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
        isChecked = true;
    }

    @Override
    public void translate() {

        if(size == 0) {
            size = Visitor.ST.findSizeVarDec();
            nextAddress = size;
        }

        Operand operSize = new ImmValue(size);
        int index = ((VARIABLE) identObj).getIndex();

        if(index == 0) {
            //decrement stack pointer
            Utility.addMain(new SUB(Register.SP, Register.SP, operSize));
        }

        Register nextReg = CodeGen.notUsedRegisters.peek();
        rhs.translate();

        nextAddress = nextAddress - identObj.getSize();

        Utility.addMain(new STORE(nextReg, new PreIndex(Register.SP,
                new ImmValue(nextAddress)), identObj.getSize()));

        if(index == Visitor.ST.findNumVarDec() - 1) {
            //increment stack pointer
            Utility.addMain(new ADD(Register.SP, Register.SP, operSize));
        }
    }

}
