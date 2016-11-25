package front_end.AST.FunctionDecl;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.register.PreIndex;
import back_end.data_type.register.Register;

import back_end.data_type.register.ShiftedReg;
import back_end.instruction.Directive;
import back_end.instruction.POP;
import back_end.instruction.PUSH;

import back_end.instruction.*;

import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
import front_end.AST.Node;
import front_end.AST.ProgramAST;
import front_end.AST.StatementAST.StatementAST;
import front_end.AST.TypeAST.TypeAST;
import main.CodeGen;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsd15 on 09/11/16.
 */
public class FunctionDeclAST extends Node {
    private TypeAST returntype;
    private String returntypename;
    private String funcname;
    private ParamlistAST parameters;
    private StatementAST statement;
    private FUNCTION function;


    public FunctionDeclAST(ParserRuleContext ctx, TypeAST returntype, String funcname) {
        super(ctx);
        this.returntype = returntype;
        this.returntypename = returntype.getType().getTypeName();
        this.funcname = funcname;
        this.parameters = null;
    }
    public FunctionDeclAST(ParserRuleContext ctx, TypeAST returntype, String funcname, ParamlistAST paramList) {
        super(ctx);
        //return type name will remove all non alphanumeric characters to
        // search for primitive types
        this.returntype = returntype;
        this.returntypename = returntype.getType().getTypeName();
        this.funcname = funcname;
        this.parameters = paramList;
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

    public void setStatement(StatementAST statement) {
        this.statement = statement;
    }

    @Override
    public void translate() {
        //Utility.pushData("\0");
        function = (FUNCTION) identObj;
        Visitor.ST = function.getSymtab();
        int size = Visitor.ST.findSize();
        int sizeOfParams = 0;
        if (parameters != null) {
            for (ParamAST p : parameters.getParams()) {
                int shift = Visitor.ST.findStackShift(p.getIdent());
                ShiftedReg address = new PreIndex(Register.SP,
                        new ImmValue(shift));
                Visitor.ST.addToMemoryAddress(p.getIdent(), address);
                sizeOfParams += p.getSize();
//                ProgramAST.nextAddress += identObj.getSize();
//                ProgramAST.size -= identObj.getSize();
//
//                ShiftedReg address = new PreIndex(Register.SP,
//                        new ImmValue(ProgramAST.size));
//                Visitor.ST.addToMemoryAddress(ident, address);
            }
        }

        CodeGen.main.add(new LabelInstr("f_"+funcname));
        CodeGen.main.add(new PUSH(Register.LR));

        int sizeOfNewVars = size - sizeOfParams;

        if (sizeOfNewVars != 0) {
            Utility.addMain(new SUB(Register.SP, Register.SP, new ImmValue(sizeOfNewVars)));
        }
        Utility.addJumpSP(sizeOfNewVars);

        statement.translate();

        Utility.resetJumpSP();
//        if (sizeOfNewVars != 0) {
//            Utility.addMain(new ADD(Register.SP, Register.SP, new ImmValue(sizeOfNewVars)));
//        }

        //CodeGen.main.add(new POP(Register.PC));
        CodeGen.main.add (new Directive("ltorg"));
        Utility.pushBackRegisters();
        Visitor.ST = Visitor.ST.getEncSymbolTable();
    }
}
