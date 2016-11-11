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
		RPAREN=39, STRINGLITERAL=62, READ=6, NOTEQUAL=55, CHARCLOSE=64, AND=56, 
		END=2, THEN=14, LESS=52, EXIT=9, PLUS=49, ORD=30, CALL=12, EXCLUDESTRING=65, 
		PRINTLN=11, SEMI=43, CHAR=22, BEGIN=1, ASSIGN=5, FREE=7, INT=20, COMMENT=35, 
		RETURN=8, CHARLITERAL=61, SKIP=4, WS=58, COMMA=42, EOL=34, MOD=48, OR=57, 
		STRINGCLOSE=66, EQUAL=54, ENDIF=16, GREATER=50, LBRACKET=40, RBRACKET=41, 
		FIRST=24, DIGIT=36, ESCAPE=59, DIV=47, OTHER=67, LEN=29, IDENT=37, STAR=46, 
		BOOL=21, ESC_SLASH=60, GREATEREQUAL=51, STRING=23, WHILE=17, SECOND=25, 
		FALSE=33, PAIR=26, FACTORIAL=44;
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
		RULE_p5 = 21, RULE_p6 = 22, RULE_unop = 23, RULE_arrayelem = 24, RULE_intliter = 25, 
		RULE_intsign = 26, RULE_boolliter = 27, RULE_charliter = 28, RULE_strliter = 29, 
		RULE_character = 30, RULE_arrayliter = 31;
	public static final String[] ruleNames = {
		"program", "function", "paramlist", "param", "statement", "assignlhs", 
		"assignrhs", "arglist", "pairelem", "type", "basetype", "arraytype", "pairtype", 
		"pairelemtype", "exprNoBinOp", "expression", "binOp", "p1", "p2", "p3", 
		"p4", "p5", "p6", "unop", "arrayelem", "intliter", "intsign", "boolliter", 
		"charliter", "strliter", "character", "arrayliter"
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
			setState(64); match(BEGIN);
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(65); function();
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(71); statement(0);
			setState(72); match(END);
			setState(73); match(EOF);
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
			setState(75); type();
			setState(76); match(IDENT);
			setState(77); match(LPAREN);
			setState(79);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << PAIR))) != 0)) {
				{
				setState(78); paramlist();
				}
			}

			setState(81); match(RPAREN);
			setState(82); match(IS);
			setState(83); statement(0);
			setState(84); match(END);
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
			setState(86); param();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(87); match(COMMA);
				setState(88); param();
				}
				}
				setState(93);
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
			setState(94); type();
			setState(95); match(IDENT);
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
			setState(138);
			switch (_input.LA(1)) {
			case SKIP:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(98); match(SKIP);
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
				setState(99); type();
				setState(100); match(IDENT);
				setState(101); match(ASSIGN);
				setState(102); assignrhs();
				}
				break;
			case FIRST:
			case SECOND:
			case IDENT:
				{
				_localctx = new AssignmentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104); assignlhs();
				setState(105); match(ASSIGN);
				setState(106); assignrhs();
				}
				break;
			case READ:
				{
				_localctx = new ReadContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108); match(READ);
				setState(109); assignlhs();
				}
				break;
			case FREE:
				{
				_localctx = new FreeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110); match(FREE);
				setState(111); expression();
				}
				break;
			case RETURN:
				{
				_localctx = new ReturnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112); match(RETURN);
				setState(113); expression();
				}
				break;
			case EXIT:
				{
				_localctx = new ExitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(114); match(EXIT);
				setState(115); expression();
				}
				break;
			case PRINT:
				{
				_localctx = new PrintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116); match(PRINT);
				setState(117); expression();
				}
				break;
			case PRINTLN:
				{
				_localctx = new PrintlnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118); match(PRINTLN);
				setState(119); expression();
				}
				break;
			case IF:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120); match(IF);
				setState(121); expression();
				setState(122); match(THEN);
				setState(123); statement(0);
				setState(124); match(ELSE);
				setState(125); statement(0);
				setState(126); match(ENDIF);
				}
				break;
			case WHILE:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128); match(WHILE);
				setState(129); expression();
				setState(130); match(DO);
				setState(131); statement(0);
				setState(132); match(DONE);
				}
				break;
			case BEGIN:
				{
				_localctx = new BeginContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134); match(BEGIN);
				setState(135); statement(0);
				setState(136); match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(145);
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
					setState(140);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(141); match(SEMI);
					setState(142); statement(2);
					}
					} 
				}
				setState(147);
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
			setState(151);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148); match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149); arrayelem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(150); pairelem();
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
			setState(170);
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
			case FACTORIAL:
			case MINUS:
			case PLUS:
			case CHARLITERAL:
			case STRINGLITERAL:
				_localctx = new ExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(153); expression();
				}
				break;
			case LBRACKET:
				_localctx = new ArraylitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(154); arrayliter();
				}
				break;
			case NEWPAIR:
				_localctx = new NewpairContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(155); match(NEWPAIR);
				setState(156); match(LPAREN);
				setState(157); expression();
				setState(158); match(COMMA);
				setState(159); expression();
				setState(160); match(RPAREN);
				}
				break;
			case FIRST:
			case SECOND:
				_localctx = new PairelementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(162); pairelem();
				}
				break;
			case CALL:
				_localctx = new FunctioncallContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(163); match(CALL);
				setState(164); match(IDENT);
				setState(165); match(LPAREN);
				setState(167);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIRLITERAL) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << DIGIT) | (1L << IDENT) | (1L << LPAREN) | (1L << FACTORIAL) | (1L << MINUS) | (1L << PLUS) | (1L << CHARLITERAL) | (1L << STRINGLITERAL))) != 0)) {
					{
					setState(166); arglist();
					}
				}

				setState(169); match(RPAREN);
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
			setState(172); expression();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(173); match(COMMA);
				setState(174); expression();
				}
				}
				setState(179);
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
			setState(180);
			_la = _input.LA(1);
			if ( !(_la==FIRST || _la==SECOND) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(181); expression();
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
			setState(186);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(183); basetype();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184); arraytype();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(185); pairtype();
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
			setState(188);
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
			setState(192);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(190); basetype();
				}
				break;
			case PAIR:
				{
				setState(191); pairtype();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(196); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(194); match(LBRACKET);
				setState(195); match(RBRACKET);
				}
				}
				setState(198); 
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
			setState(200); match(PAIR);
			setState(201); match(LPAREN);
			setState(202); pairelemtype();
			setState(203); match(COMMA);
			setState(204); pairelemtype();
			setState(205); match(RPAREN);
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
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207); basetype();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(208); arraytype();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(209); match(PAIR);
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
			setState(226);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); intliter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213); boolliter();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214); charliter();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(215); strliter();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(216); match(PAIRLITERAL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(217); match(IDENT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(218); arrayelem();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(219); unop();
				setState(220); expression();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(222); match(LPAREN);
				setState(223); expression();
				setState(224); match(RPAREN);
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
			setState(230);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228); exprNoBinOp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229); binOp();
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
			setState(238);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(232); p1();
				}
				break;
			case 2:
				{
				setState(233); p2();
				}
				break;
			case 3:
				{
				setState(234); p3();
				}
				break;
			case 4:
				{
				setState(235); p4();
				}
				break;
			case 5:
				{
				setState(236); p5();
				}
				break;
			case 6:
				{
				setState(237); p6();
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
			setState(245);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(240); exprNoBinOp();
				setState(241);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << DIV) | (1L << MOD))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(242); p1();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(244); exprNoBinOp();
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
			setState(252);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(247); p1();
				setState(248);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==PLUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(249); p2();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(251); p1();
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
			setState(259);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(254); p2();
				setState(255);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATEREQUAL) | (1L << LESS) | (1L << LESSEQUAL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(256); p3();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(258); p2();
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
			setState(266);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(261); p3();
				setState(262);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(263); p4();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(265); p3();
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
			setState(273);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(268); p4();
				setState(269); match(AND);
				setState(270); p5();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(272); p4();
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
			setState(280);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(275); p5();
				setState(276); match(OR);
				setState(277); p6();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(279); p5();
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

	public static class UnopContext extends ParserRuleContext {
		public TerminalNode ORD() { return getToken(BasicParser.ORD, 0); }
		public TerminalNode LEN() { return getToken(BasicParser.LEN, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode FACTORIAL() { return getToken(BasicParser.FACTORIAL, 0); }
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
		enterRule(_localctx, 46, RULE_unop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << FACTORIAL) | (1L << MINUS))) != 0)) ) {
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
		enterRule(_localctx, 48, RULE_arrayelem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(284); match(IDENT);
			setState(289); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(285); match(LBRACKET);
					setState(286); expression();
					setState(287); match(RBRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(291); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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

	public static class IntliterContext extends ParserRuleContext {
		public TerminalNode DIGIT(int i) {
			return getToken(BasicParser.DIGIT, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(BasicParser.DIGIT); }
		public IntsignContext intsign() {
			return getRuleContext(IntsignContext.class,0);
		}
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
		enterRule(_localctx, 50, RULE_intliter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			_la = _input.LA(1);
			if (_la==MINUS || _la==PLUS) {
				{
				setState(293); intsign();
				}
			}

			setState(297); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(296); match(DIGIT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(299); 
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

	public static class IntsignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public IntsignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intsign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIntsign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntsignContext intsign() throws RecognitionException {
		IntsignContext _localctx = new IntsignContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_intsign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			_la = _input.LA(1);
			if ( !(_la==MINUS || _la==PLUS) ) {
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
		enterRule(_localctx, 54, RULE_boolliter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
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
		enterRule(_localctx, 56, RULE_charliter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305); match(CHARLITERAL);
			setState(306); match(EXCLUDECHAR);
			setState(307); match(CHARCLOSE);
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
		enterRule(_localctx, 58, RULE_strliter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309); match(STRINGLITERAL);
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXCLUDESTRING) {
				{
				{
				setState(310); match(EXCLUDESTRING);
				}
				}
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(316); match(STRINGCLOSE);
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
		enterRule(_localctx, 60, RULE_character);
		int _la;
		try {
			setState(320);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
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
				setState(319); match(ESCAPE);
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
		enterRule(_localctx, 62, RULE_arrayliter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322); match(LBRACKET);
			setState(331);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIRLITERAL) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << TRUE) | (1L << FALSE) | (1L << DIGIT) | (1L << IDENT) | (1L << LPAREN) | (1L << FACTORIAL) | (1L << MINUS) | (1L << PLUS) | (1L << CHARLITERAL) | (1L << STRINGLITERAL))) != 0)) {
				{
				setState(323); expression();
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(324); match(COMMA);
					setState(325); expression();
					}
					}
					setState(330);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(333); match(RBRACKET);
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
		case 0: return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3E\u0152\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\7\2E\n\2\f\2\16\2H\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\5"+
		"\3R\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4\\\n\4\f\4\16\4_\13\4\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u008d\n\6\3\6\3\6\3\6\7\6\u0092\n\6"+
		"\f\6\16\6\u0095\13\6\3\7\3\7\3\7\5\7\u009a\n\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00aa\n\b\3\b\5\b\u00ad\n\b\3\t\3"+
		"\t\3\t\7\t\u00b2\n\t\f\t\16\t\u00b5\13\t\3\n\3\n\3\n\3\13\3\13\3\13\5"+
		"\13\u00bd\n\13\3\f\3\f\3\r\3\r\5\r\u00c3\n\r\3\r\3\r\6\r\u00c7\n\r\r\r"+
		"\16\r\u00c8\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\5\17\u00d5"+
		"\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00e5\n\20\3\21\3\21\5\21\u00e9\n\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\5\22\u00f1\n\22\3\23\3\23\3\23\3\23\3\23\5\23\u00f8\n\23\3\24"+
		"\3\24\3\24\3\24\3\24\5\24\u00ff\n\24\3\25\3\25\3\25\3\25\3\25\5\25\u0106"+
		"\n\25\3\26\3\26\3\26\3\26\3\26\5\26\u010d\n\26\3\27\3\27\3\27\3\27\3\27"+
		"\5\27\u0114\n\27\3\30\3\30\3\30\3\30\3\30\5\30\u011b\n\30\3\31\3\31\3"+
		"\32\3\32\3\32\3\32\3\32\6\32\u0124\n\32\r\32\16\32\u0125\3\33\5\33\u0129"+
		"\n\33\3\33\6\33\u012c\n\33\r\33\16\33\u012d\3\34\3\34\3\35\3\35\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\7\37\u013a\n\37\f\37\16\37\u013d\13\37\3\37\3"+
		"\37\3 \3 \5 \u0143\n \3!\3!\3!\3!\7!\u0149\n!\f!\16!\u014c\13!\5!\u014e"+
		"\n!\3!\3!\3!\2\3\n\"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@\2\13\3\2\32\33\3\2\26\31\3\2\60\62\4\2//\63\63\3\2\64\67"+
		"\3\289\4\2\37!./\3\2\"#\3\2>@\u0169\2B\3\2\2\2\4M\3\2\2\2\6X\3\2\2\2\b"+
		"`\3\2\2\2\n\u008c\3\2\2\2\f\u0099\3\2\2\2\16\u00ac\3\2\2\2\20\u00ae\3"+
		"\2\2\2\22\u00b6\3\2\2\2\24\u00bc\3\2\2\2\26\u00be\3\2\2\2\30\u00c2\3\2"+
		"\2\2\32\u00ca\3\2\2\2\34\u00d4\3\2\2\2\36\u00e4\3\2\2\2 \u00e8\3\2\2\2"+
		"\"\u00f0\3\2\2\2$\u00f7\3\2\2\2&\u00fe\3\2\2\2(\u0105\3\2\2\2*\u010c\3"+
		"\2\2\2,\u0113\3\2\2\2.\u011a\3\2\2\2\60\u011c\3\2\2\2\62\u011e\3\2\2\2"+
		"\64\u0128\3\2\2\2\66\u012f\3\2\2\28\u0131\3\2\2\2:\u0133\3\2\2\2<\u0137"+
		"\3\2\2\2>\u0142\3\2\2\2@\u0144\3\2\2\2BF\7\3\2\2CE\5\4\3\2DC\3\2\2\2E"+
		"H\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\5\n\6\2JK\7\4\2\2"+
		"KL\7\2\2\3L\3\3\2\2\2MN\5\24\13\2NO\7\'\2\2OQ\7(\2\2PR\5\6\4\2QP\3\2\2"+
		"\2QR\3\2\2\2RS\3\2\2\2ST\7)\2\2TU\7\5\2\2UV\5\n\6\2VW\7\4\2\2W\5\3\2\2"+
		"\2X]\5\b\5\2YZ\7,\2\2Z\\\5\b\5\2[Y\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2"+
		"\2\2^\7\3\2\2\2_]\3\2\2\2`a\5\24\13\2ab\7\'\2\2b\t\3\2\2\2cd\b\6\1\2d"+
		"\u008d\7\6\2\2ef\5\24\13\2fg\7\'\2\2gh\7\7\2\2hi\5\16\b\2i\u008d\3\2\2"+
		"\2jk\5\f\7\2kl\7\7\2\2lm\5\16\b\2m\u008d\3\2\2\2no\7\b\2\2o\u008d\5\f"+
		"\7\2pq\7\t\2\2q\u008d\5 \21\2rs\7\n\2\2s\u008d\5 \21\2tu\7\13\2\2u\u008d"+
		"\5 \21\2vw\7\f\2\2w\u008d\5 \21\2xy\7\r\2\2y\u008d\5 \21\2z{\7\17\2\2"+
		"{|\5 \21\2|}\7\20\2\2}~\5\n\6\2~\177\7\21\2\2\177\u0080\5\n\6\2\u0080"+
		"\u0081\7\22\2\2\u0081\u008d\3\2\2\2\u0082\u0083\7\23\2\2\u0083\u0084\5"+
		" \21\2\u0084\u0085\7\24\2\2\u0085\u0086\5\n\6\2\u0086\u0087\7\25\2\2\u0087"+
		"\u008d\3\2\2\2\u0088\u0089\7\3\2\2\u0089\u008a\5\n\6\2\u008a\u008b\7\4"+
		"\2\2\u008b\u008d\3\2\2\2\u008cc\3\2\2\2\u008ce\3\2\2\2\u008cj\3\2\2\2"+
		"\u008cn\3\2\2\2\u008cp\3\2\2\2\u008cr\3\2\2\2\u008ct\3\2\2\2\u008cv\3"+
		"\2\2\2\u008cx\3\2\2\2\u008cz\3\2\2\2\u008c\u0082\3\2\2\2\u008c\u0088\3"+
		"\2\2\2\u008d\u0093\3\2\2\2\u008e\u008f\f\3\2\2\u008f\u0090\7-\2\2\u0090"+
		"\u0092\5\n\6\4\u0091\u008e\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\13\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u009a"+
		"\7\'\2\2\u0097\u009a\5\62\32\2\u0098\u009a\5\22\n\2\u0099\u0096\3\2\2"+
		"\2\u0099\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\r\3\2\2\2\u009b\u00ad"+
		"\5 \21\2\u009c\u00ad\5@!\2\u009d\u009e\7\35\2\2\u009e\u009f\7(\2\2\u009f"+
		"\u00a0\5 \21\2\u00a0\u00a1\7,\2\2\u00a1\u00a2\5 \21\2\u00a2\u00a3\7)\2"+
		"\2\u00a3\u00ad\3\2\2\2\u00a4\u00ad\5\22\n\2\u00a5\u00a6\7\16\2\2\u00a6"+
		"\u00a7\7\'\2\2\u00a7\u00a9\7(\2\2\u00a8\u00aa\5\20\t\2\u00a9\u00a8\3\2"+
		"\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\7)\2\2\u00ac"+
		"\u009b\3\2\2\2\u00ac\u009c\3\2\2\2\u00ac\u009d\3\2\2\2\u00ac\u00a4\3\2"+
		"\2\2\u00ac\u00a5\3\2\2\2\u00ad\17\3\2\2\2\u00ae\u00b3\5 \21\2\u00af\u00b0"+
		"\7,\2\2\u00b0\u00b2\5 \21\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3"+
		"\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\21\3\2\2\2\u00b5\u00b3\3\2\2"+
		"\2\u00b6\u00b7\t\2\2\2\u00b7\u00b8\5 \21\2\u00b8\23\3\2\2\2\u00b9\u00bd"+
		"\5\26\f\2\u00ba\u00bd\5\30\r\2\u00bb\u00bd\5\32\16\2\u00bc\u00b9\3\2\2"+
		"\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\25\3\2\2\2\u00be\u00bf"+
		"\t\3\2\2\u00bf\27\3\2\2\2\u00c0\u00c3\5\26\f\2\u00c1\u00c3\5\32\16\2\u00c2"+
		"\u00c0\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c5\7*"+
		"\2\2\u00c5\u00c7\7+\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\31\3\2\2\2\u00ca\u00cb\7\34\2"+
		"\2\u00cb\u00cc\7(\2\2\u00cc\u00cd\5\34\17\2\u00cd\u00ce\7,\2\2\u00ce\u00cf"+
		"\5\34\17\2\u00cf\u00d0\7)\2\2\u00d0\33\3\2\2\2\u00d1\u00d5\5\26\f\2\u00d2"+
		"\u00d5\5\30\r\2\u00d3\u00d5\7\34\2\2\u00d4\u00d1\3\2\2\2\u00d4\u00d2\3"+
		"\2\2\2\u00d4\u00d3\3\2\2\2\u00d5\35\3\2\2\2\u00d6\u00e5\5\64\33\2\u00d7"+
		"\u00e5\58\35\2\u00d8\u00e5\5:\36\2\u00d9\u00e5\5<\37\2\u00da\u00e5\7\36"+
		"\2\2\u00db\u00e5\7\'\2\2\u00dc\u00e5\5\62\32\2\u00dd\u00de\5\60\31\2\u00de"+
		"\u00df\5 \21\2\u00df\u00e5\3\2\2\2\u00e0\u00e1\7(\2\2\u00e1\u00e2\5 \21"+
		"\2\u00e2\u00e3\7)\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00d6\3\2\2\2\u00e4\u00d7"+
		"\3\2\2\2\u00e4\u00d8\3\2\2\2\u00e4\u00d9\3\2\2\2\u00e4\u00da\3\2\2\2\u00e4"+
		"\u00db\3\2\2\2\u00e4\u00dc\3\2\2\2\u00e4\u00dd\3\2\2\2\u00e4\u00e0\3\2"+
		"\2\2\u00e5\37\3\2\2\2\u00e6\u00e9\5\36\20\2\u00e7\u00e9\5\"\22\2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9!\3\2\2\2\u00ea\u00f1\5$\23\2"+
		"\u00eb\u00f1\5&\24\2\u00ec\u00f1\5(\25\2\u00ed\u00f1\5*\26\2\u00ee\u00f1"+
		"\5,\27\2\u00ef\u00f1\5.\30\2\u00f0\u00ea\3\2\2\2\u00f0\u00eb\3\2\2\2\u00f0"+
		"\u00ec\3\2\2\2\u00f0\u00ed\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00ef\3\2"+
		"\2\2\u00f1#\3\2\2\2\u00f2\u00f3\5\36\20\2\u00f3\u00f4\t\4\2\2\u00f4\u00f5"+
		"\5$\23\2\u00f5\u00f8\3\2\2\2\u00f6\u00f8\5\36\20\2\u00f7\u00f2\3\2\2\2"+
		"\u00f7\u00f6\3\2\2\2\u00f8%\3\2\2\2\u00f9\u00fa\5$\23\2\u00fa\u00fb\t"+
		"\5\2\2\u00fb\u00fc\5&\24\2\u00fc\u00ff\3\2\2\2\u00fd\u00ff\5$\23\2\u00fe"+
		"\u00f9\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff\'\3\2\2\2\u0100\u0101\5&\24\2"+
		"\u0101\u0102\t\6\2\2\u0102\u0103\5(\25\2\u0103\u0106\3\2\2\2\u0104\u0106"+
		"\5&\24\2\u0105\u0100\3\2\2\2\u0105\u0104\3\2\2\2\u0106)\3\2\2\2\u0107"+
		"\u0108\5(\25\2\u0108\u0109\t\7\2\2\u0109\u010a\5*\26\2\u010a\u010d\3\2"+
		"\2\2\u010b\u010d\5(\25\2\u010c\u0107\3\2\2\2\u010c\u010b\3\2\2\2\u010d"+
		"+\3\2\2\2\u010e\u010f\5*\26\2\u010f\u0110\7:\2\2\u0110\u0111\5,\27\2\u0111"+
		"\u0114\3\2\2\2\u0112\u0114\5*\26\2\u0113\u010e\3\2\2\2\u0113\u0112\3\2"+
		"\2\2\u0114-\3\2\2\2\u0115\u0116\5,\27\2\u0116\u0117\7;\2\2\u0117\u0118"+
		"\5.\30\2\u0118\u011b\3\2\2\2\u0119\u011b\5,\27\2\u011a\u0115\3\2\2\2\u011a"+
		"\u0119\3\2\2\2\u011b/\3\2\2\2\u011c\u011d\t\b\2\2\u011d\61\3\2\2\2\u011e"+
		"\u0123\7\'\2\2\u011f\u0120\7*\2\2\u0120\u0121\5 \21\2\u0121\u0122\7+\2"+
		"\2\u0122\u0124\3\2\2\2\u0123\u011f\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0123"+
		"\3\2\2\2\u0125\u0126\3\2\2\2\u0126\63\3\2\2\2\u0127\u0129\5\66\34\2\u0128"+
		"\u0127\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012b\3\2\2\2\u012a\u012c\7&"+
		"\2\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012b\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\65\3\2\2\2\u012f\u0130\t\5\2\2\u0130\67\3\2\2\2\u0131"+
		"\u0132\t\t\2\2\u01329\3\2\2\2\u0133\u0134\7?\2\2\u0134\u0135\7A\2\2\u0135"+
		"\u0136\7B\2\2\u0136;\3\2\2\2\u0137\u013b\7@\2\2\u0138\u013a\7C\2\2\u0139"+
		"\u0138\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2"+
		"\2\2\u013c\u013e\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u013f\7D\2\2\u013f"+
		"=\3\2\2\2\u0140\u0143\n\n\2\2\u0141\u0143\7=\2\2\u0142\u0140\3\2\2\2\u0142"+
		"\u0141\3\2\2\2\u0143?\3\2\2\2\u0144\u014d\7*\2\2\u0145\u014a\5 \21\2\u0146"+
		"\u0147\7,\2\2\u0147\u0149\5 \21\2\u0148\u0146\3\2\2\2\u0149\u014c\3\2"+
		"\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014e\3\2\2\2\u014c"+
		"\u014a\3\2\2\2\u014d\u0145\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\3\2"+
		"\2\2\u014f\u0150\7+\2\2\u0150A\3\2\2\2\37FQ]\u008c\u0093\u0099\u00a9\u00ac"+
		"\u00b3\u00bc\u00c2\u00c8\u00d4\u00e4\u00e8\u00f0\u00f7\u00fe\u0105\u010c"+
		"\u0113\u011a\u0125\u0128\u012d\u013b\u0142\u014a\u014d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}