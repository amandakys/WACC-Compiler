package front_end.AST.ExpressionAST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.instruction.load_store.LOAD;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class StringLiterAST extends ExpressionAST{
    private String value;

    public StringLiterAST(ParserRuleContext ctx, String value) {
        super(ctx);
        this.value = value;
        identObj = Visitor.ST.lookUpAll("string");
    }

    @Override
    public void check() {
        checkIfInScope(value);
    }

    @Override
    public void translate() {
        String label = "msg_" + (CodeGen.data.size() - 1)/3;

        Utility.pushData(value);
        Utility.addMain(new LOAD(getRegister(), new LabelExpr(label)));
    }

    @Override
    public void weight() {
        size = 1;
    }

    @Override
    public void IRepresentation() {
        ident = "string_" + value;
        defaultIRep(ident);

        List<ExpressionAST> chars = new ArrayList<>();
        for(int i = 0; i < value.length(); i++) {
            chars.add(new CharLitAST(ctx, String.valueOf(value.charAt(i))));
        }
        (new ArraylitAST(ctx, chars)).IRepresentation();
    }

    public String getValue() {
        return value;
    }
}
