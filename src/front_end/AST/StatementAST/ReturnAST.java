package front_end.AST.StatementAST;

import back_end.data_type.register.Register;


import back_end.instruction.data_manipulation.MOV;
import front_end.AST.Compare;

import front_end.AST.Node;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;



/**
 * Created by tsd15 on 09/11/16.
 */
public class ReturnAST extends StatementAST {
    Node expression;
    public ReturnAST(ParserRuleContext ctx, Node expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        //check that return expression returns correct type
        expression.checkNode();
    }


    @Override
    public void translate() {
        Register result = CodeGen.notUsedRegisters.peek();

        expression.translate();

        CodeGen.main.add(new MOV(Register.R0, result));
    }
}
