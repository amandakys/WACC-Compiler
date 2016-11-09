package main;

import AST.*;
import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import symbol_table.SCALAR;
import symbol_table.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class Visitor extends BasicParserBaseVisitor<Node>{
    private SymbolTable ST = new SymbolTable(null);

    public Visitor() {
        ST.add("bool", new SCALAR("bool"));
        ST.add("int", new SCALAR("int"));
        ST.add("char", new SCALAR("char"));
        ST.add("string", new SCALAR("string"));
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

    public Node visitStatement(BasicParser.StatementContext ctx) {
        if (ctx instanceof BasicParser.SkipContext) {
            return visitSkip((BasicParser.SkipContext)ctx);
        } else if (ctx instanceof BasicParser.Var_declContext) {
            return visitVar_decl((BasicParser.Var_declContext)ctx);
        } else if (ctx instanceof BasicParser.AssignmentContext) {
            return visitAssignment((BasicParser.AssignmentContext)ctx);
        } else if (ctx instanceof BasicParser.ReadContext) {
            return visitRead((BasicParser.ReadContext)ctx);
        } else if (ctx instanceof BasicParser.FreeContext) {
            return visitFree((BasicParser.FreeContext) ctx);
        } else if (ctx instanceof BasicParser.ReturnContext) {
            return visitReturn((BasicParser.ReturnContext)ctx);
        } else if (ctx instanceof BasicParser.ExitContext) {
            return visitExit((BasicParser.ExitContext)ctx);
        } else if (ctx instanceof BasicParser.PrintContext) {
            return visitPrint((BasicParser.PrintContext)ctx);
        } else if (ctx instanceof BasicParser.PrintlnContext) {
            return visitPrintln((BasicParser.PrintlnContext)ctx);
        } else if (ctx instanceof BasicParser.IfContext) {
            return visitIf((BasicParser.IfContext)ctx);
        } else if (ctx instanceof BasicParser.WhileContext) {
            return visitWhile((BasicParser.WhileContext)ctx);
        } else if (ctx instanceof BasicParser.BeginContext) {
            return visitBegin((BasicParser.BeginContext)ctx);
        } else if (ctx instanceof BasicParser.SequenceContext) {
            return visitSequence((BasicParser.SequenceContext)ctx);
        }
        return null;
    }

    @Override
    public Node visitRead(BasicParser.ReadContext ctx) {
        return new ReadAST(visitAssignlhs(ctx.assignlhs()));
    }

    @Override
    public Node visitFree(BasicParser.FreeContext ctx) {
        return new FreeAST(visitExpression(ctx.expression()));
    }

    @Override
    public Node visitReturn(BasicParser.ReturnContext ctx) {
        return new ReturnAST(visitExpression(ctx.expression()));
    }

    @Override
    public Node visitExit(BasicParser.ExitContext ctx) {
        return new ExitAST(visitExpression(ctx.expression()));
    }

    @Override
    public Node visitPrint(BasicParser.PrintContext ctx) {
        return new PrintAST(visitExpression(ctx.expression()));
    }

    @Override
    public Node visitPrintln(BasicParser.PrintlnContext ctx) {
        return new PrintlnAST(visitExpression(ctx.expression()));
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
        return visitExpression(ctx.expression());
    }

    @Override
    public Node visitArraylit(BasicParser.ArraylitContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.arrayliter().expression();
        List<Node> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }

        ArraylitAST newpair = new ArraylitAST(ST, expressionNodes);
        return  newpair;
    }

    @Override
    public Node visitNewpair(BasicParser.NewpairContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<Node> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }

        NewpairAST newpair = new NewpairAST(ST, expressionNodes);
        return  newpair;
    }

    //@Override
    public Node visitPairelement(BasicParser.PairelemContext ctx) {
        return visitExpression(ctx.expression());
    }

    @Override
    public Node visitFunctioncall(BasicParser.FunctioncallContext ctx) {
        CallAST call = new CallAST(ST, ctx.IDENT().getText(), visitArglist(ctx.arglist()));
        return call;
    }

    @Override
    public Node visitPairelem(BasicParser.PairelemContext ctx) {
        return visitExpression(ctx.expression());
    }

    @Override
    public Node visitArglist(BasicParser.ArglistContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<Node> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }

        ArglistAST arglist = new ArglistAST(ST, expressionNodes);
        return arglist;

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

    public Node visitParamlist(BasicParser.ParamlistContext ctx) {
        List<BasicParser.ParamContext> parameters = ctx.param();
        List<Node> parameterNodes = new ArrayList<>();
        for (BasicParser.ParamContext p :parameters) {
            parameterNodes.add(visitParam(p));
        }

        ParamlistAST paramlist = new ParamlistAST(ST, parameterNodes);
        return paramlist;
    }

    @Override
    public Node visitParam(BasicParser.ParamContext ctx) {
        return new ParamAST(ST, ctx.type().getText(), ctx.IDENT().getText());
    }

    public Node visitVar_decl(BasicParser.Var_declContext ctx) {
        return new VarDeclAST(ST, visitType(ctx.type()), ctx.IDENT().getText(), visitAssignrhs(ctx.assignrhs()));
    }
}
