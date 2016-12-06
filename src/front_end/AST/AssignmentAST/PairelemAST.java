package front_end.AST.AssignmentAST;

import antlr.BasicParser;
import back_end.Error;
import back_end.PrintUtility;
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
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.PAIR;

import static back_end.Utility.addMain;

public class PairelemAST extends AssignrhsAST{
    private String token;
    private ExpressionAST expression;

    private static boolean hasError;
    private final int PAIR_SIZE = 4;

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

        //load the result to a register when necessary (i.e if pairelem is on the rhs, or on lhs)
        if(ctx instanceof BasicParser.PairelemContext
                || ctx.getParent() instanceof BasicParser.PairelemContext) {
            String value = "";

            if(expression instanceof IdentAST) {
                value = ((IdentAST) expression).getIdent();
            }
            CodeGen.main.add(new LOAD(r, new PreIndex(Register.SP,
                    Visitor.ST.getAddress(value).getShiftVal())));
        }

        CodeGen.main.add(new MOV(Register.R0, r));

        int val = token.equals("fst") ? 0 : PAIR_SIZE;

        CodeGen.main.add(new Branch("L", "p_check_null_pointer"));
        CodeGen.main.add(new LOAD(r, new PreIndex(r, new ImmValue(val))));

        if(ctx instanceof BasicParser.PairelementContext
                || ctx.getParent() instanceof BasicParser.PairelementContext) {
            CodeGen.main.add(new LOAD(r, new PreIndex(r, new ImmValue(0))));
        }

        if(!hasError) {
            Utility.pushData(Error.nullReference);
            PrintUtility.addToEndFunctions("p_check_null_pointer");
            PrintUtility.throwRuntimeError();
            hasError = true;
        }
    }

    @Override
    public void weight() {
        expression.weight();
        size = expression.getSize();
    }

    @Override
    public void IRepresentation() {

    }
}