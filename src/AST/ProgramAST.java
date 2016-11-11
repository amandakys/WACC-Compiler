package AST;

import AST.FunctionDecl.FunctionDeclAST;
import AST.StatementAST.StatementAST;

import java.util.List;

/**
 * Created by andikoh on 11/11/2016.
 */
public class ProgramAST extends Node {
    List<FunctionDeclAST> functions;
    StatementAST statement;


    public ProgramAST(List<FunctionDeclAST> functions, StatementAST statement) {
        this.functions = functions;
        this.statement = statement;
    }

    @Override
    public void check() {

    }
}
