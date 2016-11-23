package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import back_end.instruction.load_store.STORE;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ExpressionAST.IdentAST;
import front_end.AST.ProgramAST;
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
<<<<<<< HEAD:src/AST/AssignmentAST/PairelemAST.java
=======

    }

    @Override
    public void translate() {

>>>>>>> 6b337cab76019fe831f3d79afda5748ab6209f32:src/front_end/AST/AssignmentAST/PairelemAST.java
    }
}
