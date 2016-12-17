package front_end.AST.StatementAST;

import antlr.BasicParser;
import back_end.Utility;
import back_end.data_type.*;

import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.AssignmentAST.PairelemAST;
import front_end.AST.Compare;
import front_end.AST.ExpressionAST.*;
import front_end.AST.AssignmentAST.AssignrhsAST;
import front_end.AST.AssignmentAST.CallAST;
import front_end.AST.ProgramAST;
import front_end.AST.TypeAST.ArraytypeAST;
import front_end.AST.TypeAST.PairelemtypeAST;
import front_end.AST.TypeAST.PairtypeAST;
import front_end.AST.TypeAST.TypeAST;
import front_end.symbol_table.*;
import main.CodeGen;
import main.Visitor;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class VarDeclAST extends StatementAST {
    private String ident; //var name
    private TypeAST type;
    private AssignrhsAST rhs;
    private boolean invariant;

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
                int arraysize = ((ARRAY) rhs.getType()).getElem_size();

                IDENTIFIER V = Visitor.ST.lookUp(ident);
                if (V != null) {
                    error(ident + " is already declared");
                } else {
                    IDENTIFIER T = new ARRAY(elementType, arraysize);
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
        ProgramAST.nextAddress = 0;

        //do not malloc a space on the stack if the pair is null
        if(!(rhs instanceof PairliterAST && ((PairliterAST) rhs).getNullStr().equals("null"))) {
            rhs.translate();
        } else {
            CodeGen.main.add(new LOAD(rhs.getRegister(), new ImmValue(0)));
        }

        //increment the next available address (regarding the next available register)
        ProgramAST.nextAddress += identObj.getSize();

        //decrement the next available address (regarding sp)
        Visitor.ST.decrementAddress(identObj.getSize());

        ShiftedReg address = new PreIndex(Register.SP,
                new ImmValue(Visitor.ST.getNextAvailableAddress()));
        Visitor.ST.addToMemoryAddress(ident, address);

        //jumpSP take care of the change in position of Stack pointer whenever it is add or sub
        ShiftedReg addressWithJump = new PreIndex(Register.SP,
                new ImmValue(Visitor.ST.getNextAvailableAddress()+Utility.getJumpSP()));
        CodeGen.main.add(new STORE(rhs.getRegister(), addressWithJump, identObj.getSize()));
    }

    @Override
    public void weight() {
        type.weight();
        rhs.weight();

        size += type.getSize();
        size += rhs.getSize();
    }

    @Override
    public void IRepresentation() {
        rhs.IRepresentation();

        IGNode = new IGNode(ident);
        //register used by the variable must be the same as register used by rhs
        IGNode = rhs.getIGNode();
        IGNode.setIdent();
    }

}