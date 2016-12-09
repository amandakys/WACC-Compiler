package front_end.AST.StatementAST;

import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;

import back_end.instruction.data_manipulation.MOV;
import back_end.Error;
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
        Register value = Utility.popUnusedReg();
        CodeGen.main.add(new LOAD(value, new Address(Register.SP)));
        CodeGen.main.add(new MOV(Register.R0, value));
        PrintUtility.throwRuntimeError();
        Utility.pushData(Error.nullReference);
        if (expression.getType() instanceof PAIR) {
            CodeGen.main.add(new Branch("L", "p_free_pair"));
            PrintUtility.addToEndFunctions(("p_free_pair"));
        } else if (expression.getType() instanceof ARRAY) {
            CodeGen.main.add(new Branch("L", "p_free_array"));
            PrintUtility.addToEndFunctions(("p_free_array"));
        }
    }

}
