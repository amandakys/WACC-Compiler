package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ExpressionAST.IdentAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.PAIR;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PairelemAST extends AssignrhsAST{
    String token;
    ExpressionAST expression;

    public PairelemAST(ParserRuleContext ctx, String token, ExpressionAST expression) {
        super(ctx);
        this.token = token;
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.checkNode();

        //expression is an ident - referencing a pair
        IDENTIFIER type = Visitor.ST.lookUpAll(((IdentAST) expression).getIdent());

        if (!(type instanceof PAIR)) {
            error("can only take pair types, actual: " + expression.getType().getTypeName());
        } else {
            //expression is a pair

            switch(token) {
                case "fst": identObj = ((PAIR) type).getFirst();
                    break;
                case "snd": identObj = ((PAIR) type).getSecond();
                    break;
            }

        }
    }

    @Override
    public void translate() {
        CodeGen.main.add(new Branch("L", "malloc"));
        CodeGen.main.add(new MOV(Utility.popUnusedReg(), Register.R0));
    }
}
