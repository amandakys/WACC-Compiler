package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import com.sun.org.apache.bcel.internal.classfile.Code;
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

        if (!(type.getType() instanceof PAIR)) {
            error("can only take pair types, actual: " + expression.getType().getTypeName());
        } else {
            //expression is a pair

            switch(token) {
                case "fst": identObj = ((PAIR) type.getType()).getFirst();
                    break;
                case "snd": identObj = ((PAIR) type.getType()).getSecond();
                    break;
            }

        }
    }

    @Override
    public void translate() {
        Register before = CodeGen.toPushUnusedReg.peek();

        CodeGen.main.add(new Branch("L", "p_check_null_pointer"));
        CodeGen.main.add(new LOAD(before, new PreIndex(before)));
        CodeGen.main.add(new LOAD(before, new PreIndex(before)));
    }
}
