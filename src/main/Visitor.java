package main;

import AST.*;
import AST.AssignmentAST.*;
import AST.ExpressionAST.*;
import AST.FunctionDecl.ArglistAST;
import AST.FunctionDecl.FunctionDeclAST;
import AST.FunctionDecl.ParamAST;
import AST.FunctionDecl.ParamlistAST;
import AST.Node;
import AST.StatementAST.*;
import AST.TypeAST.*;
import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import symbol_table.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andikoh on 08/11/2016.
 */
public class Visitor extends BasicParserBaseVisitor<Node>{
    public static SymbolTable ST = new SymbolTable(null);
    private static List<CallAST> toBeVisited = new ArrayList<>();

    public Visitor() {
        ST.add("bool", new SCALAR("bool"));
        ST.add("int", new SCALAR("int"));
        ST.add("char", new SCALAR("char"));
        ST.add("string", new STRING());

        SymbolTable next = new SymbolTable(ST);
        ST = next;
    }

    @Override
    public ProgramAST visitProgram(BasicParser.ProgramContext ctx) {
        List<BasicParser.FunctionContext> functions = ctx.function();
        List<FunctionDeclAST> functionASTs = new ArrayList<>();

        for (BasicParser.FunctionContext f: functions) {
            functionASTs.add(visitFunction(f));
        }

        return new ProgramAST(ctx, functionASTs, visitStatement(ctx.statement()));
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
        return new SkipAST(ctx);
    }

    @Override
    public AssignmentAST visitAssignment(BasicParser.AssignmentContext ctx) {
        AssignmentAST assignment = new AssignmentAST(ctx, visitAssignlhs(ctx.assignlhs()), visitAssignrhs(ctx.assignrhs()));
        assignment.check();
        return assignment;
    }

    @Override
    public VarDeclAST visitVar_decl(BasicParser.Var_declContext ctx) {
        VarDeclAST var = new VarDeclAST(ctx, visitType(ctx.type()), ctx.IDENT()
                .getText(), visitAssignrhs(ctx.assignrhs()));
        var.check();
        return var;
    }

    @Override
    public ReadAST visitRead(BasicParser.ReadContext ctx) {

        ReadAST readAST = new ReadAST(ctx, visitAssignlhs(ctx.assignlhs()));
        readAST.check();
        return  readAST;
    }

    @Override
    public FreeAST visitFree(BasicParser.FreeContext ctx) {
        FreeAST freeAST = new FreeAST(ctx, visitExpression(ctx.expression()));
        freeAST.check();
        return freeAST;
    }

    @Override
    public ReturnAST visitReturn(BasicParser.ReturnContext ctx) {

        TypeAST returnType = visitType((checkReturnInFunction(ctx)).type());
        ExpressionAST expression = visitExpression(ctx.expression());
        returnType.checkType(expression);

        ReturnAST returnAST = new ReturnAST(ctx, visitExpression(ctx.expression()));
        returnAST.check();
        return returnAST;
    }

    private BasicParser.FunctionContext checkReturnInFunction(ParserRuleContext ctx) {
        while(!(ctx.getParent() instanceof BasicParser.FunctionContext)) {
            ctx = ctx.getParent();

            if(ctx instanceof BasicParser.ProgramContext) {
                error(ctx, "is supposed to be in a function");
            }
        }

        return (BasicParser.FunctionContext) ctx.getParent();
    }

    @Override
    public ExitAST visitExit(BasicParser.ExitContext ctx) {
        ExpressionAST expression = visitExpression(ctx.expression());
        ExitAST exit = new ExitAST(ctx, expression);
        exit.check();
        return exit;
    }

    @Override
    public PrintAST visitPrint(BasicParser.PrintContext ctx) {
        return new PrintAST(ctx, visitExpression(ctx.expression()));
    }

    @Override
    public FunctionDeclAST visitFunction(BasicParser.FunctionContext ctx) {
        Visitor.ST = new SymbolTable(Visitor.ST);
        FunctionDeclAST function;
        if (ctx.paramlist() == null) {
            //no params
            function = new FunctionDeclAST(ctx, visitType(ctx.type()), ctx.IDENT().getText());
        } else {
            //has params
            function = new FunctionDeclAST(ctx, visitType(ctx.type()), ctx.IDENT().getText(),
                    visitParamlist(ctx.paramlist()));
        }

        function.check();
        visitChildren(ctx);

        Visitor.ST = Visitor.ST.getEncSymbolTable();
        String funcName = ctx.IDENT().getText();

        IDENTIFIER func = ST.lookUp(funcName);

        if(func == null ||
                ((func instanceof FUNCTION) && ((FUNCTION) func).getReturntype() == null)) {
            Visitor.ST.add(funcName, function.getIdentObj());
        } else {
            error(ctx, "function " + funcName + " already existed");
        }

        return function;
    }

