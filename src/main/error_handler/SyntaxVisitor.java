package main.error_handler;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;

import java.util.List;


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
        if (statement instanceof BasicParser.SequenceContext
                || statement instanceof BasicParser.IfContext) {

            List<BasicParser.StatementContext> list;

            if (statement instanceof BasicParser.SequenceContext) {
                list = ((BasicParser.SequenceContext) statement).statement();
            } else {
                list = ((BasicParser.IfContext) statement).statement();
            }

            if (list.size() > 0) {
                return getLast(list.get(list.size() - 1));
            }
        }

        return statement;
    }


    /*
        Check function takes the last statement and returns true if the last
        statement is neither RETURN nor EXIT
     */
    private boolean check(BasicParser.StatementContext last) {
        return !((last instanceof BasicParser.ReturnContext) || (last
                instanceof BasicParser.ExitContext));
    }
}
