package back_end.instruction;

import back_end.data_type.register.Register;
import main.CodeGen;

public class Pop implements Instruction{
    private Register reg;

    public Pop(Register reg) {
        this.reg = reg;

        while (!CodeGen.toPushParamReg.isEmpty()) {
            Register r = CodeGen.toPushParamReg.pop();

            if(!CodeGen.paramRegister.contains(r)) {
                CodeGen.paramRegister.push(r);
            }
        }

        while (!CodeGen.toPushUnusedReg.isEmpty()) {
            Register r = CodeGen.toPushUnusedReg.pop();

            if(!CodeGen.notUsedRegisters.contains(r)) {
                CodeGen.notUsedRegisters.push(r);
            }
        }
    }

    @Override
    public String toString() {
        return "\tPop {" + reg + "}";
    }

    @Override
    public String getValue() {
        return reg.toString();
    }
}