package main;

import AST.*;
import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import symbol_table.SCALAR;
import symbol_table.SymbolTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class Visitor extends BasicParserBaseVisitor<Node>{
    private SymbolTable ST = new SymbolTable(null);

    public Visitor() {
        ST.add("bool", new SCALAR());
        ST.add("int", new SCALAR());
        ST.add("char", new SCALAR());
        ST.add("string", new SCALAR());
    }

    @Override
    public Node visitAssignment(BasicParser.AssignmentContext ctx) {
        AssignmentAST assignment = new AssignmentAST(ST, visitAssignlhs(ctx.assignlhs()), visitAssignrhs(ctx.assignrhs()));
        assignment.check();
        return assignment;
    }

    @Override
    public Node visitAssignlhs(BasicParser.AssignlhsContext ctx) {
        AssignlhsAST lhs;
        if (ctx.IDENT() != null) {
            lhs = new AssignlhsAST(ST, ctx.IDENT().getText());
        } else if (ctx.arrayelem() != null){
            lhs = new AssignlhsAST(ST, visitArrayelem(ctx.arrayelem()));
        } else {
            lhs = new AssignlhsAST(ST, visitPairelem(ctx.pairelem()));
        }
        return lhs;
    }

    public Node visitAssignrhs(BasicParser.AssignrhsContext ctx) {
        //can it be replaced with visitchildren?

//        switch(ctx) {
//            BasicParser.ExprContext: return visitExpr((BasicParser.ExprContext) ctx);
//        }
        if (ctx instanceof BasicParser.ExprContext) {
            return visitExpr((BasicParser.ExprContext) ctx);
        } else if (ctx instanceof BasicParser.ArraylitContext) {
            return visitArraylit((BasicParser.ArraylitContext) ctx);
        } else if (ctx instanceof BasicParser.NewpairContext) {
            return visitNewpair((BasicParser.NewpairContext) ctx);
        } else if (ctx instanceof BasicParser.PairelementContext) {
            return visitPairelement((BasicParser.PairelementContext) ctx);
        } else if(ctx instanceof BasicParser.FunctioncallContext) {
            return visitFunctioncall((BasicParser.FunctioncallContext) ctx);
        }
        return null;
    }

    @Override
    public Node visitExpr(BasicParser.ExprContext ctx) {
        ExpressionAST expression = null;
        if (ctx.)
    }

    @Override
    public Node visitArraylit(BasicParser.ArraylitContext ctx) {
        return null;
    }

    @Override
    public Node visitNewpair(BasicParser.NewpairContext ctx) {
        return null;
    }

    //@Override
    public Node visitPairelement(BasicParser.PairelemContext ctx) {
        return null;
    }

    @Override
    public Node visitFunctioncall(BasicParser.FunctioncallContext ctx) {
        return null;
    }

    @Override
    public Node visitArrayelem(BasicParser.ArrayelemContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<Node> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }
        ArrayelemAST arrayelem = new ArrayelemAST(ST, ctx.IDENT().getText(), expressionNodes);
        return arrayelem;
    }

    @Override
    public Node visitExpression(BasicParser.ExpressionContext ctx) {
        ExpressionAST expression = null;
        if (ctx.IDENT() != null) {
            expression = new ExpressionAST(ST, ctx.IDENT().getText());
        } else if (ctx.intliter() != null) {
            expression = new ExpressionAST(ST, visitIntliter(ctx.intliter()));
        } else if (ctx.boolliter() != null) {
            expression = new ExpressionAST(ST, visitBoolliter(ctx.boolliter()));
        } else if (ctx.charliter() != null) {
            expression = new ExpressionAST(ST, visitCharliter(ctx.charliter()));
        } else if (ctx.strliter() != null) {
            expression = new ExpressionAST(ST, visitStrliter(ctx.strliter()));
        } else if (ctx.arrayelem() != null) {
            expression = new ExpressionAST(ST, visitArrayelem(ctx.arrayelem()));
        } else if (ctx.unop() != null) {
            expression = new ExpressionAST(ST, visitUnop(ctx.unop()));
        } else if (ctx.binop() != null) {
            expression = new ExpressionAST(ST, visitBinop(ctx.binop()));
        } else if (!ctx.expression().isEmpty()){
            List<BasicParser.ExpressionContext> expressions = ctx.expression();
            List<Node> expressionNodes = new ArrayList<>();

            for (BasicParser.ExpressionContext e : expressions) {
                expressionNodes.add(visitExpression(e));
            }
            expression = new ExpressionAST(ST, expressionNodes);
        }

        return expression;
    }

    @Override
    public Node visitIntliter(BasicParser.IntliterContext ctx) {
        return new IntLiterAST(ST, visitChildren(ctx), ctx.DIGIT());
    }

    @Override
    public Node visitIntsign(BasicParser.IntsignContext ctx) {
        return new IntSignAST(ST);
    }

    @Override
    public Node visitBoolliter(BasicParser.BoolliterContext ctx) {
       new BoolLiterAST(ST)
    }

    @Override Node visitBinop(BasicParser.BinopContext ctx) {

    }
}
