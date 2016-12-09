package front_end.AST.StatementAST;

import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;

import back_end.Error;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.symbol_table.ARRAY;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

public class FreeAST extends StatementAST {
    ExpressionAST expression;

    public FreeAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.checkNode();

        if (!(expression.getType() instanceof PAIR)) {
            error("free must take a pair type");
        }
    }

    @Override
    public void translate() {
        CodeGen.main.add(new LOAD(getRegister(), new Address(Register.SP)));
        CodeGen.main.add(new MOV(Register.R0, getRegister()));
        //updating registers after MOV instruction
        setRegister(Register.R0);

        PrintUtility.throwRuntimeError();
        Utility.pushData(Error.nullReference);

        if (expression.getType() instanceof PAIR) {
            CodeGen.main.add(new Branch("L", "p_free_pair"));
            PrintUtility.addToEndFunctions(("p_free_pair"), getRegister());
        } else if (expression.getType() instanceof ARRAY) {
            CodeGen.main.add(new Branch("L", "p_free_array"));
            PrintUtility.addToEndFunctions(("p_free_array"), getRegister());
        }
    }

    @Override
    public void weight() {
        expression.weight();
        size = expression.getSize();
    }

    @Override
    public void IRepresentation() {
        expression.IRepresentation();
        IGNode = expression.getIGNode();

        //print string is added as double free runtime error might be thrown
        print_stringIR();
    }
}
