package AST.StatementAST;

/**
 * Created by andikoh on 10/11/2016.
 */
public class BeginAST extends StatementAST {
    StatementAST statement;

    public BeginAST(StatementAST statement) {
        this.statement = statement;
    }
    @Override
    public void check() {

    }
}
