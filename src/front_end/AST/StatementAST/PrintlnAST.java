package front_end.AST.StatementAST;

import back_end.PrintUtility;
import back_end.instruction.Branch;
import front_end.AST.ExpressionAST.*;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Utility.*;

public class PrintlnAST extends StatementAST {
    private ExpressionAST expression;
    private PrintAST printAST;

    public PrintlnAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
        this.printAST = new PrintAST(null, expression);
    }

    @Override
    public void check() {
        expression.checkNode();
    }

    @Override
    public void translate() {
        printAST.translate();
        addMain(new Branch("L", "p_print_ln"));
        PrintUtility.addToPlaceholders("\"\\0\"");
        PrintUtility.addToEndFunctions("p_print_ln", getRegister());
    }

    public ExpressionAST getExpression() {
        return expression;
    }

    @Override
    public void weight() {
        expression.weight();
        size = expression.getSize();
    }

    @Override
    public void IRepresentation() {
        printAST.IRepresentation();
        IGNode = printAST.getIGNode();
    }
}
