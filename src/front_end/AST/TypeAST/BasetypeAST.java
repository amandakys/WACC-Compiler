package front_end.AST.TypeAST;

import back_end.instruction.Instruction;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;

import java.util.List;

/**
 * Created by andikoh on 09/11/2016.
 */
public class BasetypeAST extends TypeAST {
    String typename;

    public BasetypeAST(ParserRuleContext ctx, String typename) {
        super(ctx);
        this.typename = typename;
    }

    @Override
    public void check() {
        //check type is in symbol table;
        IDENTIFIER T = Visitor.ST.lookUpAll(typename);
        if (T == null) {
            //type does not exist
            error("undefined type");
        }
        identObj = T;
    }

    @Override
    public void translate() {

    }
}
