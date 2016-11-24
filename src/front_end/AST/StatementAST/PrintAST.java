package front_end.AST.StatementAST;

import back_end.data_type.*;
import back_end.data_type.register.Register;
import back_end.instruction.*;
import back_end.instruction.condition.CMP;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.MOV;
import back_end.instruction.load_store.LOAD;
import front_end.AST.ExpressionAST.*;
import front_end.symbol_table.ARRAY;
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
        //Result of expression.translate() is stored CodeGen.notUsedRegisters.pop().Final result is stored
        // in R0 so peek at the register to move the value from CodeGen.notUsedRegisters.pop() to R0
        Register result = CodeGen.notUsedRegisters.peek();
        expression.translate();
        addMain(new MOV(Register.R0, result));

        //TODO:find and push the placeholder when there are no more PrintAST node with an expression of the same type
        pushPlaceholder();


//        int exprSize = expression.getType().getSize();
//        String type = expression.getType().getTypeName();

//        if (!hasFunction("p_print_" + type)) {
//            //a char can be printed using "putchar" function
//            if (!(expression instanceof CharLitAST)) {
//
//                addFunction(new LabelInstr("p_print_" + type));
//                addFunction(new PUSH(Register.LR));
//
//                if (expression instanceof BoolliterAST) {
//                    addFunction(new CMP(Register.R0, new ImmValue(0)));
//
//                    //last message should be false
//                    String last = getLastPlaceholder();
//                    //the message before last message should be true. If last message is msg_2 then
//                    //beforeLast must be msg_1
//                    int previous = CodeGen.numPlaceholders + CodeGen.numStrings - 1;
//                    String beforeLast = "msg_" + previous;
//                            //last.substring(0, last.length() - 1) +
//                            //(Integer.parseInt(String.valueOf(last.charAt(last.length() - 1))) - 1);
//
//                    addFunction(new LOAD("NE", Register.R0, new LabelExpr(beforeLast)));
//                    addFunction(new LOAD("EQ", Register.R0, new LabelExpr(last)));
//                } else {
//                    //functions that print string, bool, int have to be specified separately
//                    if (expression instanceof StringLiterAST) {
//                        addFunction(new LOAD(popParamReg(), new Address(Register.R0)));
//                        addFunction(new ADD(popParamReg(), Register.R0, new
//                                ImmValue(exprSize)));
//                    } else if (expression instanceof IntLiterAST) {
//                        addFunction(new MOV(popParamReg(), Register.R0));
//                    }
//                    addFunction(new LOAD(Register.R0, new LabelExpr(getLastPlaceholder())));
//                }
//
//                //TODO: Why adding r0 to 4?
//                addFunction(new ADD(Register.R0, Register.R0, new ImmValue(4)));
//
//                addFunction(new Branch("L", "printf"));
//                addFunction(new MOV(Register.R0, new ImmValue(0)));
//                addFunction(new Branch("L", "fflush"));
//
//                addFunction(new POP(Register.PC));
//            }
//        }

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
        if (type.getTypeName() == "array") {
            typeName = "reference";
        } else {
            typeName = type.getTypeName();
        }
        String functionName = "p_print_" + typeName;

//        if (expression instanceof BoolliterAST) {
//            CodeGen.placeholders.add("\"true\\0\"");
//            CodeGen.placeholders.add("\"false\\0\"");
////            placeholder = "\"" + ((BoolliterAST) expression).getBoolVal() + "\\0\"";
//        } else if (expression instanceof StringLiterAST) {
//            CodeGen.placeholders.add("\"%.*s\\0\"");
//
//        } else if (expression instanceof IntLiterAST) {
//            CodeGen.placeholders.add("\"%d\\0\"");
//
//        } else if (expression instanceof CharLitAST) {
//            functionName = "putchar";
//        } else if (expression instanceof IdentAST) {
//            String type = expression.getType().getTypeName();
            switch(typeName) {
                case "string": CodeGen.placeholders.add("\"%.*s\\0\""); break;
                case "int": CodeGen.placeholders.add("\"%d\\0\""); break;
                case "char": functionName = "putchar"; break;
                case "bool":
                    CodeGen.placeholders.add("\"true\\0\"");
                    CodeGen.placeholders.add("\"false\\0\"");
                    break;
            }

        addMain(new Branch("L", functionName));

        if (!hasPlaceholder(placeholder)) {
            //when expression is a boolLiter, push both true and false to CodeGen.data
            if (expression instanceof BoolliterAST) {
                CodeGen.placeholders.add("\"true\\0\"");
                CodeGen.placeholders.add("\"false\\0\"");
            } else {
                if (placeholder != "") {
                    CodeGen.placeholders.add(placeholder);
                }
            }
        }

        if (!CodeGen.endFunctions.contains(functionName)) {
            CodeGen.endFunctions.add(functionName);
        }
    }
}
