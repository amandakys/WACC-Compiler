package front_end.AST.ExpressionAST;

import back_end.data_type.ImmValue;
import back_end.instruction.load_store.Load;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class IntLiterAST extends ExpressionAST {

    private String intsign; //intsign
    private String value;

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
        if(intsign.equals("")){
            CodeGen.globalMain.add(new Load(getNextRegister(), new ImmValue
                    (value)));
        }
    }
}
