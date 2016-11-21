package front_end.AST;

import back_end.Utility;
import back_end.data_type.*;
import back_end.instruction.Directive;
import back_end.instruction.LabelInstr;
import back_end.instruction.Pop;
import back_end.instruction.Push;
import back_end.instruction.load_store.Load;
import front_end.AST.FunctionDecl.FunctionDeclAST;
import front_end.AST.StatementAST.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.Stack;

public class ProgramAST extends Node {
    private List<FunctionDeclAST> functions;
    private StatementAST statement;


    public ProgramAST(ParserRuleContext ctx, List<FunctionDeclAST> functions, StatementAST statement) {
        super(ctx);
        this.functions = functions;
        this.statement = statement;
    }

    @Override
    public void check() {

    }

    @Override
    public void translate(Stack<Register> unusedRegs, Stack<Register> paramRegs) {

        for(FunctionDeclAST func : functions) {
            func.translate(unusedRegs, paramRegs);
        }

        Utility.addMain(new LabelInstr("main"));
        Utility.addMain(new Push(Register.LR));

        statement.translate(unusedRegs, paramRegs);

        Utility.addMain(new Load(unusedRegs.pop(), new ImmValue(0)));
        Utility.addMain(new Pop(Register.PC));

        Utility.addMain(new Directive("ltorg"));
    }
}
