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

    public StatementAST visitStatement(BasicParser.StatementContext ctx) {
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
    public SkipAST visitSkip(BasicParser.SkipContext ctx) {
        //skip statement contains no information and has no checks;
        return new SkipAST();
    }

    @Override
    public VarDeclAST visitVar_decl(BasicParser.Var_declContext ctx) {
        return new VarDeclAST(visitType(ctx.type()), ctx.IDENT().getText(), visitAssignrhs(ctx.assignrhs()));
    }

    @Override
    public AssignmentAST visitAssignment(BasicParser.AssignmentContext ctx) {
        AssignmentAST assignment = new AssignmentAST(visitAssignlhs(ctx.assignlhs()), visitAssignrhs(ctx.assignrhs()));
        assignment.check();
        return assignment;
    }

    @Override
    public ReadAST visitRead(BasicParser.ReadContext ctx) {
        return new ReadAST(visitAssignlhs(ctx.assignlhs()));
    }

    @Override
    public FreeAST visitFree(BasicParser.FreeContext ctx) {
        return new FreeAST(visitExpression(ctx.expression()));
    }

    @Override
    public ReturnAST visitReturn(BasicParser.ReturnContext ctx) {
        return new ReturnAST(visitExpression(ctx.expression()));
    }

    @Override
    public ExitAST visitExit(BasicParser.ExitContext ctx) {
        ExitAST exit = new ExitAST(visitExpression(ctx.expression()));
        exit.check();
        return exit;
    }

    @Override
    public PrintAST visitPrint(BasicParser.PrintContext ctx) {
        return new PrintAST(visitExpression(ctx.expression()));
    }

    @Override
    public PrintlnAST visitPrintln(BasicParser.PrintlnContext ctx) {
        return new PrintlnAST(visitExpression(ctx.expression()));
    }

    @Override
    public IfAST visitIf(BasicParser.IfContext ctx) {
        //components
        ExpressionAST expr = visitExpression(ctx.expression());
        StatementAST then = visitStatement(ctx.statement(0));
        StatementAST elseSt = visitStatement(ctx.statement(1));

        return new IfAST(expr, then, elseSt);
    }

    @Override
    public WhileAST visitWhile(BasicParser.WhileContext ctx) {
        //components
        ExpressionAST expr = visitExpression(ctx.expression());
        StatementAST statement = visitStatement(ctx.statement());

        return new WhileAST(expr, statement);
    }

    @Override
    public BeginAST visitBegin(BasicParser.BeginContext ctx) {
        return new BeginAST(visitStatement(ctx.statement()));
    }

    @Override
    public SequenceAST visitSequence(BasicParser.SequenceContext ctx) {
        List<BasicParser.StatementContext> statements = ctx.statement();
        List<StatementAST> statementASTs = new ArrayList<>();

        for (BasicParser.StatementContext s : statements) {
            statementASTs.add(visitStatement(s));
        }

        return new SequenceAST(statementASTs);
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
    public ArraylitAST visitArraylit(BasicParser.ArraylitContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.arrayliter().expression();
        List<Node> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }

        ArraylitAST newpair = new ArraylitAST(expressionNodes);
        return  newpair;
    }

    @Override
    public NewpairAST visitNewpair(BasicParser.NewpairContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<ExpressionAST> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }

        NewpairAST newpair = new NewpairAST(expressionNodes);
        return  newpair;
    }

    @Override
    public PairelemAST visitPairelement(BasicParser.PairelementContext ctx) {
        return visitPairelem(ctx.pairelem());
    }

    @Override
    public CallAST visitFunctioncall(BasicParser.FunctioncallContext ctx) {
        CallAST call = new CallAST(ctx.IDENT().getText(), visitArglist(ctx.arglist()));
        return call;
    }

    @Override
    public ArglistAST visitArglist(BasicParser.ArglistContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<ExpressionAST> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }

        ArglistAST arglist = new ArglistAST(expressionNodes);
        return arglist;
    }

    public PairelemAST visitPairelem(BasicParser.PairelemContext ctx) {
        return new PairelemAST(visitExpression(ctx.expression()));
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
        if (ctx.getText() != null) {
            expression = new ExpressionAST(ctx.getText());
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
            List<ExpressionAST> expressions =;
            for (BasicParser.ExpressionContext e: ctx.expression()) {
                expressions.add(visitExpression(e));
            }

            expression = new BinopAST(ctx.expression(), ctx.binop().getText(););

            expression = new ExpressionAST(visitBinop(ctx.binop()));
        } else if (!ctx.expression().isEmpty()){
            List<BasicParser.ExpressionContext> expressions = ctx.expression();
            List<ExpressionAST> expressionNodes = new ArrayList<>();

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
    public BoolliterAST visitBoolliter(BasicParser.BoolliterContext ctx) {
        //TODO: implement
        return null;
    }

    @Override
    public Node visitBinop(BasicParser.BinopContext ctx) {

        //TODOL implement
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
    public TypeAST visitType(BasicParser.TypeContext ctx) {
        if (!ctx.basetype().isEmpty()) {
            return visitBasetype(ctx.basetype());
        } else if (!ctx.arraytype().isEmpty()) {
            return visitArraytype(ctx.arraytype());
        } else if (!ctx.pairtype().isEmpty()) {
            return visitPairtype(ctx.pairtype());
        }

        return null;
    }

    @Override
    public BasetypeAST visitBasetype(BasicParser.BasetypeContext ctx) {
        return new BasetypeAST(ctx.getText());
    }

    @Override
    public ArraytypeAST visitArraytype(BasicParser.ArraytypeContext ctx) {

        int arrayDepth = ctx.LBRACKET().size();
        if (!ctx.basetype().isEmpty()) {
            return new ArraytypeAST(visitBasetype(ctx.basetype()), arrayDepth);
        } else if (!ctx.pairtype().isEmpty()) {
            return new ArraytypeAST(visitPairtype(ctx.pairtype()), arrayDepth);
        }
        return null;


    }

   @Override
    public PairtypeAST visitPairtype(BasicParser.PairtypeContext ctx) {
        return new PairtypeAST(visitPairelemtype(ctx.pairelemtype(0)), visit(ctx.pairelemtype(1)));
    }

    @Override
    public PairelemtypeAST visitPairelemtype(BasicParser.PairelemtypeContext ctx) {
        if (ctx.PAIR() != null) {
            return new PairelemtypeAST(ctx.PAIR().getText());
        } else if (!ctx.basetype().isEmpty()) {
            return new PairelemtypeAST(visitBasetype(ctx.basetype()));
        } else if (!ctx.arraytype().isEmpty()) {
            return new PairelemtypeAST(visitArraytype(ctx.arraytype()));
        }
        return null;
    }


}
