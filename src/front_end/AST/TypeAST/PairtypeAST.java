package front_end.AST.TypeAST;

import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.data_type.register.ShiftedReg;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import com.sun.org.apache.bcel.internal.classfile.Code;
import front_end.AST.ProgramAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;
import front_end.symbol_table.TYPE;

/**
 * Created by andikoh on 09/11/2016.
 */
public class PairtypeAST extends TypeAST {
    private PairelemtypeAST first;
    private PairelemtypeAST second;

    private boolean mallocPair = false;

    public PairtypeAST(ParserRuleContext ctx, PairelemtypeAST first, PairelemtypeAST second) {
        super(ctx);
        this.first = first;
        this.second = second;
    }

    @Override
    public void check() {
        first.check();
        second.check();

        identObj = new PAIR(first.getType(), second.getType());
    }

    @Override
    public void translate() {
        if(!mallocPair) {
            CodeGen.main.add(new LOAD(Register.R0, new ImmValue(identObj.getSize() * 2)));
            CodeGen.main.add(new Branch("L", "malloc"));
            CodeGen.main.add(new MOV(Utility.popUnusedReg(), Register.R0));
            mallocPair = true;
        }
    }

    public TYPE typeFirst() {
        return first.getType();
    }

    public TYPE typeSecond() {
        return second.getType();
    }

}