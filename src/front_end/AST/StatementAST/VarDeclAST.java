package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.*;

import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.Branch;
import back_end.instruction.load_store.Load;
import back_end.instruction.load_store.Store;
import front_end.AST.AssignmentAST.ArraylitAST;
import front_end.AST.AssignmentAST.AssignrhsAST;
import front_end.AST.AssignmentAST.CallAST;
import front_end.AST.AssignmentAST.NewpairAST;
import front_end.AST.ExpressionAST.BoolliterAST;
import front_end.AST.ExpressionAST.CharLitAST;
import front_end.AST.ExpressionAST.IntLiterAST;
import front_end.AST.ExpressionAST.PairliterAST;
import front_end.AST.ProgramAST;
import front_end.AST.TypeAST.ArraytypeAST;
import front_end.AST.TypeAST.BasetypeAST;
import front_end.AST.TypeAST.PairtypeAST;
import front_end.AST.TypeAST.TypeAST;
import front_end.symbol_table.*;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by dtv15 on 09/11/16.
 */

public class VarDeclAST extends StatementAST {
    private String ident;
    private TypeAST type;
    private AssignrhsAST rhs;

    private static int byte_size = 0;

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
                identObj = new VARIABLE((TYPE) T);
                Visitor.ST.add(ident, identObj);
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
                    identObj = new VARIABLE((TYPE) T);
                    Visitor.ST.add(ident, identObj);
                }
            } else {
                error("declared type and given type do not match");
            }

        } else {
            String typeName = type.getType().getTypeName();
            IDENTIFIER T = Visitor.ST.lookUpAll(typeName);
            IDENTIFIER V = Visitor.ST.lookUp(ident);

            if (T == null) {
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

                if (!(rhs instanceof CallAST) ||
                        (rhs instanceof CallAST && ((CallAST) rhs).isDeclared())) {
                    type.checkType(rhs);
                }
            }
        }
        isChecked = true;
    }

    @Override
    public void translate() {
        if(rhs instanceof ArraylitAST) {
            int arrSize = ((ArraylitAST) rhs).getArraylits().size();
            byte_size = (arrSize + 1) * identObj.getSize();

            CodeGen.main.add(new Load(Register.R0, new ImmValue(byte_size)));
        } else if(rhs instanceof NewpairAST) {
            byte_size = ((PAIR) identObj).getFirst().getSize() +
                    ((PAIR) identObj).getSecond().getSize();
        } else {
            byte_size = ProgramAST.size;
        }

        Register res = CodeGen.notUsedRegisters.peek();
        type.translate();

        rhs.translate();

        if (type instanceof ArraytypeAST) {
            Register value = Utility.popUnusedReg();

            CodeGen.main.add(new Load(value, new ImmValue(((ArraylitAST) rhs).getArraylits().size())));
            CodeGen.main.add(new Store(value, new PreIndex(res), identObj.getSize()));
        }

        ProgramAST.nextAddress += identObj.getSize();

        ShiftedReg address = new PreIndex(Register.SP,
                new ImmValue(ProgramAST.nextAddress - byte_size));
        CodeGen.memoryAddress.put(ident, address);

        //decrement the nextAddress according to the object's byte_size
        CodeGen.main.add(new Store(res, address, identObj.getSize()));
    }
}
