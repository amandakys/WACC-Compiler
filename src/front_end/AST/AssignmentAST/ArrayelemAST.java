package front_end.AST.AssignmentAST;

import back_end.Utility;
import back_end.data_type.register.Register;
import back_end.instruction.Branch;
import back_end.instruction.data_manipulation.MOV;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.Node;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.IDENTIFIER;
import front_end.symbol_table.TYPE;

import java.util.ArrayList;
import java.util.List;

public class ArrayelemAST extends ExpressionAST {
    String ident;
    List<Node> expressions = new ArrayList<>();

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
            }

            identObj = ((ARRAY) N.getType()).getElementType();
        }
    }

    @Override
    public void translate() {
        CodeGen.main.add(new Branch("L", "malloc"));
        CodeGen.main.add(new MOV(Utility.popUnusedReg(), Register.R0));
    }
}
