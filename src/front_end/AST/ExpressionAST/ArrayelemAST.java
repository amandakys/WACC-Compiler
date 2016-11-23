package front_end.AST.ExpressionAST;

import antlr.BasicParser;
import back_end.Utility;
import back_end.data_type.Address;
import back_end.data_type.ImmValue;
import back_end.data_type.LabelExpr;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.POP;
import back_end.instruction.PUSH;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import front_end.AST.StatementAST.PrintAST;
import front_end.AST.StatementAST.PrintlnAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;

import java.util.ArrayList;
import java.util.List;

public class ArrayelemAST extends ExpressionAST {
    private String ident;
    private List<Node> expressions = new ArrayList<>();

    private final String NEGATIVE_ARRAY_BOUND = "\"ArrayIndexOutOfBoundsError: negative index\\n\\0\"";
    private final String TOO_LARGE_ARRAY_BOUND = "\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"";
    private final int INT_SIZE = 4;

    public ArrayelemAST(ParserRuleContext ctx, String ident, List<Node> expressionNodes) {
        super(ctx);
        this.ident = ident;
        this.expressions = expressionNodes;
    }

    @Override
    public void check() {
        //check idents
        IDENTIFIER N = Visitor.ST.lookUpAll(ident);
        if (N == null) {
            error("undeclared variable");
        } else {

            for (Node n : expressions) {
                n.checkNode();
                TYPE T = Visitor.ST.lookUpAll("int").getType();
                if (!T.equals(n.getType())) {
                    error("arrayelement only takes integers, actual: " + T.getTypeName());
                }
                identObj = N.getType();
            }
        }
    }

    @Override
    public void translate() {
        Register first = Utility.popUnusedReg();
        Utility.pushData(NEGATIVE_ARRAY_BOUND);
        Utility.pushData(TOO_LARGE_ARRAY_BOUND);

        CodeGen.functions.add(new ADD(first, Register.SP, new ImmValue(ProgramAST.nextAddress)));

        Register reg = CodeGen.notUsedRegisters.peek();
        for(Node n : expressions) {
            n.translate();
        }

        CodeGen.functions.add(new LOAD(first, new Address(first)));
        CodeGen.functions.add(new MOV(Register.R0, reg));
        CodeGen.functions.add(new MOV(Utility.popParamReg(), first));
        CodeGen.functions.add(new Branch("L", "p_check_array_bounds"));

        ProgramAST.nextAddress += identObj.getSize();

        CodeGen.functions.add(new ADD(first, first, new ImmValue(INT_SIZE)));
        //TODO
        //CodeGen.functions.add(new ADD(first, first, new PostIndex()));
        CodeGen.functions.add(new LOAD(first, new Address(first)));
        CodeGen.functions.add(new MOV(Register.R0, first));
        CodeGen.functions.add(new Branch("L", "p_print_int"));

        CodeGen.endFunctions.add("p_check_array_bounds");
        CodeGen.endFunctions.add("p_print_int");
        if(ctx.getParent() instanceof BasicParser.PrintlnContext) {
            CodeGen.endFunctions.add("p_print_ln");
        }
        CodeGen.endFunctions.add("p_throw_runtime_error");
    }

}
