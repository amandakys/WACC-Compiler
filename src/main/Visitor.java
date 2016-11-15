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
import symbol_table.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Visitor extends BasicParserBaseVisitor<Node>{
    public static SymbolTable ST = new SymbolTable(null);
    private static List<ParserRuleContext> toBeVisited = new ArrayList<>();

    public Visitor() {
        ST.add("bool", new SCALAR("bool"));
        ST.add("int", new SCALAR("int"));
        ST.add("char", new SCALAR("char"));
        ST.add("string", new STRING());

        ST = new SymbolTable(ST);
    }

    public StatementAST visitStatement(BasicParser.StatementContext ctx) {
        if (ctx instanceof BasicParser.Var_declContext) {
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
    public AssignmentAST visitAssignment(BasicParser.AssignmentContext ctx) {
        AssignmentAST assignment = new AssignmentAST(ctx, visitAssignlhs(ctx.assignlhs()), visitAssignrhs(ctx.assignrhs()));
        assignment.checkNode();
        return assignment;
    }

    @Override
    public VarDeclAST visitVar_decl(BasicParser.Var_declContext ctx) {
        VarDeclAST var = new VarDeclAST(ctx, visitType(ctx.type()), ctx.IDENT()
                .getText(), visitAssignrhs(ctx.assignrhs()));
        var.checkNode();
        return var;
    }

    @Override
    public StatementAST visitRead(BasicParser.ReadContext ctx) {
        AssignlhsAST assignlhs = visitAssignlhs(ctx.assignlhs());
        assignlhs.check();
        TYPE t = assignlhs.getType(); //check that expresison is of a type acceptable to read

        TYPE intType = Visitor.ST.lookUpAll("int").getType();

        TYPE charType = Visitor.ST.lookUpAll("char").getType();

        if (!t.equals(intType) && !t.equals(charType)){
            //error
            error(ctx,"read only takes int or char types");
        }
        return null;
    }

    @Override
    public StatementAST visitFree(BasicParser.FreeContext ctx) {
        ExpressionAST expression = visitExpression(ctx.expression());
        expression.checkNode();

        if (!(expression.getType() instanceof PAIR)) {
            error(ctx, "free must take a pair type");
        }

        return null;
    }

    @Override
    public StatementAST visitReturn(BasicParser.ReturnContext ctx) {

        TypeAST returnType = visitType((checkNodeReturnInFunction(ctx)).type());
        ExpressionAST expression = visitExpression(ctx.expression());
        //check that the expression returns the same type as the expected type
        returnType.checkType(expression);

        expression.checkNode();
        return null;
    }

    private BasicParser.FunctionContext checkNodeReturnInFunction(ParserRuleContext ctx) {
        while(!(ctx.getParent() instanceof BasicParser.FunctionContext)) {
            ctx = ctx.getParent();

            if(ctx instanceof BasicParser.ProgramContext) {
                error(ctx, "is supposed to be in a function");
            }
        }

        return (BasicParser.FunctionContext) ctx.getParent();
    }

    @Override
    public StatementAST visitExit(BasicParser.ExitContext ctx) {
        ExpressionAST expression = visitExpression(ctx.expression());;
        IDENTIFIER T = Visitor.ST.lookUpAll("int");
        expression.checkNode();
        if(!expression.getType().equals(T.getType())) {
            error(ctx, "Exit statement must take integer");
        }
        return null;
    }

    @Override
    public StatementAST visitPrint(BasicParser.PrintContext ctx) {
        ExpressionAST expression = visitExpression(ctx.expression());
        expression.checkNode();
        return null;
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

        function.checkNode();
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
    public StatementAST visitPrintln(BasicParser.PrintlnContext ctx) {
        ExpressionAST expr = visitExpression(ctx.expression());
        expr.checkNode();
        return null;
    }

    @Override
    public StatementAST visitIf(BasicParser.IfContext ctx) {
        //components
        ExpressionAST expr = visitExpression(ctx.expression());
        StatementAST then = visitStatement(ctx.statement(0));
        StatementAST elseSt = visitStatement(ctx.statement(1));

        expr.checkNode();
        IDENTIFIER T = Visitor.ST.lookUpAll("bool");
        if (!expr.getType().equals(T)) {
            error(ctx, "If condition type mismatch");
        }
        //check statements are valid
        then.checkNode();
        elseSt.checkNode();
        return null;
    }

    @Override
    public StatementAST visitWhile(BasicParser.WhileContext ctx) {
        //components
        ExpressionAST expression = visitExpression(ctx.expression());
        StatementAST statement = visitStatement(ctx.statement());

        //check that expression is valid
        expression.checkNode();

        if(expression.getType().equals(Visitor.ST.lookUpAll("bool"))) {
            //check that statement is valid
            statement.checkNode();
        } else {
            error(ctx, "expression is not of type boolean");
        }
        return null;
    }

    @Override
    public StatementAST visitBegin(BasicParser.BeginContext ctx) {
        Visitor.ST = new SymbolTable(Visitor.ST);
        visitStatement(ctx.statement());
        Visitor.ST = Visitor.ST.getEncSymbolTable();
        return null;
    }

    @Override
    public StatementAST visitSequence(BasicParser.SequenceContext ctx) {
        List<BasicParser.StatementContext> statements = ctx.statement();

        for (BasicParser.StatementContext s : statements) {
            visitStatement(s);
        }
        return null;
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
            toBeVisited.add(ctx.getParent());
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
            paramlist.checkNode();
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
        baseType.checkNode();
        return baseType;
    }

    @Override
    public ArraytypeAST visitArraytype(BasicParser.ArraytypeContext ctx) {
        ArraytypeAST arraytype = null;
        int arrayDepth = ctx.LBRACKET().size();
        if (ctx.basetype() != null) {
            arraytype = new ArraytypeAST(ctx, visitBasetype(ctx.basetype()), arrayDepth);
            arraytype.checkNode();
        } else if (ctx.pairtype() != null) {
            arraytype = new ArraytypeAST(ctx, visitPairtype(ctx.pairtype()), arrayDepth);
            arraytype.checkNode();
        }

        return arraytype;


    }

   @Override
    public PairtypeAST visitPairtype(BasicParser.PairtypeContext ctx) {
       PairtypeAST pairtype = new PairtypeAST(ctx, visitPairelemtype(ctx.pairelemtype(0)), visitPairelemtype(ctx.pairelemtype(1)));
       pairtype.checkNode();
       return pairtype;
   }

    @Override
    public PairelemtypeAST visitPairelemtype(BasicParser.PairelemtypeContext ctx) {
        PairelemtypeAST pairelemtype = null;
        if (ctx.PAIR() != null) {
            pairelemtype = new PairelemtypeAST(ctx, ctx.PAIR().getText());
            pairelemtype.checkNode();
        } else if (ctx.basetype() != null) {
            pairelemtype = new PairelemtypeAST(ctx, visitBasetype(ctx.basetype()));
            pairelemtype.checkNode();
        } else if (ctx.arraytype() != null) {
            pairelemtype =  new PairelemtypeAST(ctx, visitArraytype(ctx.arraytype()));
            pairelemtype.checkNode();
        }
        return pairelemtype;
    }

    public void checkUndefinedFunc() {
       for(ParserRuleContext ctx : toBeVisited ) {
           visit(ctx);
       }
    }

    public static void error(ParserRuleContext ctx, String message) {
        System.err.println("line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine()
                + " " + ctx.start.getText() + " " + message);
        System.exit(200);
    }

}
