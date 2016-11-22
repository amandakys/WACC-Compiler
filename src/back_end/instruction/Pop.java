package back_end.instruction;

import back_end.data_type.Register;
import main.CodeGen;

public class POP implements Instruction{
    private Register reg;

    public POP(Register reg) {
        this.reg = reg;

        for(int i = CodeGen.toPushParamReg.size() - 1; i >= 0; i--) {
            CodeGen.paramRegister.push(CodeGen.toPushParamReg.get(i));
        }

        for(int j = CodeGen.toPushUnusedReg.size() - 1; j >= 0; j--) {
            CodeGen.notUsedRegisters.push(CodeGen.toPushUnusedReg.get(j));
        }
    }

    @Override
    public String toString() {
        return "\tPOP {" + reg + "}";
    }

    @Override
    public String getValue() {
        return reg.toString();
    }
}