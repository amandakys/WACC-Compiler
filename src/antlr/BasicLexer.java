// Generated from ./BasicLexer.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, END=2, IS=3, SKIP=4, ASSIGN=5, READ=6, FREE=7, RETURN=8, EXIT=9, 
		PRINT=10, PRINTLN=11, CALL=12, IF=13, THEN=14, ELSE=15, ENDIF=16, WHILE=17, 
		FOR=18, DO=19, DONE=20, INT=21, BOOL=22, CHAR=23, STRING=24, FIRST=25, 
		SECOND=26, PAIR=27, NEWPAIR=28, PAIRLITERAL=29, LEN=30, ORD=31, CHR=32, 
		TRUE=33, FALSE=34, EOL=35, COMMENT=36, DIGIT=37, IDENT=38, LPAREN=39, 
		RPAREN=40, LBRACKET=41, RBRACKET=42, COMMA=43, SEMI=44, NOT=45, MINUS=46, 
		STAR=47, DIV=48, MOD=49, PLUS=50, GREATER=51, GREATEREQUAL=52, LESS=53, 
		LESSEQUAL=54, EQUAL=55, NOTEQUAL=56, AND=57, OR=58, WS=59, ESCAPE=60, 
		ESC_SLASH=61, CHARLITERAL=62, STRINGLITERAL=63, EXCLUDECHAR=64, CHARCLOSE=65, 
		EXCLUDESTRING=66, STRINGCLOSE=67, OTHER=68;
	public static final int CHARMODE = 1;
	public static final int STRINGMODE = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "CHARMODE", "STRINGMODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "':'", "';'", 
		"'<'", "'='", "'>'", "'?'", "'@'", "'A'", "'B'", "'C'", "'D'"
	};
	public static final String[] ruleNames = {
		"BEGIN", "END", "IS", "SKIP", "ASSIGN", "READ", "FREE", "RETURN", "EXIT", 
		"PRINT", "PRINTLN", "CALL", "IF", "THEN", "ELSE", "ENDIF", "WHILE", "FOR", 
		"DO", "DONE", "INT", "BOOL", "CHAR", "STRING", "FIRST", "SECOND", "PAIR", 
		"NEWPAIR", "PAIRLITERAL", "LEN", "ORD", "CHR", "TRUE", "FALSE", "EOL", 
		"COMMENT", "DIGIT", "IDENT", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", 
		"COMMA", "SEMI", "NOT", "MINUS", "STAR", "DIV", "MOD", "PLUS", "GREATER", 
		"GREATEREQUAL", "LESS", "LESSEQUAL", "EQUAL", "NOTEQUAL", "AND", "OR", 
		"WS", "ESCAPECHAR", "ESCAPE", "ESC_SLASH", "CHARLITERAL", "STRINGLITERAL", 
		"EXCLUDECHAR", "CHARCLOSE", "EXCLUDESTRING", "STRINGCLOSE", "OTHER"
	};


	public BasicLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BasicLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2F\u01a3\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
		"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30"+
		"\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37"+
		"\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t"+
		"*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63"+
		"\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t"+
		"<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3"+
		"\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\""+
		"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\7%\u013b\n%\f%\16%\u013e"+
		"\13%\3%\3%\3%\3%\3&\3&\3\'\5\'\u0147\n\'\3\'\7\'\u014a\n\'\f\'\16\'\u014d"+
		"\13\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3"+
		"\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\67\3\67\3"+
		"\67\38\38\38\39\39\39\3:\3:\3:\3;\3;\3;\3<\3<\3<\3<\3=\3=\3=\3>\3>\3?"+
		"\3?\3@\3@\3@\3@\3A\3A\3A\3A\3B\3B\5B\u0192\nB\3C\3C\3C\3C\3D\3D\6D\u019a"+
		"\nD\rD\16D\u019b\3E\3E\3E\3E\3F\3F\3\u013c\2G\5\3\7\4\t\5\13\6\r\7\17"+
		"\b\21\t\23\n\25\13\27\f\31\r\33\16\35\17\37\20!\21#\22%\23\'\24)\25+\26"+
		"-\27/\30\61\31\63\32\65\33\67\349\35;\36=\37? A!C\"E#G$I%K&M\'O(Q)S*U"+
		"+W,Y-[.]/_\60a\61c\62e\63g\64i\65k\66m\67o8q9s:u;w<y={\2}>\177?\u0081"+
		"@\u0083A\u0085B\u0087C\u0089D\u008bE\u008dF\5\2\3\4\n\4\2\f\f\17\17\3"+
		"\2\62;\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\13\2$$))\62\62^^d"+
		"dhhppttvv\7\2\f\f\17\17$$))^^\5\2$$))^^\u01a4\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2"+
		"O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3"+
		"\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2"+
		"\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2"+
		"u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2"+
		"\2\2\u0083\3\2\2\2\3\u0085\3\2\2\2\3\u0087\3\2\2\2\4\u0089\3\2\2\2\4\u008b"+
		"\3\2\2\2\4\u008d\3\2\2\2\5\u008f\3\2\2\2\7\u0095\3\2\2\2\t\u0099\3\2\2"+
		"\2\13\u009c\3\2\2\2\r\u00a1\3\2\2\2\17\u00a3\3\2\2\2\21\u00a8\3\2\2\2"+
		"\23\u00ad\3\2\2\2\25\u00b4\3\2\2\2\27\u00b9\3\2\2\2\31\u00bf\3\2\2\2\33"+
		"\u00c7\3\2\2\2\35\u00cc\3\2\2\2\37\u00cf\3\2\2\2!\u00d4\3\2\2\2#\u00d9"+
		"\3\2\2\2%\u00dc\3\2\2\2\'\u00e2\3\2\2\2)\u00e6\3\2\2\2+\u00e9\3\2\2\2"+
		"-\u00ee\3\2\2\2/\u00f2\3\2\2\2\61\u00f7\3\2\2\2\63\u00fc\3\2\2\2\65\u0103"+
		"\3\2\2\2\67\u0107\3\2\2\29\u010b\3\2\2\2;\u0110\3\2\2\2=\u0118\3\2\2\2"+
		"?\u011d\3\2\2\2A\u0121\3\2\2\2C\u0125\3\2\2\2E\u0129\3\2\2\2G\u012e\3"+
		"\2\2\2I\u0134\3\2\2\2K\u0138\3\2\2\2M\u0143\3\2\2\2O\u0146\3\2\2\2Q\u014e"+
		"\3\2\2\2S\u0150\3\2\2\2U\u0152\3\2\2\2W\u0154\3\2\2\2Y\u0156\3\2\2\2["+
		"\u0158\3\2\2\2]\u015a\3\2\2\2_\u015c\3\2\2\2a\u015e\3\2\2\2c\u0160\3\2"+
		"\2\2e\u0162\3\2\2\2g\u0164\3\2\2\2i\u0166\3\2\2\2k\u0168\3\2\2\2m\u016b"+
		"\3\2\2\2o\u016d\3\2\2\2q\u0170\3\2\2\2s\u0173\3\2\2\2u\u0176\3\2\2\2w"+
		"\u0179\3\2\2\2y\u017c\3\2\2\2{\u0180\3\2\2\2}\u0183\3\2\2\2\177\u0185"+
		"\3\2\2\2\u0081\u0187\3\2\2\2\u0083\u018b\3\2\2\2\u0085\u0191\3\2\2\2\u0087"+
		"\u0193\3\2\2\2\u0089\u0199\3\2\2\2\u008b\u019d\3\2\2\2\u008d\u01a1\3\2"+
		"\2\2\u008f\u0090\7d\2\2\u0090\u0091\7g\2\2\u0091\u0092\7i\2\2\u0092\u0093"+
		"\7k\2\2\u0093\u0094\7p\2\2\u0094\6\3\2\2\2\u0095\u0096\7g\2\2\u0096\u0097"+
		"\7p\2\2\u0097\u0098\7f\2\2\u0098\b\3\2\2\2\u0099\u009a\7k\2\2\u009a\u009b"+
		"\7u\2\2\u009b\n\3\2\2\2\u009c\u009d\7u\2\2\u009d\u009e\7m\2\2\u009e\u009f"+
		"\7k\2\2\u009f\u00a0\7r\2\2\u00a0\f\3\2\2\2\u00a1\u00a2\7?\2\2\u00a2\16"+
		"\3\2\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7c\2\2\u00a6"+
		"\u00a7\7f\2\2\u00a7\20\3\2\2\2\u00a8\u00a9\7h\2\2\u00a9\u00aa\7t\2\2\u00aa"+
		"\u00ab\7g\2\2\u00ab\u00ac\7g\2\2\u00ac\22\3\2\2\2\u00ad\u00ae\7t\2\2\u00ae"+
		"\u00af\7g\2\2\u00af\u00b0\7v\2\2\u00b0\u00b1\7w\2\2\u00b1\u00b2\7t\2\2"+
		"\u00b2\u00b3\7p\2\2\u00b3\24\3\2\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7"+
		"z\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7v\2\2\u00b8\26\3\2\2\2\u00b9\u00ba"+
		"\7r\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7k\2\2\u00bc\u00bd\7p\2\2\u00bd"+
		"\u00be\7v\2\2\u00be\30\3\2\2\2\u00bf\u00c0\7r\2\2\u00c0\u00c1\7t\2\2\u00c1"+
		"\u00c2\7k\2\2\u00c2\u00c3\7p\2\2\u00c3\u00c4\7v\2\2\u00c4\u00c5\7n\2\2"+
		"\u00c5\u00c6\7p\2\2\u00c6\32\3\2\2\2\u00c7\u00c8\7e\2\2\u00c8\u00c9\7"+
		"c\2\2\u00c9\u00ca\7n\2\2\u00ca\u00cb\7n\2\2\u00cb\34\3\2\2\2\u00cc\u00cd"+
		"\7k\2\2\u00cd\u00ce\7h\2\2\u00ce\36\3\2\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1"+
		"\7j\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3\7p\2\2\u00d3 \3\2\2\2\u00d4\u00d5"+
		"\7g\2\2\u00d5\u00d6\7n\2\2\u00d6\u00d7\7u\2\2\u00d7\u00d8\7g\2\2\u00d8"+
		"\"\3\2\2\2\u00d9\u00da\7h\2\2\u00da\u00db\7k\2\2\u00db$\3\2\2\2\u00dc"+
		"\u00dd\7y\2\2\u00dd\u00de\7j\2\2\u00de\u00df\7k\2\2\u00df\u00e0\7n\2\2"+
		"\u00e0\u00e1\7g\2\2\u00e1&\3\2\2\2\u00e2\u00e3\7h\2\2\u00e3\u00e4\7q\2"+
		"\2\u00e4\u00e5\7t\2\2\u00e5(\3\2\2\2\u00e6\u00e7\7f\2\2\u00e7\u00e8\7"+
		"q\2\2\u00e8*\3\2\2\2\u00e9\u00ea\7f\2\2\u00ea\u00eb\7q\2\2\u00eb\u00ec"+
		"\7p\2\2\u00ec\u00ed\7g\2\2\u00ed,\3\2\2\2\u00ee\u00ef\7k\2\2\u00ef\u00f0"+
		"\7p\2\2\u00f0\u00f1\7v\2\2\u00f1.\3\2\2\2\u00f2\u00f3\7d\2\2\u00f3\u00f4"+
		"\7q\2\2\u00f4\u00f5\7q\2\2\u00f5\u00f6\7n\2\2\u00f6\60\3\2\2\2\u00f7\u00f8"+
		"\7e\2\2\u00f8\u00f9\7j\2\2\u00f9\u00fa\7c\2\2\u00fa\u00fb\7t\2\2\u00fb"+
		"\62\3\2\2\2\u00fc\u00fd\7u\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\7t\2\2\u00ff"+
		"\u0100\7k\2\2\u0100\u0101\7p\2\2\u0101\u0102\7i\2\2\u0102\64\3\2\2\2\u0103"+
		"\u0104\7h\2\2\u0104\u0105\7u\2\2\u0105\u0106\7v\2\2\u0106\66\3\2\2\2\u0107"+
		"\u0108\7u\2\2\u0108\u0109\7p\2\2\u0109\u010a\7f\2\2\u010a8\3\2\2\2\u010b"+
		"\u010c\7r\2\2\u010c\u010d\7c\2\2\u010d\u010e\7k\2\2\u010e\u010f\7t\2\2"+
		"\u010f:\3\2\2\2\u0110\u0111\7p\2\2\u0111\u0112\7g\2\2\u0112\u0113\7y\2"+
		"\2\u0113\u0114\7r\2\2\u0114\u0115\7c\2\2\u0115\u0116\7k\2\2\u0116\u0117"+
		"\7t\2\2\u0117<\3\2\2\2\u0118\u0119\7p\2\2\u0119\u011a\7w\2\2\u011a\u011b"+
		"\7n\2\2\u011b\u011c\7n\2\2\u011c>\3\2\2\2\u011d\u011e\7n\2\2\u011e\u011f"+
		"\7g\2\2\u011f\u0120\7p\2\2\u0120@\3\2\2\2\u0121\u0122\7q\2\2\u0122\u0123"+
		"\7t\2\2\u0123\u0124\7f\2\2\u0124B\3\2\2\2\u0125\u0126\7e\2\2\u0126\u0127"+
		"\7j\2\2\u0127\u0128\7t\2\2\u0128D\3\2\2\2\u0129\u012a\7v\2\2\u012a\u012b"+
		"\7t\2\2\u012b\u012c\7w\2\2\u012c\u012d\7g\2\2\u012dF\3\2\2\2\u012e\u012f"+
		"\7h\2\2\u012f\u0130\7c\2\2\u0130\u0131\7n\2\2\u0131\u0132\7u\2\2\u0132"+
		"\u0133\7g\2\2\u0133H\3\2\2\2\u0134\u0135\t\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\u0137\b$\2\2\u0137J\3\2\2\2\u0138\u013c\7%\2\2\u0139\u013b\13\2\2\2\u013a"+
		"\u0139\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013d\3\2\2\2\u013c\u013a\3\2"+
		"\2\2\u013d\u013f\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0140\t\2\2\2\u0140"+
		"\u0141\3\2\2\2\u0141\u0142\b%\2\2\u0142L\3\2\2\2\u0143\u0144\t\3\2\2\u0144"+
		"N\3\2\2\2\u0145\u0147\t\4\2\2\u0146\u0145\3\2\2\2\u0147\u014b\3\2\2\2"+
		"\u0148\u014a\t\5\2\2\u0149\u0148\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149"+
		"\3\2\2\2\u014b\u014c\3\2\2\2\u014cP\3\2\2\2\u014d\u014b\3\2\2\2\u014e"+
		"\u014f\7*\2\2\u014fR\3\2\2\2\u0150\u0151\7+\2\2\u0151T\3\2\2\2\u0152\u0153"+
		"\7]\2\2\u0153V\3\2\2\2\u0154\u0155\7_\2\2\u0155X\3\2\2\2\u0156\u0157\7"+
		".\2\2\u0157Z\3\2\2\2\u0158\u0159\7=\2\2\u0159\\\3\2\2\2\u015a\u015b\7"+
		"#\2\2\u015b^\3\2\2\2\u015c\u015d\7/\2\2\u015d`\3\2\2\2\u015e\u015f\7,"+
		"\2\2\u015fb\3\2\2\2\u0160\u0161\7\61\2\2\u0161d\3\2\2\2\u0162\u0163\7"+
		"\'\2\2\u0163f\3\2\2\2\u0164\u0165\7-\2\2\u0165h\3\2\2\2\u0166\u0167\7"+
		"@\2\2\u0167j\3\2\2\2\u0168\u0169\7@\2\2\u0169\u016a\7?\2\2\u016al\3\2"+
		"\2\2\u016b\u016c\7>\2\2\u016cn\3\2\2\2\u016d\u016e\7>\2\2\u016e\u016f"+
		"\7?\2\2\u016fp\3\2\2\2\u0170\u0171\7?\2\2\u0171\u0172\7?\2\2\u0172r\3"+
		"\2\2\2\u0173\u0174\7#\2\2\u0174\u0175\7?\2\2\u0175t\3\2\2\2\u0176\u0177"+
		"\7(\2\2\u0177\u0178\7(\2\2\u0178v\3\2\2\2\u0179\u017a\7~\2\2\u017a\u017b"+
		"\7~\2\2\u017bx\3\2\2\2\u017c\u017d\t\6\2\2\u017d\u017e\3\2\2\2\u017e\u017f"+
		"\b<\2\2\u017fz\3\2\2\2\u0180\u0181\7^\2\2\u0181\u0182\t\7\2\2\u0182|\3"+
		"\2\2\2\u0183\u0184\5{=\2\u0184~\3\2\2\2\u0185\u0186\7^\2\2\u0186\u0080"+
		"\3\2\2\2\u0187\u0188\7)\2\2\u0188\u0189\3\2\2\2\u0189\u018a\b@\3\2\u018a"+
		"\u0082\3\2\2\2\u018b\u018c\7$\2\2\u018c\u018d\3\2\2\2\u018d\u018e\bA\4"+
		"\2\u018e\u0084\3\2\2\2\u018f\u0192\n\b\2\2\u0190\u0192\5{=\2\u0191\u018f"+
		"\3\2\2\2\u0191\u0190\3\2\2\2\u0192\u0086\3\2\2\2\u0193\u0194\7)\2\2\u0194"+
		"\u0195\3\2\2\2\u0195\u0196\bC\5\2\u0196\u0088\3\2\2\2\u0197\u019a\n\t"+
		"\2\2\u0198\u019a\5{=\2\u0199\u0197\3\2\2\2\u0199\u0198\3\2\2\2\u019a\u019b"+
		"\3\2\2\2\u019b\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u008a\3\2\2\2\u019d"+
		"\u019e\7$\2\2\u019e\u019f\3\2\2\2\u019f\u01a0\bE\5\2\u01a0\u008c\3\2\2"+
		"\2\u01a1\u01a2\4\"\u0080\2\u01a2\u008e\3\2\2\2\f\2\3\4\u013c\u0146\u0149"+
		"\u014b\u0191\u0199\u019b\6\b\2\2\7\3\2\7\4\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}