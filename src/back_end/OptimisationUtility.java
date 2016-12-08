package back_end;

import back_end.data_type.Expression;
import back_end.data_type.register.Register;
import back_end.instruction.Instruction;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import com.sun.org.apache.bcel.internal.classfile.Code;
import main.CodeGen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andikoh on 07/12/2016.
 */
public class OptimisationUtility {
    private static boolean ZERO_FLAG = false;
    private static List<Instruction> optimised = new ArrayList<>();

    public static void setZeroFlag() {
        ZERO_FLAG = true;
    }

    public static void unSetZeroFlag() {
        ZERO_FLAG = false;
    }

    public static boolean getZeroFlag() {
        return ZERO_FLAG;
    }

    public static void checkNextAndReplace(Instruction one, Instruction two) {
        if (one instanceof STORE && two instanceof LOAD) {
            //check load instruction is not loading from the same address into the same register
            Register r1 = ((STORE) one).getRegister();
            Expression e1 = ((STORE) one).getExpression();

            Register r2 = ((LOAD) two).getRegister();
            Expression e2 = ((LOAD) two).getExpression();
            if (r1.equals(r2) && e1.equals(e2)) {
                //load is unecesary
                optimised.add(one);
            }
        } else if (one instanceof MOV && two instanceof MOV){
            if (((MOV) two).getRhs() instanceof Register && ((MOV) one).getDst().equals(((MOV) two).getRhs())) {
                //can be simplified
                optimised.add(new MOV(((MOV) two).getDst(), ((MOV) one).getRhs()));
            } else {
                optimised.add(one);
                optimised.add(two);
            }

        } else {
            optimised.add(one);
            optimised.add(two);
        }

    }

    public static void optimiseInstructions() {

        List<Instruction> original = new ArrayList<>();
        original.addAll(CodeGen.text);
        original.addAll(CodeGen.main);
        original.addAll(CodeGen.functions);

        Iterator<Instruction> iter = original.iterator();

        while(iter.hasNext()) {
            Instruction i = iter.next();
            if (!i.toRemove()) {
                optimised.add(i);
            }
            if(i.checkNext()) {
                if (iter.hasNext()) {
                    Instruction next = iter.next();
                    checkNextAndReplace(i, next);
                }
            }
        }
    }

}
