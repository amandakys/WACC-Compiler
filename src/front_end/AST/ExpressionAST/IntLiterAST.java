package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.instruction.Branch;
import back_end.instruction.LabelInstr;
import back_end.instruction.data_manipulation.RSBS;
import back_end.instruction.load_store.Load;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

public class IntLiterAST extends ExpressionAST {

    private String intsign; //intsign
    private String value;
    private final String OVERFLOW_ERROR_MESSAGE = "\"OverflowError: the result is too small/large to store in a " +
            "4-byte signed-integer.\n\"";

    public IntLiterAST(ParserRuleContext ctx, String intsign, String value) {
        super(ctx);

        //only leaves digit in the string value, e.g "1-2-3" will be stored as "123"
        this.value = value.replaceAll("\\D+","");;
        this.intsign = intsign;
        identObj = Visitor.ST.lookUpAll("int");
    }

    @Override
    public void check() {
        checkIfInScope(value);
    }

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {
        Utility.addMain(new Load(unusedRegs.pop(), new ImmValue(value)));

        if(intsign.equals("-")){
            //TODO: take care of when int is less than 0
            //negating the number stored in the next available register
            Register next = unusedRegs.pop();
            Utility.addMain(new RSBS(next, next));

            Utility.addMain(new Branch("BLVS", "p_throw_overflow_error"));


            //throw overflow error
            Utility.addFunction(new LabelInstr("p_throw_overflow_error"));
            //the next message that is put inside data will be loaded in the next free param register
            //this message should be an overflow message
            //Utility.addFunction(new Load(Utility.getNextRegister(), new Expression("msg_" + CodeGen.data.size()%3)));
            Utility.addFunction(new Branch("p_throw_runtime_error"));


            //throw runtime error
            Utility.addFunction(new LabelInstr("p_throw_runtime_error"));
            //function p_print_string will be defined after this
            Utility.addFunction(new Branch("p_print_string"));
            //Utility.addFunction(new Mov(Utility.getNextParamReg(), new Operand(intsign + value)));
            Utility.addFunction(new Branch("exit"));

            //(new PrintAST(null, new StringLiterAST(null, OVERFLOW_ERROR_MESSAGE))).translate();
        }
    }
}
