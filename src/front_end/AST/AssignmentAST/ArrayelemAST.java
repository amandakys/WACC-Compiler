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
        }
        identObj = N;
    }

    @Override
    public void translate() {
        int arraySize = ((ARRAY)identObj).getElem_size();
        int total = 1;
        for (Node n : expressions) {
            total *= ((IntLiterAST) n).getValue();
        }

        if(total > arraySize) {

        }
    }
}
