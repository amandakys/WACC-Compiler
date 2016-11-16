package AST;

import AST.FunctionDecl.FunctionDeclAST;
import AST.StatementAST.StatementAST;
import org.antlr.v4.runtime.ParserRuleContext;

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
}
