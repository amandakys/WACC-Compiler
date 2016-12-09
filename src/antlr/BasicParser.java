// Generated from ./BasicParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PRINT=10, NEWPAIR=27, DO=18, CHR=31, MINUS=45, PAIRLITERAL=28, ELSE=15, 
		EXCLUDECHAR=63, IF=13, LESSEQUAL=53, DONE=19, LPAREN=38, TRUE=32, IS=3, 
		RPAREN=39, STRINGLITERAL=62, READ=6, NOTEQUAL=55, NOT=44, CHARCLOSE=64, 
		AND=56, END=2, THEN=14, LESS=52, EXIT=9, PLUS=49, ORD=30, CALL=12, EXCLUDESTRING=65, 
		PRINTLN=11, SEMI=43, CHAR=22, BEGIN=1, ASSIGN=5, FREE=7, INT=20, COMMENT=35, 
		RETURN=8, CHARLITERAL=61, SKIP=4, WS=58, COMMA=42, EOL=34, MOD=48, OR=57, 
		STRINGCLOSE=66, EQUAL=54, ENDIF=16, GREATER=50, LBRACKET=40, RBRACKET=41, 
		FIRST=24, DIGIT=36, ESCAPE=59, DIV=47, OTHER=67, LEN=29, IDENT=37, STAR=46, 
		BOOL=21, ESC_SLASH=60, GREATEREQUAL=51, STRING=23, WHILE=17, SECOND=25, 
		FALSE=33, PAIR=26;
	public static final String[] tokenNames = {
		"<INVALID>", "'begin'", "'end'", "'is'", "'skip'", "'='", "'read'", "'free'", 
		"'return'", "'exit'", "'print'", "'println'", "'call'", "'if'", "'then'", 
		"'else'", "'fi'", "'while'", "'do'", "'done'", "'int'", "'bool'", "'char'", 
		"'string'", "'fst'", "'snd'", "'pair'", "'newpair'", "'null'", "'len'", 
		"'ord'", "'chr'", "'true'", "'false'", "EOL", "COMMENT", "DIGIT", "IDENT", 
		"'('", "')'", "'['", "']'", "','", "';'", "'!'", "'-'", "'*'", "'/'", 
		"'%'", "'+'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", 
		"WS", "ESCAPE", "'\\'", "CHARLITERAL", "STRINGLITERAL", "EXCLUDECHAR", 
		"CHARCLOSE", "EXCLUDESTRING", "STRINGCLOSE", "OTHER"
	};
	public static final int
		RULE_program = 0, RULE_function = 1, RULE_paramlist = 2, RULE_param = 3, 
		RULE_statement = 4, RULE_assignlhs = 5, RULE_assignrhs = 6, RULE_arglist = 7, 
		RULE_pairelem = 8, RULE_type = 9, RULE_basetype = 10, RULE_arraytype = 11, 
		RULE_pairtype = 12, RULE_pairelemtype = 13, RULE_exprNoBinOp = 14, RULE_expression = 15, 
		RULE_binOp = 16, RULE_p1 = 17, RULE_p2 = 18, RULE_p3 = 19, RULE_p4 = 20, 
		RULE_p5 = 21, RULE_p6 = 22, RULE_intliter = 23, RULE_unop = 24, RULE_arrayelem = 25, 
		RULE_boolliter = 26, RULE_charliter = 27, RULE_strliter = 28, RULE_character = 29, 
		RULE_arrayliter = 30;
	public static final String[] ruleNames = {
		"program", "function", "paramlist", "param", "statement", "assignlhs", 
		"assignrhs", "arglist", "pairelem", "type", "basetype", "arraytype", "pairtype", 
		"pairelemtype", "exprNoBinOp", "expression", "binOp", "p1", "p2", "p3", 
		"p4", "p5", "p6", "intliter", "unop", "arrayelem", "boolliter", "charliter", 
		"strliter", "character", "arrayliter"
	};

	@Override
	public String getGrammarFileName() { return "BasicParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(BasicParser.EOF, 0); }
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(BEGIN);
			setState(66);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(63); function();
					}
					} 
				}
				setState(68);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(69); statement(0);
			setState(70); match(END);
			setState(71); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public ParamlistContext paramlist() {
			return getRuleContext(ParamlistContext.class,0);
		}
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public TerminalNode LPAREN() { return getToken(BasicParser.LPAREN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BasicParser.RPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73); type();
			setState(74); match(IDENT);
			setState(75); match(LPAREN);
			setState(77);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << PAIR))) != 0)) {
				{
				setState(76); paramlist();
				}
			}

			setState(79); match(RPAREN);
			setState(80); match(IS);
			setState(81); statement(0);
			setState(82); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamlistContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ParamlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramlist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParamlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamlistContext paramlist() throws RecognitionException {
		ParamlistContext _localctx = new ParamlistContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_paramlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); param();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(85); match(COMMA);
				setState(86); param();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); type();
			setState(93); match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReadContext extends StatementContext {
		public AssignlhsContext assignlhs() {
			return getRuleContext(AssignlhsContext.class,0);
		}
		public TerminalNode READ() { return getToken(BasicParser.READ, 0); }
		public ReadContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitRead(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignmentContext extends StatementContext {
		public TerminalNode ASSIGN() { return getToken(BasicParser.ASSIGN, 0); }
		public AssignlhsContext assignlhs() {
			return getRuleContext(AssignlhsContext.class,0);
		}
		public AssignrhsContext assignrhs() {
			return getRuleContext(AssignrhsContext.class,0);
		}
		public AssignmentContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SkipContext extends StatementContext {
		public TerminalNode SKIP() { return getToken(BasicParser.SKIP, 0); }
		public SkipContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitSkip(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileContext extends StatementContext {
		public TerminalNode DONE() { return getToken(BasicParser.DONE, 0); }
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhileContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DowhileContext extends StatementContext {
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DowhileContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitDowhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExitContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EXIT() { return getToken(BasicParser.EXIT, 0); }
		public ExitContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SequenceContext extends StatementContext {
		public TerminalNode SEMI() { return getToken(BasicParser.SEMI, 0); }
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public SequenceContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitSequence(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintContext extends StatementContext {
		public TerminalNode PRINT() { return getToken(BasicParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintlnContext extends StatementContext {
		public TerminalNode PRINTLN() { return getToken(BasicParser.PRINTLN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintlnContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPrintln(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Var_declContext extends StatementContext {
		public TerminalNode ASSIGN() { return getToken(BasicParser.ASSIGN, 0); }
		public AssignrhsContext assignrhs() {
			return getRuleContext(AssignrhsContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public Var_declContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitVar_decl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FreeContext extends StatementContext {
		public TerminalNode FREE() { return getToken(BasicParser.FREE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FreeContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFree(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends StatementContext {
		public TerminalNode ELSE() { return getToken(BasicParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(BasicParser.IF, 0); }
		public TerminalNode THEN() { return getToken(BasicParser.THEN, 0); }
		public TerminalNode ENDIF() { return getToken(BasicParser.ENDIF, 0); }
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IfContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BeginContext extends StatementContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BeginContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBegin(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(BasicParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		return statement(0);
	}

	private StatementContext statement(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementContext _localctx = new StatementContext(_ctx, _parentState);
		StatementContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_statement, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			switch (_input.LA(1)) {
			case SKIP:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(96); match(SKIP);
				}
				break;
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
			case PAIR:
				{
				_localctx = new Var_declContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97); type();
				setState(98); match(IDENT);
				setState(99); match(ASSIGN);
				setState(100); assignrhs();
				}
				break;
			case FIRST:
			case SECOND:
			case IDENT:
				{
				_localctx = new AssignmentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(102); assignlhs();
				setState(103); match(ASSIGN);
				setState(104); assignrhs();
				}
				break;
			case READ:
				{
				_localctx = new ReadContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106); match(READ);
				setState(107); assignlhs();
				}
				break;
			case FREE:
				{
				_localctx = new FreeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108); match(FREE);
				setState(109); expression();
				}
				break;
			case RETURN:
				{
				_localctx = new ReturnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110); match(RETURN);
				setState(111); expression();
				}
				break;
			case EXIT:
				{
				_localctx = new ExitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112); match(EXIT);
				setState(113); expression();
				}
				break;
			case PRINT:
				{
				_localctx = new PrintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(114); match(PRINT);
				setState(115); expression();
				}
				break;
			case PRINTLN:
				{
				_localctx = new PrintlnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116); match(PRINTLN);
				setState(117); expression();
				}
				break;
			case IF:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118); match(IF);
				setState(119); expression();
				setState(120); match(THEN);
				setState(121); statement(0);
				setState(122); match(ELSE);
				setState(123); statement(0);
				setState(124); match(ENDIF);
				}
				break;
			case WHILE:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126); match(WHILE);
				setState(127); expression();
				setState(128); match(DO);
				setState(129); statement(0);
				setState(130); match(DONE);
				}
				break;
			case BEGIN:
				{
				_localctx = new BeginContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132); match(BEGIN);
				setState(133); statement(0);
				setState(134); match(END);
				}
				break;
			case DO:
				{
				_localctx = new DowhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136); match(DO);
				setState(137); statement(0);
				setState(138); match(WHILE);
				setState(139); expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(148);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SequenceContext(new StatementContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_statement);
					setState(143);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(144); match(SEMI);
					setState(145); statement(3);
					}
					} 
				}
				setState(150);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignlhsContext extends ParserRuleContext {
		public PairelemContext pairelem() {
			return getRuleContext(PairelemContext.class,0);
		}
		public ArrayelemContext arrayelem() {
			return getRuleContext(ArrayelemContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public AssignlhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignlhs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignlhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignlhsContext assignlhs() throws RecognitionException {
		AssignlhsContext _localctx = new AssignlhsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignlhs);
		try {
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(151); match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152); arrayelem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(153); pairelem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignrhsContext extends ParserRuleContext {
		public AssignrhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignrhs; }
	 
		public AssignrhsContext() { }
		public void copyFrom(AssignrhsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewpairContext extends AssignrhsContext {
		public TerminalNode NEWPAIR() { return getToken(BasicParser.NEWPAIR, 0); }
		public TerminalNode LPAREN() { return getToken(BasicParser.LPAREN, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(BasicParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public NewpairContext(AssignrhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitNewpair(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctioncallContext extends AssignrhsContext {
		public TerminalNode LPAREN() { return getToken(BasicParser.LPAREN, 0); }
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public TerminalNode RPAREN() { return getToken(BasicParser.RPAREN, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public FunctioncallContext(AssignrhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunctioncall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprContext extends AssignrhsContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprContext(AssignrhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArraylitContext extends AssignrhsContext {
		public ArrayliterContext arrayliter() {
			return getRuleContext(ArrayliterContext.class,0);
		}
		public ArraylitContext(AssignrhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArraylit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairelementContext extends AssignrhsContext {
		public PairelemContext pairelem() {
			return getRuleContext(PairelemContext.class,0);
		}
		public PairelementContext(AssignrhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairelement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignrhsContext assignrhs() throws RecognitionException {
		AssignrhsContext _localctx = new AssignrhsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignrhs);
		int _la;
		try {
			setState(173);
			switch (_input.LA(1)) {
			case PAIRLITERAL:
			case LEN:
			case ORD:
			case CHR:
			case TRUE:
			case FALSE:
			case DIGIT:
			case IDENT:
			case LPAREN:
			case NOT:
			case MINUS:
			case PLUS:
			case CHARLITERAL:
			case STRINGLITERAL:
				_localctx = new ExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(156); expression();
				}
				break;
			case LBRACKET:
				_localctx = new ArraylitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(157); arrayliter();
				}
				break;
			case NEWPAIR:
				_localctx = new NewpairContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(158); match(NEWPAIR);
				setState(159); match(LPAREN);
				setState(160); expression();
				setState(161); match(COMMA);
				setState(162); expression();
				setState(163); match(RPAREN);
				}
				break;
			case FIRST:
			case SECOND:
				_localctx = new PairelementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(165); pairelem();
				}
				break;
			case CALL:
				_localctx = new FunctioncallContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(166); match(CALL);
				setState(167); match(IDENT);
				setState(168); match(LPAREN);
				setState(170);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIRLITERAL) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << DIGIT) | (1L << IDENT) | (1L << LPAREN) | (1L << NOT) | (1L << MINUS) | (1L << PLUS) | (1L << CHARLITERAL) | (1L << STRINGLITERAL))) != 0)) {
					{
					setState(169); arglist();
					}
				}

				setState(172); match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArglistContext extends ParserRuleContext {
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArglist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arglist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); expression();
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(176); match(COMMA);
				setState(177); expression();
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairelemContext extends ParserRuleContext {
		public TerminalNode SECOND() { return getToken(BasicParser.SECOND, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode FIRST() { return getToken(BasicParser.FIRST, 0); }
		public PairelemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairelem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairelem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairelemContext pairelem() throws RecognitionException {
		PairelemContext _localctx = new PairelemContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pairelem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_la = _input.LA(1);
			if ( !(_la==FIRST || _la==SECOND) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(184); expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public ArraytypeContext arraytype() {
			return getRuleContext(ArraytypeContext.class,0);
		}
		public PairtypeContext pairtype() {
			return getRuleContext(PairtypeContext.class,0);
		}
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type);
		try {
			setState(189);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(186); basetype();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(187); arraytype();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(188); pairtype();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasetypeContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(BasicParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(BasicParser.CHAR, 0); }
		public TerminalNode INT() { return getToken(BasicParser.INT, 0); }
		public BasetypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basetype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBasetype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BasetypeContext basetype() throws RecognitionException {
		BasetypeContext _localctx = new BasetypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_basetype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArraytypeContext extends ParserRuleContext {
		public List<TerminalNode> RBRACKET() { return getTokens(BasicParser.RBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(BasicParser.LBRACKET, i);
		}
		public TerminalNode RBRACKET(int i) {
			return getToken(BasicParser.RBRACKET, i);
		}
		public PairtypeContext pairtype() {
			return getRuleContext(PairtypeContext.class,0);
		}
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(BasicParser.LBRACKET); }
		public ArraytypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arraytype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArraytype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArraytypeContext arraytype() throws RecognitionException {
		ArraytypeContext _localctx = new ArraytypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arraytype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(193); basetype();
				}
				break;
			case PAIR:
				{
				setState(194); pairtype();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(199); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(197); match(LBRACKET);
				setState(198); match(RBRACKET);
				}
				}
				setState(201); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LBRACKET );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairtypeContext extends ParserRuleContext {
		public List<PairelemtypeContext> pairelemtype() {
			return getRuleContexts(PairelemtypeContext.class);
		}
		public PairelemtypeContext pairelemtype(int i) {
			return getRuleContext(PairelemtypeContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(BasicParser.LPAREN, 0); }
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(BasicParser.RPAREN, 0); }
		public PairtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairtype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairtype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairtypeContext pairtype() throws RecognitionException {
		PairtypeContext _localctx = new PairtypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pairtype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); match(PAIR);
			setState(204); match(LPAREN);
			setState(205); pairelemtype();
			setState(206); match(COMMA);
			setState(207); pairelemtype();
			setState(208); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairelemtypeContext extends ParserRuleContext {
		public ArraytypeContext arraytype() {
			return getRuleContext(ArraytypeContext.class,0);
		}
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public PairelemtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairelemtype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairelemtype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairelemtypeContext pairelemtype() throws RecognitionException {
		PairelemtypeContext _localctx = new PairelemtypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pairelemtype);
		try {
			setState(213);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(210); basetype();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(211); arraytype();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(212); match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprNoBinOpContext extends ParserRuleContext {
		public BoolliterContext boolliter() {
			return getRuleContext(BoolliterContext.class,0);
		}
		public CharliterContext charliter() {
			return getRuleContext(CharliterContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(BasicParser.LPAREN, 0); }
		public IntliterContext intliter() {
			return getRuleContext(IntliterContext.class,0);
		}
		public StrliterContext strliter() {
			return getRuleContext(StrliterContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BasicParser.RPAREN, 0); }
		public TerminalNode PAIRLITERAL() { return getToken(BasicParser.PAIRLITERAL, 0); }
		public ArrayelemContext arrayelem() {
			return getRuleContext(ArrayelemContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public UnopContext unop() {
			return getRuleContext(UnopContext.class,0);
		}
		public ExprNoBinOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprNoBinOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExprNoBinOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprNoBinOpContext exprNoBinOp() throws RecognitionException {
		ExprNoBinOpContext _localctx = new ExprNoBinOpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprNoBinOp);
		try {
			setState(229);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(215); intliter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(216); boolliter();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(217); charliter();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(218); strliter();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(219); match(PAIRLITERAL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(220); match(IDENT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(221); arrayelem();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(222); match(LPAREN);
				setState(223); expression();
				setState(224); match(RPAREN);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(226); unop();
				setState(227); expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public BinOpContext binOp() {
			return getRuleContext(BinOpContext.class,0);
		}
		public ExprNoBinOpContext exprNoBinOp() {
			return getRuleContext(ExprNoBinOpContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expression);
		try {
			setState(233);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(231); exprNoBinOp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232); binOp();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinOpContext extends ParserRuleContext {
		public P3Context p3() {
			return getRuleContext(P3Context.class,0);
		}
		public P1Context p1() {
			return getRuleContext(P1Context.class,0);
		}
		public P2Context p2() {
			return getRuleContext(P2Context.class,0);
		}
		public P4Context p4() {
			return getRuleContext(P4Context.class,0);
		}
		public P5Context p5() {
			return getRuleContext(P5Context.class,0);
		}
		public P6Context p6() {
			return getRuleContext(P6Context.class,0);
		}
		public BinOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binOp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinOpContext binOp() throws RecognitionException {
		BinOpContext _localctx = new BinOpContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_binOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(235); p1();
				}
				break;
			case 2:
				{
				setState(236); p2();
				}
				break;
			case 3:
				{
				setState(237); p3();
				}
				break;
			case 4:
				{
				setState(238); p4();
				}
				break;
			case 5:
				{
				setState(239); p5();
				}
				break;
			case 6:
				{
				setState(240); p6();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class P1Context extends ParserRuleContext {
		public P1Context p1() {
			return getRuleContext(P1Context.class,0);
		}
		public TerminalNode STAR() { return getToken(BasicParser.STAR, 0); }
		public TerminalNode MOD() { return getToken(BasicParser.MOD, 0); }
		public TerminalNode DIV() { return getToken(BasicParser.DIV, 0); }
		public ExprNoBinOpContext exprNoBinOp() {
			return getRuleContext(ExprNoBinOpContext.class,0);
		}
		public P1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitP1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final P1Context p1() throws RecognitionException {
		P1Context _localctx = new P1Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_p1);
		int _la;
		try {
			setState(248);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(243); exprNoBinOp();
				setState(244);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << DIV) | (1L << MOD))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(245); p1();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(247); exprNoBinOp();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class P2Context extends ParserRuleContext {
		public P1Context p1() {
			return getRuleContext(P1Context.class,0);
		}
		public P2Context p2() {
			return getRuleContext(P2Context.class,0);
		}
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public P2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitP2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final P2Context p2() throws RecognitionException {
		P2Context _localctx = new P2Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_p2);
		int _la;
		try {
			setState(255);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(250); p1();
				setState(251);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==PLUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(252); p2();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254); p1();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class P3Context extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(BasicParser.LESS, 0); }
		public P3Context p3() {
			return getRuleContext(P3Context.class,0);
		}
		public TerminalNode GREATEREQUAL() { return getToken(BasicParser.GREATEREQUAL, 0); }
		public P2Context p2() {
			return getRuleContext(P2Context.class,0);
		}
		public TerminalNode LESSEQUAL() { return getToken(BasicParser.LESSEQUAL, 0); }
		public TerminalNode GREATER() { return getToken(BasicParser.GREATER, 0); }
		public P3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitP3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final P3Context p3() throws RecognitionException {
		P3Context _localctx = new P3Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_p3);
		int _la;
		try {
			setState(262);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257); p2();
				setState(258);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATEREQUAL) | (1L << LESS) | (1L << LESSEQUAL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(259); p3();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(261); p2();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class P4Context extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(BasicParser.EQUAL, 0); }
		public P3Context p3() {
			return getRuleContext(P3Context.class,0);
		}
		public P4Context p4() {
			return getRuleContext(P4Context.class,0);
		}
		public TerminalNode NOTEQUAL() { return getToken(BasicParser.NOTEQUAL, 0); }
		public P4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p4; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitP4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final P4Context p4() throws RecognitionException {
		P4Context _localctx = new P4Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_p4);
		int _la;
		try {
			setState(269);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(264); p3();
				setState(265);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(266); p4();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(268); p3();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class P5Context extends ParserRuleContext {
		public P4Context p4() {
			return getRuleContext(P4Context.class,0);
		}
		public P5Context p5() {
			return getRuleContext(P5Context.class,0);
		}
		public TerminalNode AND() { return getToken(BasicParser.AND, 0); }
		public P5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p5; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitP5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final P5Context p5() throws RecognitionException {
		P5Context _localctx = new P5Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_p5);
		try {
			setState(276);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(271); p4();
				setState(272); match(AND);
				setState(273); p5();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(275); p4();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class P6Context extends ParserRuleContext {
		public P5Context p5() {
			return getRuleContext(P5Context.class,0);
		}
		public P6Context p6() {
			return getRuleContext(P6Context.class,0);
		}
		public TerminalNode OR() { return getToken(BasicParser.OR, 0); }
		public P6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p6; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitP6(this);
			else return visitor.visitChildren(this);
		}
	}

	public final P6Context p6() throws RecognitionException {
		P6Context _localctx = new P6Context(_ctx, getState());
		enterRule(_localctx, 44, RULE_p6);
		try {
			setState(283);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(278); p5();
				setState(279); match(OR);
				setState(280); p6();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(282); p5();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntliterContext extends ParserRuleContext {
		public TerminalNode DIGIT(int i) {
			return getToken(BasicParser.DIGIT, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(BasicParser.DIGIT); }
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public IntliterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intliter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIntliter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntliterContext intliter() throws RecognitionException {
		IntliterContext _localctx = new IntliterContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_intliter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			_la = _input.LA(1);
			if (_la==MINUS || _la==PLUS) {
				{
				setState(285);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==PLUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
			}

			setState(289); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(288); match(DIGIT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(291); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnopContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(BasicParser.NOT, 0); }
		public TerminalNode ORD() { return getToken(BasicParser.ORD, 0); }
		public TerminalNode LEN() { return getToken(BasicParser.LEN, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode CHR() { return getToken(BasicParser.CHR, 0); }
		public UnopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnopContext unop() throws RecognitionException {
		UnopContext _localctx = new UnopContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_unop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << NOT) | (1L << MINUS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayelemContext extends ParserRuleContext {
		public List<TerminalNode> RBRACKET() { return getTokens(BasicParser.RBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(BasicParser.LBRACKET, i);
		}
		public TerminalNode RBRACKET(int i) {
			return getToken(BasicParser.RBRACKET, i);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(BasicParser.LBRACKET); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayelemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayelem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayelem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayelemContext arrayelem() throws RecognitionException {
		ArrayelemContext _localctx = new ArrayelemContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_arrayelem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(295); match(IDENT);
			setState(300); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(296); match(LBRACKET);
					setState(297); expression();
					setState(298); match(RBRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(302); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolliterContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(BasicParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(BasicParser.TRUE, 0); }
		public BoolliterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolliter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBoolliter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolliterContext boolliter() throws RecognitionException {
		BoolliterContext _localctx = new BoolliterContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_boolliter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharliterContext extends ParserRuleContext {
		public TerminalNode EXCLUDECHAR() { return getToken(BasicParser.EXCLUDECHAR, 0); }
		public TerminalNode CHARCLOSE() { return getToken(BasicParser.CHARCLOSE, 0); }
		public TerminalNode CHARLITERAL() { return getToken(BasicParser.CHARLITERAL, 0); }
		public CharliterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charliter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitCharliter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharliterContext charliter() throws RecognitionException {
		CharliterContext _localctx = new CharliterContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_charliter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306); match(CHARLITERAL);
			setState(307); match(EXCLUDECHAR);
			setState(308); match(CHARCLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrliterContext extends ParserRuleContext {
		public List<TerminalNode> EXCLUDESTRING() { return getTokens(BasicParser.EXCLUDESTRING); }
		public TerminalNode STRINGLITERAL() { return getToken(BasicParser.STRINGLITERAL, 0); }
		public TerminalNode STRINGCLOSE() { return getToken(BasicParser.STRINGCLOSE, 0); }
		public TerminalNode EXCLUDESTRING(int i) {
			return getToken(BasicParser.EXCLUDESTRING, i);
		}
		public StrliterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strliter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStrliter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrliterContext strliter() throws RecognitionException {
		StrliterContext _localctx = new StrliterContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_strliter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310); match(STRINGLITERAL);
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXCLUDESTRING) {
				{
				{
				setState(311); match(EXCLUDESTRING);
				}
				}
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(317); match(STRINGCLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharacterContext extends ParserRuleContext {
		public TerminalNode ESC_SLASH() { return getToken(BasicParser.ESC_SLASH, 0); }
		public TerminalNode ESCAPE() { return getToken(BasicParser.ESCAPE, 0); }
		public TerminalNode STRINGLITERAL() { return getToken(BasicParser.STRINGLITERAL, 0); }
		public TerminalNode CHARLITERAL() { return getToken(BasicParser.CHARLITERAL, 0); }
		public CharacterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitCharacter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharacterContext character() throws RecognitionException {
		CharacterContext _localctx = new CharacterContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_character);
		int _la;
		try {
			setState(321);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				_la = _input.LA(1);
				if ( _la <= 0 || ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ESC_SLASH) | (1L << CHARLITERAL) | (1L << STRINGLITERAL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(320); match(ESCAPE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayliterContext extends ParserRuleContext {
		public TerminalNode RBRACKET() { return getToken(BasicParser.RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LBRACKET() { return getToken(BasicParser.LBRACKET, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArrayliterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayliter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayliter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayliterContext arrayliter() throws RecognitionException {
		ArrayliterContext _localctx = new ArrayliterContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_arrayliter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323); match(LBRACKET);
			setState(332);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIRLITERAL) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << DIGIT) | (1L << IDENT) | (1L << LPAREN) | (1L << NOT) | (1L << MINUS) | (1L << PLUS) | (1L << CHARLITERAL) | (1L << STRINGLITERAL))) != 0)) {
				{
				setState(324); expression();
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(325); match(COMMA);
					setState(326); expression();
					}
					}
					setState(331);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(334); match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4: return statement_sempred((StatementContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean statement_sempred(StatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3E\u0153\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\7\2C\n\2\f\2\16\2F\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3P\n\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0090\n\6\3\6\3\6\3\6\7"+
		"\6\u0095\n\6\f\6\16\6\u0098\13\6\3\7\3\7\3\7\5\7\u009d\n\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00ad\n\b\3\b\5\b\u00b0"+
		"\n\b\3\t\3\t\3\t\7\t\u00b5\n\t\f\t\16\t\u00b8\13\t\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\5\13\u00c0\n\13\3\f\3\f\3\r\3\r\5\r\u00c6\n\r\3\r\3\r\6\r\u00ca"+
		"\n\r\r\r\16\r\u00cb\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\5\17\u00d8\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\5\20\u00e8\n\20\3\21\3\21\5\21\u00ec\n\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\5\22\u00f4\n\22\3\23\3\23\3\23\3\23\3\23\5\23\u00fb"+
		"\n\23\3\24\3\24\3\24\3\24\3\24\5\24\u0102\n\24\3\25\3\25\3\25\3\25\3\25"+
		"\5\25\u0109\n\25\3\26\3\26\3\26\3\26\3\26\5\26\u0110\n\26\3\27\3\27\3"+
		"\27\3\27\3\27\5\27\u0117\n\27\3\30\3\30\3\30\3\30\3\30\5\30\u011e\n\30"+
		"\3\31\5\31\u0121\n\31\3\31\6\31\u0124\n\31\r\31\16\31\u0125\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\33\6\33\u012f\n\33\r\33\16\33\u0130\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\36\3\36\7\36\u013b\n\36\f\36\16\36\u013e\13\36\3"+
		"\36\3\36\3\37\3\37\5\37\u0144\n\37\3 \3 \3 \3 \7 \u014a\n \f \16 \u014d"+
		"\13 \5 \u014f\n \3 \3 \3 \2\3\n!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>\2\13\3\2\32\33\3\2\26\31\3\2\60\62\4\2//\63"+
		"\63\3\2\64\67\3\289\4\2\37!./\3\2\"#\3\2>@\u016c\2@\3\2\2\2\4K\3\2\2\2"+
		"\6V\3\2\2\2\b^\3\2\2\2\n\u008f\3\2\2\2\f\u009c\3\2\2\2\16\u00af\3\2\2"+
		"\2\20\u00b1\3\2\2\2\22\u00b9\3\2\2\2\24\u00bf\3\2\2\2\26\u00c1\3\2\2\2"+
		"\30\u00c5\3\2\2\2\32\u00cd\3\2\2\2\34\u00d7\3\2\2\2\36\u00e7\3\2\2\2 "+
		"\u00eb\3\2\2\2\"\u00f3\3\2\2\2$\u00fa\3\2\2\2&\u0101\3\2\2\2(\u0108\3"+
		"\2\2\2*\u010f\3\2\2\2,\u0116\3\2\2\2.\u011d\3\2\2\2\60\u0120\3\2\2\2\62"+
		"\u0127\3\2\2\2\64\u0129\3\2\2\2\66\u0132\3\2\2\28\u0134\3\2\2\2:\u0138"+
		"\3\2\2\2<\u0143\3\2\2\2>\u0145\3\2\2\2@D\7\3\2\2AC\5\4\3\2BA\3\2\2\2C"+
		"F\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2FD\3\2\2\2GH\5\n\6\2HI\7\4\2\2"+
		"IJ\7\2\2\3J\3\3\2\2\2KL\5\24\13\2LM\7\'\2\2MO\7(\2\2NP\5\6\4\2ON\3\2\2"+
		"\2OP\3\2\2\2PQ\3\2\2\2QR\7)\2\2RS\7\5\2\2ST\5\n\6\2TU\7\4\2\2U\5\3\2\2"+
		"\2V[\5\b\5\2WX\7,\2\2XZ\5\b\5\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2"+
		"\2\\\7\3\2\2\2][\3\2\2\2^_\5\24\13\2_`\7\'\2\2`\t\3\2\2\2ab\b\6\1\2b\u0090"+
		"\7\6\2\2cd\5\24\13\2de\7\'\2\2ef\7\7\2\2fg\5\16\b\2g\u0090\3\2\2\2hi\5"+
		"\f\7\2ij\7\7\2\2jk\5\16\b\2k\u0090\3\2\2\2lm\7\b\2\2m\u0090\5\f\7\2no"+
		"\7\t\2\2o\u0090\5 \21\2pq\7\n\2\2q\u0090\5 \21\2rs\7\13\2\2s\u0090\5 "+
		"\21\2tu\7\f\2\2u\u0090\5 \21\2vw\7\r\2\2w\u0090\5 \21\2xy\7\17\2\2yz\5"+
		" \21\2z{\7\20\2\2{|\5\n\6\2|}\7\21\2\2}~\5\n\6\2~\177\7\22\2\2\177\u0090"+
		"\3\2\2\2\u0080\u0081\7\23\2\2\u0081\u0082\5 \21\2\u0082\u0083\7\24\2\2"+
		"\u0083\u0084\5\n\6\2\u0084\u0085\7\25\2\2\u0085\u0090\3\2\2\2\u0086\u0087"+
		"\7\3\2\2\u0087\u0088\5\n\6\2\u0088\u0089\7\4\2\2\u0089\u0090\3\2\2\2\u008a"+
		"\u008b\7\24\2\2\u008b\u008c\5\n\6\2\u008c\u008d\7\23\2\2\u008d\u008e\5"+
		" \21\2\u008e\u0090\3\2\2\2\u008fa\3\2\2\2\u008fc\3\2\2\2\u008fh\3\2\2"+
		"\2\u008fl\3\2\2\2\u008fn\3\2\2\2\u008fp\3\2\2\2\u008fr\3\2\2\2\u008ft"+
		"\3\2\2\2\u008fv\3\2\2\2\u008fx\3\2\2\2\u008f\u0080\3\2\2\2\u008f\u0086"+
		"\3\2\2\2\u008f\u008a\3\2\2\2\u0090\u0096\3\2\2\2\u0091\u0092\f\4\2\2\u0092"+
		"\u0093\7-\2\2\u0093\u0095\5\n\6\5\u0094\u0091\3\2\2\2\u0095\u0098\3\2"+
		"\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\13\3\2\2\2\u0098\u0096"+
		"\3\2\2\2\u0099\u009d\7\'\2\2\u009a\u009d\5\64\33\2\u009b\u009d\5\22\n"+
		"\2\u009c\u0099\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\r"+
		"\3\2\2\2\u009e\u00b0\5 \21\2\u009f\u00b0\5> \2\u00a0\u00a1\7\35\2\2\u00a1"+
		"\u00a2\7(\2\2\u00a2\u00a3\5 \21\2\u00a3\u00a4\7,\2\2\u00a4\u00a5\5 \21"+
		"\2\u00a5\u00a6\7)\2\2\u00a6\u00b0\3\2\2\2\u00a7\u00b0\5\22\n\2\u00a8\u00a9"+
		"\7\16\2\2\u00a9\u00aa\7\'\2\2\u00aa\u00ac\7(\2\2\u00ab\u00ad\5\20\t\2"+
		"\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0"+
		"\7)\2\2\u00af\u009e\3\2\2\2\u00af\u009f\3\2\2\2\u00af\u00a0\3\2\2\2\u00af"+
		"\u00a7\3\2\2\2\u00af\u00a8\3\2\2\2\u00b0\17\3\2\2\2\u00b1\u00b6\5 \21"+
		"\2\u00b2\u00b3\7,\2\2\u00b3\u00b5\5 \21\2\u00b4\u00b2\3\2\2\2\u00b5\u00b8"+
		"\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\21\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b9\u00ba\t\2\2\2\u00ba\u00bb\5 \21\2\u00bb\23\3\2\2"+
		"\2\u00bc\u00c0\5\26\f\2\u00bd\u00c0\5\30\r\2\u00be\u00c0\5\32\16\2\u00bf"+
		"\u00bc\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0\25\3\2\2"+
		"\2\u00c1\u00c2\t\3\2\2\u00c2\27\3\2\2\2\u00c3\u00c6\5\26\f\2\u00c4\u00c6"+
		"\5\32\16\2\u00c5\u00c3\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2"+
		"\u00c7\u00c8\7*\2\2\u00c8\u00ca\7+\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb"+
		"\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\31\3\2\2\2\u00cd"+
		"\u00ce\7\34\2\2\u00ce\u00cf\7(\2\2\u00cf\u00d0\5\34\17\2\u00d0\u00d1\7"+
		",\2\2\u00d1\u00d2\5\34\17\2\u00d2\u00d3\7)\2\2\u00d3\33\3\2\2\2\u00d4"+
		"\u00d8\5\26\f\2\u00d5\u00d8\5\30\r\2\u00d6\u00d8\7\34\2\2\u00d7\u00d4"+
		"\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8\35\3\2\2\2\u00d9"+
		"\u00e8\5\60\31\2\u00da\u00e8\5\66\34\2\u00db\u00e8\58\35\2\u00dc\u00e8"+
		"\5:\36\2\u00dd\u00e8\7\36\2\2\u00de\u00e8\7\'\2\2\u00df\u00e8\5\64\33"+
		"\2\u00e0\u00e1\7(\2\2\u00e1\u00e2\5 \21\2\u00e2\u00e3\7)\2\2\u00e3\u00e8"+
		"\3\2\2\2\u00e4\u00e5\5\62\32\2\u00e5\u00e6\5 \21\2\u00e6\u00e8\3\2\2\2"+
		"\u00e7\u00d9\3\2\2\2\u00e7\u00da\3\2\2\2\u00e7\u00db\3\2\2\2\u00e7\u00dc"+
		"\3\2\2\2\u00e7\u00dd\3\2\2\2\u00e7\u00de\3\2\2\2\u00e7\u00df\3\2\2\2\u00e7"+
		"\u00e0\3\2\2\2\u00e7\u00e4\3\2\2\2\u00e8\37\3\2\2\2\u00e9\u00ec\5\36\20"+
		"\2\u00ea\u00ec\5\"\22\2\u00eb\u00e9\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec"+
		"!\3\2\2\2\u00ed\u00f4\5$\23\2\u00ee\u00f4\5&\24\2\u00ef\u00f4\5(\25\2"+
		"\u00f0\u00f4\5*\26\2\u00f1\u00f4\5,\27\2\u00f2\u00f4\5.\30\2\u00f3\u00ed"+
		"\3\2\2\2\u00f3\u00ee\3\2\2\2\u00f3\u00ef\3\2\2\2\u00f3\u00f0\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4#\3\2\2\2\u00f5\u00f6\5\36\20"+
		"\2\u00f6\u00f7\t\4\2\2\u00f7\u00f8\5$\23\2\u00f8\u00fb\3\2\2\2\u00f9\u00fb"+
		"\5\36\20\2\u00fa\u00f5\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb%\3\2\2\2\u00fc"+
		"\u00fd\5$\23\2\u00fd\u00fe\t\5\2\2\u00fe\u00ff\5&\24\2\u00ff\u0102\3\2"+
		"\2\2\u0100\u0102\5$\23\2\u0101\u00fc\3\2\2\2\u0101\u0100\3\2\2\2\u0102"+
		"\'\3\2\2\2\u0103\u0104\5&\24\2\u0104\u0105\t\6\2\2\u0105\u0106\5(\25\2"+
		"\u0106\u0109\3\2\2\2\u0107\u0109\5&\24\2\u0108\u0103\3\2\2\2\u0108\u0107"+
		"\3\2\2\2\u0109)\3\2\2\2\u010a\u010b\5(\25\2\u010b\u010c\t\7\2\2\u010c"+
		"\u010d\5*\26\2\u010d\u0110\3\2\2\2\u010e\u0110\5(\25\2\u010f\u010a\3\2"+
		"\2\2\u010f\u010e\3\2\2\2\u0110+\3\2\2\2\u0111\u0112\5*\26\2\u0112\u0113"+
		"\7:\2\2\u0113\u0114\5,\27\2\u0114\u0117\3\2\2\2\u0115\u0117\5*\26\2\u0116"+
		"\u0111\3\2\2\2\u0116\u0115\3\2\2\2\u0117-\3\2\2\2\u0118\u0119\5,\27\2"+
		"\u0119\u011a\7;\2\2\u011a\u011b\5.\30\2\u011b\u011e\3\2\2\2\u011c\u011e"+
		"\5,\27\2\u011d\u0118\3\2\2\2\u011d\u011c\3\2\2\2\u011e/\3\2\2\2\u011f"+
		"\u0121\t\5\2\2\u0120\u011f\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2"+
		"\2\2\u0122\u0124\7&\2\2\u0123\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\61\3\2\2\2\u0127\u0128\t\b\2"+
		"\2\u0128\63\3\2\2\2\u0129\u012e\7\'\2\2\u012a\u012b\7*\2\2\u012b\u012c"+
		"\5 \21\2\u012c\u012d\7+\2\2\u012d\u012f\3\2\2\2\u012e\u012a\3\2\2\2\u012f"+
		"\u0130\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\65\3\2\2"+
		"\2\u0132\u0133\t\t\2\2\u0133\67\3\2\2\2\u0134\u0135\7?\2\2\u0135\u0136"+
		"\7A\2\2\u0136\u0137\7B\2\2\u01379\3\2\2\2\u0138\u013c\7@\2\2\u0139\u013b"+
		"\7C\2\2\u013a\u0139\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c"+
		"\u013d\3\2\2\2\u013d\u013f\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0140\7D"+
		"\2\2\u0140;\3\2\2\2\u0141\u0144\n\n\2\2\u0142\u0144\7=\2\2\u0143\u0141"+
		"\3\2\2\2\u0143\u0142\3\2\2\2\u0144=\3\2\2\2\u0145\u014e\7*\2\2\u0146\u014b"+
		"\5 \21\2\u0147\u0148\7,\2\2\u0148\u014a\5 \21\2\u0149\u0147\3\2\2\2\u014a"+
		"\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014f\3\2"+
		"\2\2\u014d\u014b\3\2\2\2\u014e\u0146\3\2\2\2\u014e\u014f\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0151\7+\2\2\u0151?\3\2\2\2\37DO[\u008f\u0096\u009c"+
		"\u00ac\u00af\u00b6\u00bf\u00c5\u00cb\u00d7\u00e7\u00eb\u00f3\u00fa\u0101"+
		"\u0108\u010f\u0116\u011d\u0120\u0125\u0130\u013c\u0143\u014b\u014e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}