package back_end;
/**
 * Created by npd215 on 23/11/16.
 */
import back_end.data_type.ImmValue;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.data_manipulation.MOV;
import main.CodeGen;

/**
 * Created by donamphuong on 23/11/2016.
 */
public class RuntimeError {
    private static int EXIT_CODE = -1;

    public static void throwRuntimeError() {
        CodeGen.functions.add(new LabelInstr("p_throw_runtime_error"));
        CodeGen.functions.add(new Branch("L", "p_print_string"));
        CodeGen.functions.add(new MOV(Register.R0, new ImmValue(EXIT_CODE)));
        CodeGen.functions.add(new Branch("L", "exit"));
    }

}
