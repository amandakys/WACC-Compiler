import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;

import java.util.List;

/**
 * Created by donamphuong on 09/11/2016.
 */
public class SyntaxVisitor extends BasicParserBaseVisitor<Void> {
    private String sign = "";

    @Override
    public Void visitFunction(BasicParser.FunctionContext ctx) {
        BasicParser.StatementContext statement = ctx.statement();

        if(check(getLast(statement))) {
            error();
        }
        return null;
    }

    @Override
    public Void visitIntliter(BasicParser.IntliterContext ctx) {
        try {
            Integer.parseInt(sign + ctx.getText());
        } catch(NumberFormatException e) {
            error();
        }
        return null;
    }

    private void error() {
        System.err.println("#syntax_error#");
        System.exit(100);
    }

    @Override
    public Void visitUnop(BasicParser.UnopContext ctx) {
        if(ctx.MINUS() != null) {
            sign = ctx.getText();
        }
        return null;
    }

    private BasicParser.StatementContext getLast(BasicParser.StatementContext statement) {
        List<BasicParser.StatementContext> list = statement.statement();
        if(list.size() > 0) {
            return getLast(list.get(list.size() - 1));
        }

        return statement;
    }

    private boolean check(BasicParser.StatementContext last) {
        if(last.IF() != null) {
            List<BasicParser.StatementContext> stats = last.statement();
            for (int i = 0; i < last.getChildCount(); i++) {
                if(stats.get(i).RETURN() == null && stats.get(i).EXIT()  == null) {
                    return true;
                }
            }
            return false;
        }

        return last.RETURN() == null && last.EXIT() == null;
    }
}
