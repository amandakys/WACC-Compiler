package main;

import AST.*;
import AST.AssignmentAST.*;
import AST.ExpressionAST.*;
import AST.FunctionDecl.ArglistAST;
import AST.FunctionDecl.FunctionDeclAST;
import AST.FunctionDecl.ParamAST;
import AST.FunctionDecl.ParamlistAST;
import AST.StatementAST.*;
import AST.TypeAST.*;
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
            return visitBegin((BasicParser.BeginContext) ctx);
        } else if (ctx instanceof  BasicParser.SequenceContext) {
            return visitSequence((BasicParser.SequenceContext) ctx);
        }
        return null;
    }

    @Override
    public SkipAST visitSkip(BasicParser.SkipContext ctx) {
        //skip statement contains no information and has no checks;
        return new SkipAST();
    }

    @Override
    public AssignmentAST visitAssignment(BasicParser.AssignmentContext ctx) {
        AssignmentAST assignment = new AssignmentAST(visitAssignlhs(ctx.assignlhs()), visitAssignrhs(ctx.assignrhs()));
        assignment.check();
        return assignment;
    }

    @Override
    public VarDeclAST visitVar_decl(BasicParser.Var_declContext ctx) {
        VarDeclAST var = new VarDeclAST(visitType(ctx.type()), ctx.IDENT()
                .getText(), visitAssignrhs(ctx.assignrhs()));
        var.check();
        return var;
    }

    @Override
    public ReadAST visitRead(BasicParser.ReadContext ctx) {

        ReadAST readAST = new ReadAST(visitAssignlhs(ctx.assignlhs()));
        readAST.check();
        return  readAST;
    }

    @Override
    public FreeAST visitFree(BasicParser.FreeContext ctx) {
        return new FreeAST(visitExpression(ctx.expression()));
    }

    @Override
    public ReturnAST visitReturn(BasicParser.ReturnContext ctx) {
        if(!(ctx.getParent() instanceof BasicParser.FunctionContext)) {
            System.err.println("Global return statement");
        }
        ReturnAST returnAST = new ReturnAST(visitExpression(ctx.expression()));
        returnAST.check();
        return returnAST;
    }

    @Override
    public ExitAST visitExit(BasicParser.ExitContext ctx) {
        ExpressionAST expression = visitExpression(ctx.expression());
        ExitAST exit = new ExitAST(expression);
        exit.check();
        return exit;
    }

    @Override
    public PrintAST visitPrint(BasicParser.PrintContext ctx) {
        return new PrintAST(visitExpression(ctx.expression()));
    }

    @Override
    public Node visitFunction(BasicParser.FunctionContext ctx) {
        FunctionDeclAST function = new FunctionDeclAST(ctx.type().getText(), ctx
                .IDENT().getText(),
                visitParamlist(ctx.paramlist()));
        function.check();
        return visitChildren(ctx);
    }

    @Override
    public PrintlnAST visitPrintln(BasicParser.PrintlnContext ctx) {
        PrintlnAST print = new PrintlnAST(visitExpression(ctx.expression()));
        print.check();
        return print;
    }

    @Override
    public IfAST visitIf(BasicParser.IfContext ctx) {
        //components
        ExpressionAST expr = visitExpression(ctx.expression());
        StatementAST then = visitStatement(ctx.statement(0));
        StatementAST elseSt = visitStatement(ctx.statement(1));

        IfAST ifAST = new IfAST(expr, then, elseSt);
        ifAST.check();
        return ifAST;
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
        BeginAST begin = new BeginAST(visitStatement(ctx.statement()));
        begin.check();
        return begin;
    }

    @Override
    public SequenceAST visitSequence(BasicParser.SequenceContext ctx) {
        List<BasicParser.StatementContext> statements = ctx.statement();
        List<StatementAST> statementASTs = new ArrayList<>();

        for (BasicParser.StatementContext s : statements) {
            statementASTs.add(visitStatement(s));
        }

        SequenceAST sequence = new SequenceAST(statementASTs);
        //sequence.check();
        visitChildren(ctx);
        return sequence;
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

    public AssignrhsAST visitAssignrhs(BasicParser.AssignrhsContext ctx) {
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
        return visitArrayliter(ctx.arrayliter());
    }

    @Override
    public ArraylitAST visitArrayliter(BasicParser.ArrayliterContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<ExpressionAST> expressionASTs = new ArrayList<>();

        for (BasicParser.ExpressionContext e : expressions) {
            expressionASTs.add(visitExpression(e));
        }

        return new ArraylitAST(expressionASTs);
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
        if(ctx != null) {
            List<BasicParser.ExpressionContext> expressions = ctx.expression();
            List<ExpressionAST> expressionNodes = new ArrayList<>();
            for (BasicParser.ExpressionContext e : expressions) {
                expressionNodes.add(visitExpression(e));
            }

            ArglistAST arglist = new ArglistAST(expressionNodes);
            return arglist;
        }

        return new ArglistAST(new ArrayList<ExpressionAST>());
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
        List<ExpressionAST> list = new ArrayList<>();
        for(BasicParser.ExpressionContext expr: ctx.expression()) {
            list.add(visitExpression(expr));
        }

        if (ctx.IDENT()!= null) {
            expression = new IdentAST(ctx.IDENT().getText());
        } else if (ctx.intliter() != null) {
            expression = visitIntliter(ctx.intliter());
        } else if (ctx.boolliter() != null) {
            expression = visitBoolliter(ctx.boolliter());
        } else if (ctx.charliter() != null) {
            expression = visitCharliter(ctx.charliter());
        } else if (ctx.strliter() != null) {
            expression = visitStrliter(ctx.strliter());
        } else if (ctx.arrayelem() != null) {
            expression = visitArrayelem(ctx.arrayelem());
        } else if (ctx.unop() != null) {
            List<ExpressionAST> expressions = new ArrayList<>();
            for (BasicParser.ExpressionContext e: ctx.expression()) {
                expressions.add(visitExpression(e));
            }

            //expression = new UnopAST(expressions, ctx.unop().getText());

        } else {
            List<ExpressionAST> expressions = new ArrayList<>();

            for (BasicParser.ExpressionContext e: ctx.expression()) {
                expressions.add(visitExpression(e));
            }

            if (ctx.binop() != null) {
                expression = new BinOpAST(ctx.binop().getText(), expressions);
            } else if (!ctx.expression().isEmpty()){
                //TODO: need to fix instance where expression is called from expression
                //should we make epxressionAST abstract?
                //possible fix is to recursively call visitExpression here
                //expression = new ExpressionAST(expressionNodes);
            }
        }
        expression.check();
        return expression;
    }

    @Override
    public IntLiterAST visitIntliter(BasicParser.IntliterContext ctx) {
        String sign = ctx.intsign() != null ? ctx.intsign().getText() : "";
        return new IntLiterAST(sign, ctx.DIGIT().toString());
    }

    @Override
    public IntSignAST visitIntsign(BasicParser.IntsignContext ctx) {
        return new IntSignAST();
    }

    @Override
    public BoolliterAST visitBoolliter(BasicParser.BoolliterContext ctx) {
        return new BoolliterAST(ctx.getText());
    }

    @Override
    public CharLitAST visitCharliter(BasicParser.CharliterContext ctx) {
        return new CharLitAST(ctx.getText());
    }

    @Override
    public StringLiterAST visitStrliter(BasicParser.StrliterContext ctx) {
        return new StringLiterAST(ctx.getText());
    }

    @Override
    public ParamlistAST visitParamlist(BasicParser.ParamlistContext ctx) {
        if(ctx != null) {
            List<BasicParser.ParamContext> parameters = ctx.param();
            List<ParamAST> parameterNodes = new ArrayList<>();
            for (BasicParser.ParamContext p :parameters) {
                parameterNodes.add(visitParam(p));
            }

            ParamlistAST paramlist = new ParamlistAST(parameterNodes);
            paramlist.check();
            return paramlist;
        }
        return new ParamlistAST(new ArrayList<ParamAST>());
    }

    @Override
    public ParamAST visitParam(BasicParser.ParamContext ctx) {
        ParamAST param = new ParamAST(ctx.type().getText(), ctx.IDENT()
                .getText());
        return param;
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
        BasetypeAST baseType = new BasetypeAST(ctx.getText());
        baseType.check();
        return baseType;
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
