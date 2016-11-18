package front_end.AST.ExpressionAST;

import back_end.instruction.Instruction;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class IntLiterAST extends ExpressionAST {

    String intsign; //intsign
    String value;

    public IntLiterAST(ParserRuleContext ctx, String intsign, String value) {
        super(ctx);
        this.value = value;
        this.intsign = intsign;
    }

    @Override
    public void check() {
        checkIfInScope(value);
        identObj = Visitor.ST.lookUpAll("int");
    }

    @Override
    public void translate() {

    }
}