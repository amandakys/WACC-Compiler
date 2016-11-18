package front_end.AST;

import back_end.data_type.Address;
import back_end.data_type.Label;
import back_end.instruction.Instruction;
import back_end.instruction.Pop;
import back_end.instruction.Push;
import back_end.instruction.directives.LTORG;
import back_end.instruction.load_store.Load;
import front_end.AST.FunctionDecl.FunctionDeclAST;
import front_end.AST.StatementAST.StatementAST;
import main.CodeGen;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andikoh on 11/11/2016.
 */
public class ProgramAST extends Node {
    List<FunctionDeclAST> functions;
    StatementAST statement;


    public ProgramAST(ParserRuleContext ctx, List<FunctionDeclAST> functions, StatementAST statement) {
        super(ctx);
        this.functions = functions;
        this.statement = statement;
    }

    @Override
    public void check() {

    }

    @Override
    public void translate() {

        for(FunctionDeclAST func : functions) {
            func.translate();
        }

        CodeGen.globalMain.add(new Label("main"));
        CodeGen.globalMain.add(new Push(lr));

        statement.translate();

        CodeGen.globalMain.add(new Load(r0, new Address(0)));
        CodeGen.globalMain.add(new Pop(pc));
        CodeGen.globalMain.add(new LTORG());
    }
}
