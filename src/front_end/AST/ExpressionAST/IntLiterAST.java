package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.data_manipulation.RSBS;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class IntLiterAST extends ExpressionAST {

    private String intsign; //intsign
    private String value;
    private final String OVERFLOW_ERROR_MESSAGE = "\"OverflowError: the result is too small/large to store in a " +
            "4-byte signed-integer.\n\"";

    public static int occurences = 0;

    public IntLiterAST(ParserRuleContext ctx, String intsign, String value) {
        super(ctx);

        //only leaves digit in the string value, e.g "1-2-3" will be stored as "123"
        this.value = value.replaceAll("\\D+","");;
        this.intsign = intsign;
        identObj = Visitor.ST.lookUpAll("int");
        occurences++;
    }

    @Override
    public void check() {
        checkIfInScope(value);
    }

    @Override
    public void translate() {
        Utility.addMain(new LOAD(Utility.popUnusedReg(), new ImmValue(value)));

        if(intsign.equals("-")){
            //TODO: take care of when int is less than 0
//            //negating the number stored in the next available register
//            Register next = CodeGen.notUsedRegisters.pop();
//            Utility.addMain(new RSBS(next, next));
//
//            Utility.addMain(new Branch("LVS", "p_throw_overflow_error"));
//
//
//            //throw overflow error
//            Utility.addFunction(new LabelInstr("p_throw_overflow_error"));
//            //the next message that is put inside data will be loaded in the next free param register
//            //this message should be an overflow message
//            //Utility.addFunction(new Load(Utility.getNextRegister(), new Expression("msg_" + CodeGen.data.size()%3)));
//            Utility.addFunction(new Branch("L", "p_throw_runtime_error"));
//
//
//            //throw runtime error
//            Utility.addFunction(new LabelInstr("p_throw_runtime_error"));
//            //function p_print_string will be defined after this
//            Utility.addFunction(new Branch("L", "p_print_string"));
//            //Utility.addFunction(new MOV(Utility.getNextParamReg(), new Operand(intsign + value)));
//            Utility.addFunction(new Branch("L", "exit"));

            //(new PrintAST(null, new StringLiterAST(null, OVERFLOW_ERROR_MESSAGE))).translate();
        }
    }
}
