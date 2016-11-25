package front_end.AST.AssignmentAST;

import antlr.BasicParser;
import back_end.Error;
import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ExpressionAST.IdentAST;
import front_end.AST.ProgramAST;
import front_end.AST.TypeAST.BasetypeAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.PAIR;

import static back_end.Utility.addMain;

/**
 * Created by tsd15 on 09/11/16.
 */
public class PairelemAST extends AssignrhsAST{
    private String token;
    private ExpressionAST expression;
    private final int PAIR_SIZE = 4;

    private static boolean hasError;

    public PairelemAST(ParserRuleContext ctx, String token, ExpressionAST expression) {
        super(ctx);
        this.token = token;
        this.expression = expression;
        this.hasError = false;
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
        Register r = Utility.popUnusedReg();
        if(Visitor.ST.getAddress(token) != null) {
            addMain(new LOAD(r, new Address(r)));
        }

        String ident = "";

        if(expression instanceof IdentAST) {
            ident = ((IdentAST) expression).getIdent();
        }

        CodeGen.main.add(new LOAD(r, new PreIndex(Register.SP, new ImmValue(Visitor.ST.pairStackSize(ident)))));
        CodeGen.main.add(new MOV(Register.R0, r));

        CodeGen.main.add(new Branch("L", "p_check_null_pointer"));
        CodeGen.main.add(new LOAD(r, new PreIndex(r)));

        if(ctx.getParent() instanceof BasicParser.AssignlhsContext ||
                ctx.getParent() instanceof BasicParser.AssignrhsContext) {
            CodeGen.main.add(new LOAD(r, new PreIndex(r)));
        }

        if(!hasError) {
            Utility.pushData(Error.nullReference);
            CodeGen.endFunctions.add("p_check_null_pointer");
            Utility.throwRuntimeError();
            hasError = true;
        }

    }
}
