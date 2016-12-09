package front_end.AST.StatementAST;

import back_end.Utility;
import back_end.data_type.ImmValue;
import back_end.data_type.Operand;
import back_end.data_type.register.Register;
import back_end.instruction.data_manipulation.ADD;
import back_end.instruction.data_manipulation.SUB;
import front_end.AST.ExpressionAST.ExpressionAST;
import front_end.AST.ProgramAST;
import front_end.symbol_table.SymbolTable;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andikoh on 10/11/2016.
 */
public class BeginAST extends StatementAST {
    private StatementAST statement;
    private SymbolTable ST;

    public BeginAST(ParserRuleContext ctx, StatementAST statement) {
        super(ctx);
        this.statement = statement;
        this.ST = Visitor.ST;
    }

    @Override
    public void check() {
        statement.checkNode();
    }

    @Override
    public void translate() {
        Visitor.ST = ST;
        ProgramAST.newScope(statement);
        Visitor.ST = ST.getEncSymbolTable();
    }

}
