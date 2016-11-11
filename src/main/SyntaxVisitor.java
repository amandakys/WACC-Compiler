package main;

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
        if(statement instanceof BasicParser.SequenceContext) {
            List<BasicParser.StatementContext> list = ((BasicParser
                    .SequenceContext) statement).statement();

            if(list.size() > 0) {
                return getLast(list.get(list.size() - 1));
            }
        }

        return statement;
    }


    /*
        Check function takes the last statement and returns true if the last
        statement is either EXIT or RETURN
     */
    private boolean check(BasicParser.StatementContext last) {
        if(last instanceof BasicParser.IfContext) {

            List<BasicParser.StatementContext> stats = ((BasicParser
                    .IfContext) last).statement();

            for (int i = 0; i < stats.size(); i++) {
                if(!(stats.get(i) instanceof BasicParser.ReturnContext ||
                        stats.get(i) instanceof BasicParser.ExitContext)) {
                    return true;
                }
            }
            return false;
        }

        return !((last instanceof BasicParser.ReturnContext) && (last
                instanceof BasicParser.ExitContext));
    }
}
