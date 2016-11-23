package front_end.AST.FunctionDecl;

import back_end.data_type.register.Register;

import back_end.instruction.Directive;
import back_end.instruction.POP;
import back_end.instruction.PUSH;

import back_end.instruction.*;

import front_end.AST.Node;
import front_end.AST.StatementAST.StatementAST;
import front_end.AST.TypeAST.TypeAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.*;

import java.util.ArrayList;
import java.util.List;

import static back_end.Utility.addMain;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FunctionDeclAST extends Node {
    private TypeAST returntype;
    private String returntypename;
    private String funcname;
    private ParamlistAST parameters;
    private StatementAST statement;


    public FunctionDeclAST(ParserRuleContext ctx, TypeAST returntype, String funcname, StatementAST statement) {
        super(ctx);
        this.returntype = returntype;
        this.returntypename = returntype.getType().getTypeName();
        this.funcname = funcname;
        this.parameters = null;
        this.statement = statement;
    }
    public FunctionDeclAST(ParserRuleContext ctx, TypeAST returntype, String funcname, ParamlistAST paramList, StatementAST statement) {
        super(ctx);
        //return type name will remove all non alphanumeric characters to
        // search for primitive types
        this.returntype = returntype;
        this.returntypename = returntype.getType().getTypeName();
        this.funcname = funcname;
        this.parameters = paramList;
        this.statement = statement;
    }

    public void CheckFunctionNameAndReturnType() {
        IDENTIFIER returnType = returntype.getType();

        FUNCTION f;
        if (parameters == null) {
            //no parameters
            f = new FUNCTION(null, (TYPE) returnType, new ArrayList<TYPE>());
        } else {
            f = new FUNCTION(null, (TYPE) returnType, parameters.getParamTypes());
        }
        identObj = f;

        if (returntype.getType() instanceof PAIR) {
            //return type is a pair
             returnType = new PAIR(((PAIR) returntype.getType()).getFirst(), ((PAIR) returntype.getType()).getSecond());
            //Visitor.ST.add();
        } else if (returntype.getType() instanceof ARRAY) {
            returnType = new ARRAY(((ARRAY) returnType.getType()).getElementType(), 0);
        } else {
            returnType = Visitor.ST.lookUpAll(returntypename);
            IDENTIFIER F = Visitor.ST.lookUp(funcname);

            if (returntype == null) {
                error("Unknown type " + returntypename);
            } else if (!(returntype.getType() instanceof TYPE)) {
                error(returntypename + " is not a type");
            } else if (!(returntype.getType()).isReturnable()) {
                error("cannot return " + returntypename + " objects");
            } else if (F != null) {
                error(funcname + " is already declared");
            } else {
                Visitor.ST.add(funcname, identObj);
            }
        }
    }

    @Override
    public void check() {
        CheckFunctionNameAndReturnType();

        if (parameters != null) {
            List<ParamAST> paramASTs = parameters.getParams();

            for (ParamAST p : paramASTs) {
                Visitor.ST.add(p.getIdent(), p.getType());
            }
        }

        ((FUNCTION) identObj).setSymbolTable(Visitor.ST);

    }

    @Override
    public void translate() {
        //Utility.pushData("\0");
        CodeGen.main.add(new LabelInstr("f_"+funcname));
        CodeGen.main.add(new PUSH(Register.LR));

        statement.translate();
        CodeGen.main.add(new POP(Register.PC));
        CodeGen.main.add (new Directive("ltorg"));
    }
}
