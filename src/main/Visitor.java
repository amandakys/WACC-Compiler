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
    public static SymbolTable ST = new SymbolTable(null);

    public Visitor() {
        ST.add("bool", new SCALAR("bool"));
        ST.add("int", new SCALAR("int"));
        ST.add("char", new SCALAR("char"));
        ST.add("string", new SCALAR("string"));

        SymbolTable next = new SymbolTable(ST);
        ST = next;
    }

    @Override
    public AssignmentAST visitAssignment(BasicParser.AssignmentContext ctx) {
        AssignmentAST assignment = new AssignmentAST(visitAssignlhs(ctx.assignlhs()), visitAssignrhs(ctx.assignrhs()));
        assignment.check();
        return assignment;
    }

    @Override
    public AssignlhsAST visitAssignlhs(BasicParser.AssignlhsContext ctx) {
        AssignlhsAST lhs;
        if (ctx.IDENT() != null) {
            lhs = new AssignlhsAST(ctx.IDENT().getText());
        } else if (ctx.arrayelem() != null){
            lhs = new AssignlhsAST(visitArrayelem(ctx.arrayelem()));
        } else {
            lhs = new AssignlhsAST(visitPairelem(ctx.pairelem()));
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
    public ExpressionAST visitExpr(BasicParser.ExprContext ctx) {
        return visitExpression(ctx.expression());
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
    public CallAST visitFunctioncall(BasicParser.FunctioncallContext ctx) {
        CallAST call = new CallAST(ctx.IDENT().getText(), visitArglist(ctx.arglist()));
        return call;
    }

    @Override
    public ArglistAST visitArglist(BasicParser.ArglistContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<Node> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }

        ArglistAST arglist = new ArglistAST(expressionNodes);
        return arglist;

    }
    @Override
    public ArrayelemAST visitArrayelem(BasicParser.ArrayelemContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<Node> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }
        ArrayelemAST arrayelem = new ArrayelemAST(ctx.IDENT().getText(), expressionNodes);
        return arrayelem;
    }

    @Override
    public ExpressionAST visitExpression(BasicParser.ExpressionContext ctx) {
        ExpressionAST expression = null;
        if (ctx.IDENT() != null) {
            expression = new ExpressionAST(ctx.IDENT().getText());
        } else if (ctx.intliter() != null) {
            expression = new ExpressionAST(visitIntliter(ctx.intliter()));
        } else if (ctx.boolliter() != null) {
            expression = new ExpressionAST(visitBoolliter(ctx.boolliter()));
        } else if (ctx.charliter() != null) {
            expression = new ExpressionAST(visitCharliter(ctx.charliter()));
        } else if (ctx.strliter() != null) {
            expression = new ExpressionAST(visitStrliter(ctx.strliter()));
        } else if (ctx.arrayelem() != null) {
            expression = new ExpressionAST(visitArrayelem(ctx.arrayelem()));
        } else if (ctx.unop() != null) {
            expression = new ExpressionAST(visitUnop(ctx.unop()));
        } else if (ctx.binop() != null) {
            expression = new ExpressionAST(visitBinop(ctx.binop()));
        } else if (!ctx.expression().isEmpty()){
            List<BasicParser.ExpressionContext> expressions = ctx.expression();
            List<Node> expressionNodes = new ArrayList<>();

            for (BasicParser.ExpressionContext e : expressions) {
                expressionNodes.add(visitExpression(e));
            }
            expression = new ExpressionAST(expressionNodes);
        }

        return expression;
    }

    @Override
    public IntLiterAST visitIntliter(BasicParser.IntliterContext ctx) {
        return new IntLiterAST(visitChildren(ctx), ctx.DIGIT());
    }

    @Override
    public IntSignAST visitIntsign(BasicParser.IntsignContext ctx) {
        return new IntSignAST();
    }

    @Override
    public Node visitBoolliter(BasicParser.BoolliterContext ctx) {
        return null;
    }

    @Override
    public Node visitBinop(BasicParser.BinopContext ctx) {
        return null;
    }

    public ParamlistAST visitParamlist(BasicParser.ParamlistContext ctx) {
        List<BasicParser.ParamContext> parameters = ctx.param();
        List<Node> parameterNodes = new ArrayList<>();
        for (BasicParser.ParamContext p :parameters) {
            parameterNodes.add(visitParam(p));
        }

        ParamlistAST paramlist = new ParamlistAST(parameterNodes);
        return paramlist;
    }

    @Override
    public ParamAST visitParam(BasicParser.ParamContext ctx) {
        return new ParamAST(ctx.type().getText(), ctx.IDENT().getText());
    }

    @Override
    public VarDeclAST visitVar_decl(BasicParser.Var_declContext ctx) {
        return new VarDeclAST(visitType(ctx.type()), ctx.IDENT().getText(), visitAssignrhs(ctx.assignrhs()));
    }

    @Override
    public Node visitType(BasicParser.TypeContext ctx) {
        if (ctx instanceof BasicParser.BasetypeContext) {
            return visitBasetype((BasicParser.BasetypeContext) ctx);
        } else if (ctx instanceof BasicParser.ArraytypeContext) {
            return visitArraytype((BasicParser.ArraytypeContext) ctx);
        } else if (ctx instanceof BasicParser.PairelemtypeContext) {
            return visitPairelemtype((BasicParser.PairelemtypeContext) ctx);
        }

        return null;
    }

    @Override
    public Node visitBasetype(BasicParser.BasetypeContext ctx) {
        return new BasetypeAST(ctx.getText());
    }

    @Override
    public Node visitArraytype(BasicParser.ArraytypeContext ctx) {

        int arrayDepth = ctx.LBRACKET().size();
        if (!ctx.basetype().isEmpty()) {
            return new ArraytypeAST(visitBasetype(ctx.basetype()), arrayDepth);
        } else if (!ctx.pairtype().isEmpty()) {
            return new ArraytypeAST(visitPairtype(ctx.pairtype()), arrayDepth);
        }
        return null;


    }

    @Override
    public Node visitPairtype(BasicParser.PairtypeContext ctx) {
        return new PairtypeAST(visitPairelemtype(ctx.pairelemtype(0)), ctx.pairelemtype(1));
    }

    @Override
    public Node visitPairelemtype(BasicParser.PairelemtypeContext ctx) {
        if (ctx.PAIR() != null) {
            return new PairelemtypeAST(ctx.PAIR().getText());
        } else if (!ctx.basetype().isEmpty()) {
            return new PairelemtypeAST(visitBasetype(ctx.basetype()));
        } else if (!ctx.arraytype().isEmpty()) {
            return new PairelemtypeAST(visitArraytype(ctx.arraytype()));
        }
    }
}