    @Override
    public PrintlnAST visitPrintln(BasicParser.PrintlnContext ctx) {
        PrintlnAST print = new PrintlnAST(ctx, visitExpression(ctx.expression()));
        print.check();
        return print;
    }

    @Override
    public IfAST visitIf(BasicParser.IfContext ctx) {
        //components
        ExpressionAST expr = visitExpression(ctx.expression());
        Visitor.ST = new SymbolTable(Visitor.ST);
        StatementAST then = visitStatement(ctx.statement(0));
        Visitor.ST = Visitor.ST.getEncSymbolTable();

        Visitor.ST = new SymbolTable(Visitor.ST);
        StatementAST elseSt = visitStatement(ctx.statement(1));
        Visitor.ST = Visitor.ST.getEncSymbolTable();

        IfAST ifAST = new IfAST(ctx, expr, then, elseSt);
        ifAST.check();
        return ifAST;
    }

    @Override
    public WhileAST visitWhile(BasicParser.WhileContext ctx) {
        Visitor.ST = new SymbolTable(Visitor.ST);
        //components
        ExpressionAST expr = visitExpression(ctx.expression());
        StatementAST statement = visitStatement(ctx.statement());

        WhileAST whileAST = new WhileAST(ctx, expr, statement);
        whileAST.check();
        Visitor.ST = Visitor.ST.getEncSymbolTable();
        return whileAST;
    }

    @Override
    public BeginAST visitBegin(BasicParser.BeginContext ctx) {
        Visitor.ST = new SymbolTable(Visitor.ST);
        BeginAST begin = new BeginAST(ctx, visitStatement(ctx.statement()));
        Visitor.ST = Visitor.ST.getEncSymbolTable();
        return begin;
    }

    @Override
    public SequenceAST visitSequence(BasicParser.SequenceContext ctx) {
        List<BasicParser.StatementContext> statements = ctx.statement();
        List<StatementAST> statementASTs = new ArrayList<>();

        for (BasicParser.StatementContext s : statements) {
            statementASTs.add(visitStatement(s));
        }

        SequenceAST sequence = new SequenceAST(ctx, statementASTs);
        return sequence;
    }

    @Override
    public AssignlhsAST visitAssignlhs(BasicParser.AssignlhsContext ctx) {
        AssignlhsAST lhs;
        if (ctx.IDENT() != null) {
            lhs = new AssignlhsAST(ctx, ctx.IDENT().getText());
        } else if (ctx.arrayelem() != null){
            lhs = new AssignlhsAST(ctx, visitArrayelem(ctx.arrayelem()));
        } else {
            lhs = new AssignlhsAST(ctx, visitPairelem(ctx.pairelem()));
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

        return new ArraylitAST(ctx, expressionASTs);
    }

    @Override
    public NewpairAST visitNewpair(BasicParser.NewpairContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<ExpressionAST> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }

        NewpairAST newpair = new NewpairAST(ctx, expressionNodes);
        return  newpair;
    }

    @Override
    public PairelemAST visitPairelement(BasicParser.PairelementContext ctx) {
        return visitPairelem(ctx.pairelem());
    }

    @Override
    public CallAST visitFunctioncall(BasicParser.FunctioncallContext ctx) {
        CallAST call = new CallAST(ctx, ctx.IDENT().getText(), visitArglist(ctx.arglist()));

        /*
        If the function has not been declared, put the assignment statement (callAST's parent) inside the toBeVisited
        list and revisit it at the end
         */
        if(ST.lookUp(ctx.IDENT().getText()) == null) {
            toBeVisited.add(call);
        }

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

            ArglistAST arglist = new ArglistAST(ctx, expressionNodes);
            return arglist;
        }

