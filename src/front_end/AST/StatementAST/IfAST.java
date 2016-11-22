package front_end.AST.StatementAST;

import back_end.data_type.Register;
import front_end.AST.ExpressionAST.ExpressionAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;

/**
 * Created by andikoh on 10/11/2016.
 */
public class IfAST extends StatementAST {
    ExpressionAST expression;
    StatementAST then;
    StatementAST elseSt;

    public IfAST(ParserRuleContext ctx, ExpressionAST expr, StatementAST then, StatementAST elseSt) {
        super(ctx);
        this.expression = expr;
        this.then = then;
        this.elseSt = elseSt;
    }

    @Override
    public void check() {
        //check expressions is valid
        expression.checkNode();
        IDENTIFIER T = Visitor.ST.lookUpAll("bool");
        if (!expression.getType().equals(T)) {
            error("If condition type mismatch");
        }
        //check statements are valid
        then.checkNode();
        elseSt.checkNode();
    }

    @Override
    public void translate() {
        Register result = CodeGen.notUsedRegisters.peek();
        expression.translate();

    }
}
