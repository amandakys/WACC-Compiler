package front_end.AST.StatementAST;

import back_end.PrintUtility;
import back_end.Utility;
import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.*;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.AssignmentAST.ArrayelemAST;
import front_end.AST.AssignmentAST.PairelemAST;
import front_end.AST.ExpressionAST.*;
import front_end.symbol_table.ARRAY;
import front_end.symbol_table.TYPE;
import main.CodeGen;
import optimisation.IGNode;
import optimisation.InterferenceGraph;
import org.antlr.v4.runtime.ParserRuleContext;

import static back_end.Utility.*;

public class PrintAST extends StatementAST {
    private ExpressionAST expression;

    public PrintAST(ParserRuleContext ctx, ExpressionAST expression) {
        super(ctx);
        this.expression = expression;
    }

    @Override
    public void check() {
        expression.checkNode();
    }

    @Override
    public void translate() {
        //Result of expression.translate() is stored CodeGen.notUsedRegisters.pop().Final result is stored
        // in R0 so peek at the register to move the value from CodeGen.notUsedRegisters.pop() to R0
        expression.translate();

        addMain(new MOV(Register.R0, expression.getRegister()));

        pushPlaceholder();
    }

    @Override
    public void weight() {
        expression.weight();
        size = expression.getSize();
    }

    @Override
    public void IRepresentation() {
        StatementIRepresentation("print");
        //p_read and read must be alive at the same time as they both come from ReadAST
        IGNode p_print = new IGNode("p_print_" + findTypeName());
        InterferenceGraph.nodes.add(p_print);

        IGNode.addEdge(p_print);
        expression.setIGNode(IGNode);
    }

    /*
        This function find the appropriate placeholder depending on the type of expression, and push it
        if it does not exist in the CodeGen.data list. It also branch out to the appropriate function that
        prints expression ("putchar", "p_print_string"...)
     */
    private void pushPlaceholder() {
        String placeholder = "";
        String typeName = findTypeName();
        String functionName = "p_print_" + typeName;

        switch (typeName) {
            case "string":
                PrintUtility.addToPlaceholders("\"%.*s\\0\"");
                break;
            case "int":
                PrintUtility.addToPlaceholders("\"%d\\0\"");
                break;
            case "char":
                functionName = "putchar";
                break;
            case "bool":
                PrintUtility.addToPlaceholders("\"true\\0\"");
                PrintUtility.addToPlaceholders("\"false\\0\"");
                break;
            case "reference":
                PrintUtility.addToPlaceholders("\"%p\\0\"");
        }

        addMain(new Branch("L", functionName));

        if (!hasPlaceholder(placeholder)) {
            //when expression is a boolLiter, push both true and false to CodeGen.data
            if (expression instanceof BoolliterAST) {
                PrintUtility.addToPlaceholders("\"true\\0\"");
                PrintUtility.addToPlaceholders("\"false\\0\"");
            } else {
                if (!placeholder.equals("")) {
                    PrintUtility.addToPlaceholders(placeholder);
                }
            }
        }

            PrintUtility.addToEndFunctions(functionName);
    }

    private String findTypeName() {
        String typeName;
        TYPE type = expression.getType();

        switch (type.getTypeName()) {
            case "array":
                if (((ARRAY) type).getElementType().getTypeName().equals("char")) {
                    typeName = "string";
                } else {
                    typeName = "reference";
                }
                break;
            case "pair":
                typeName = "reference";
                break;
            default:
                typeName = type.getTypeName();
                break;
        }

        return typeName;
    }
}