        return new ArglistAST(ctx, new ArrayList<ExpressionAST>());
    }

    public PairelemAST visitPairelem(BasicParser.PairelemContext ctx) {
        String token = null;
        if (ctx.FIRST() != null) {
            token = ctx.FIRST().getText();
        } else if (ctx.SECOND() != null) {
            token = ctx.SECOND().getText();
        }
        return new PairelemAST(ctx, token, visitExpression(ctx.expression()));
    }

    @Override
    public ArrayelemAST visitArrayelem(BasicParser.ArrayelemContext ctx) {
        List<BasicParser.ExpressionContext> expressions = ctx.expression();
        List<Node> expressionNodes = new ArrayList<>();
        for (BasicParser.ExpressionContext e : expressions) {
            expressionNodes.add(visitExpression(e));
        }
        return new ArrayelemAST(ctx, ctx.IDENT().getText(), expressionNodes);
    }

    @Override
    public ExpressionAST visitExpression(BasicParser.ExpressionContext ctx) {
        if(ctx.exprNoBinOp() != null) {
            return visitExprNoBinOp(ctx.exprNoBinOp());
        } else if(ctx.binOp() != null) {
            return visitBinOp(ctx.binOp());
        }

        return null;
    }

    @Override
    public ExpressionAST visitExprNoBinOp(BasicParser.ExprNoBinOpContext ctx) {
        ExpressionAST expression = null;

        if (ctx.IDENT()!= null) {
            expression = new IdentAST(ctx, ctx.IDENT().getText());
        } else if (ctx.PAIRLITERAL() != null) {
            expression = new PairliterAST(ctx, ctx.PAIRLITERAL() .getText());
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
        } else if (ctx.unop() != null){
            expression = new UnopAST(ctx, visitExpression(ctx.expression()), ctx.unop().getText());
        } else if (ctx.LPAREN() != null) {
            expression = visitExpression(ctx.expression());
        }

        if(expression != null) {
            expression.checkNode();
        }

        return expression;
    }

    @Override
    public ExpressionAST visitBinOp(BasicParser.BinOpContext ctx) {
        ExpressionAST binOpAST = null;

        if(ctx.p1() != null) {
            binOpAST = visitP1(ctx.p1());
        } else if(ctx.p2() != null) {
            binOpAST = visitP2(ctx.p2());
        } else if(ctx.p3() != null) {
            binOpAST = visitP3(ctx.p3());
        } else if(ctx.p4() != null) {
            binOpAST = visitP4(ctx.p4());
        } else if(ctx.p5() != null) {
            binOpAST = visitP5(ctx.p5());
        } else if(ctx.p6() != null) {
            binOpAST = visitP6(ctx.p6());
        }

        if(binOpAST != null) {
            binOpAST.checkNode();
        }
        return binOpAST;
    }

    @Override
    public ExpressionAST visitP1(BasicParser.P1Context ctx) {
        String op;

        if (ctx.p1() == null) {
            return visitExprNoBinOp(ctx.exprNoBinOp());
        }

        if(ctx.DIV() != null) {
            op = ctx.DIV().getText();
        } else if (ctx.MOD() != null) {
            op = ctx.MOD().getText();
        } else {
            op = ctx.STAR().getText();
        }
        return new BinOpAST(ctx, op,visitExprNoBinOp(ctx.exprNoBinOp()), visitP1(ctx.p1()));
    }

    @Override
    public ExpressionAST visitP2(BasicParser.P2Context ctx) {
        String op;

        if(ctx.p2() == null) {
            return visitP1(ctx.p1());
        }

        if(ctx.MINUS() != null) {
            op = ctx.MINUS().getText();
        } else {
            op = ctx.PLUS().getText();
        }

        return new BinOpAST(ctx, op,visitP1(ctx.p1()), visitP2(ctx.p2()));
    }

    @Override
    public ExpressionAST visitP3(BasicParser.P3Context ctx) {
        String op;

        if(ctx.p3() == null) {
            return visitP2(ctx.p2());
        }

        if(ctx.GREATER() != null) {
            op = ctx.GREATER().getText();
        } else if (ctx.GREATEREQUAL() != null) {
            op = ctx.GREATEREQUAL().getText();
        } else if(ctx.LESS() != null) {
            op = ctx.LESS().getText();
        } else {
            op = ctx.LESSEQUAL().getText();
        }
        return new BinOpAST(ctx, op,visitP2(ctx.p2()), visitP3(ctx.p3()));
    }

    @Override
    public ExpressionAST visitP4(BasicParser.P4Context ctx) {
        String op;

        if(ctx.p4() == null) {
            return visitP3(ctx.p3());
        }

        if(ctx.EQUAL()!= null) {
            op = ctx.EQUAL().getText();
        } else {
            op = ctx.NOTEQUAL().getText();
        }
        return new BinOpAST(ctx, op,visitP3(ctx.p3()), visitP4(ctx.p4()));
    }

    @Override
    public ExpressionAST visitP5(BasicParser.P5Context ctx) {
        if(ctx.p5() == null) {
            return visitP4(ctx.p4());
        }

        return new BinOpAST(ctx, ctx.AND().getText(),visitP4(ctx.p4()), visitP5(ctx.p5()));
    }

    @Override
    public ExpressionAST visitP6(BasicParser.P6Context ctx) {
        if(ctx.p6() == null) {
            return visitP5(ctx.p5());
        }

        return new BinOpAST(ctx, ctx.OR().getText(),visitP5(ctx.p5()), visitP6(ctx.p6()));
    }

    @Override
    public IntLiterAST visitIntliter(BasicParser.IntliterContext ctx) {
        String sign = ctx.intsign() != null ? ctx.intsign().getText() : "";
        return new IntLiterAST(ctx, sign, ctx.DIGIT().toString());
    }

    @Override
    public IntSignAST visitIntsign(BasicParser.IntsignContext ctx) {
        return new IntSignAST(ctx);
    }

    @Override
    public BoolliterAST visitBoolliter(BasicParser.BoolliterContext ctx) {
        return new BoolliterAST(ctx, ctx.getText());
    }

    @Override
    public CharLitAST visitCharliter(BasicParser.CharliterContext ctx) {
        return new CharLitAST(ctx, ctx.getText());
    }

    @Override
    public StringLiterAST visitStrliter(BasicParser.StrliterContext ctx) {
        return new StringLiterAST(ctx, ctx.getText());
    }

    @Override
    public ParamlistAST visitParamlist(BasicParser.ParamlistContext ctx) {
        if(!ctx.param().isEmpty()) {
            List<BasicParser.ParamContext> parameters = ctx.param();
            List<ParamAST> parameterNodes = new ArrayList<>();
            for (BasicParser.ParamContext p :parameters) {
                parameterNodes.add(visitParam(p));
            }

            ParamlistAST paramlist = new ParamlistAST(ctx, parameterNodes);
            paramlist.check();
            return paramlist;
        } else {
            return new ParamlistAST(ctx, new ArrayList<ParamAST>());
        }
    }

    @Override
    public ParamAST visitParam(BasicParser.ParamContext ctx) {
        ParamAST param = new ParamAST(ctx, visitType(ctx.type()), ctx.IDENT()
                .getText());
        return param;
    }



    @Override
    public TypeAST visitType(BasicParser.TypeContext ctx) {
        if (ctx.basetype() != null) {
            return visitBasetype(ctx.basetype());
        } else if (ctx.arraytype() != null) {
            return visitArraytype(ctx.arraytype());
        } else if (ctx.pairtype() != null) {
            return visitPairtype(ctx.pairtype());
        }

        return null;
    }

    @Override
    public BasetypeAST visitBasetype(BasicParser.BasetypeContext ctx) {
        BasetypeAST baseType = new BasetypeAST(ctx, ctx.getText());
        baseType.check();
        return baseType;
    }

    @Override
    public ArraytypeAST visitArraytype(BasicParser.ArraytypeContext ctx) {
        ArraytypeAST arraytype = null;
        int arrayDepth = ctx.LBRACKET().size();
        if (ctx.basetype() != null) {
            arraytype = new ArraytypeAST(ctx, visitBasetype(ctx.basetype()), arrayDepth);
            arraytype.check();
        } else if (ctx.pairtype() != null) {
            arraytype = new ArraytypeAST(ctx, visitPairtype(ctx.pairtype()), arrayDepth);
            arraytype.check();
        }

        return arraytype;


    }

   @Override
    public PairtypeAST visitPairtype(BasicParser.PairtypeContext ctx) {
       PairtypeAST pairtype = new PairtypeAST(ctx, visitPairelemtype(ctx.pairelemtype(0)), visitPairelemtype(ctx.pairelemtype(1)));
       pairtype.check();
       return pairtype;
   }

    @Override
    public PairelemtypeAST visitPairelemtype(BasicParser.PairelemtypeContext ctx) {
        PairelemtypeAST pairelemtype = null;
        if (ctx.PAIR() != null) {
            pairelemtype = new PairelemtypeAST(ctx, ctx.PAIR().getText());
            pairelemtype.check();
        } else if (ctx.basetype() != null) {
            pairelemtype = new PairelemtypeAST(ctx, visitBasetype(ctx.basetype()));
            pairelemtype.check();
        } else if (ctx.arraytype() != null) {
            pairelemtype =  new PairelemtypeAST(ctx, visitArraytype(ctx.arraytype()));
            pairelemtype.check();
        }
        return pairelemtype;
    }

    public void checkUndefinedFunc() {
       for(CallAST node : toBeVisited) {
           node.setReVisited();
           node.check();
       }
    }

    public static void error(ParserRuleContext ctx, String message) {
        System.err.println("line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine()
                + " " + ctx.start.getText() + " " + message);
        System.exit(200);
    }

}
