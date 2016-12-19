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
import front_end.symbol_table.STRING;
import front_end.symbol_table.TYPE;
import main.CodeGen;
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
        expression.IRepresentation();
        IGNode = expression.getIGNode();

        if(expression instanceof StringLiterAST) {
            print_stringIR();
        } else if(!(expression instanceof PairliterAST)){
            //when expression is not null
            newIGNode("p_print_" + findTypeName());
        }
    }

    /*
        This function find the appropriate placeholder depending on the type of expression, and push it
        if it does not exist in the CodeGen.data list. It also branch out to the appropriate function that
        prints expression ("putchar", "p_print_string"...)
     */
    private void pushPlaceholder() {
        String placeholder = "";
        String typeName;
        TYPE type = expression.getType();
        if (type.getTypeName().equals("array")) {
            if(((ARRAY) type).getElementType().getTypeName().equals("char")) {
                typeName = "string";
            } else {
                typeName = "reference";
            }
        } else if(type.getTypeName().equals("pair")) {
            typeName = "reference";
        } else {
            typeName = type.getTypeName();
        }

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

            PrintUtility.addToEndFunctions(functionName, getRegister());
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
