lexer grammar BasicLexer;

BEGIN: 'begin';
END: 'end';
IS: 'is';
SKIP: 'skip';
ASSIGN: '=';
READ: 'read';
FREE: 'free';
RETURN: 'return';
EXIT: 'exit';
PRINT: 'print';
PRINTLN: 'println';
CALL: 'call';
IF: 'if';
THEN: 'then';
ELSE: 'else';
ENDIF: 'fi';
WHILE: 'while';
DO: 'do';
DONE: 'done';

INT: 'int';
BOOL: 'bool';
CHAR: 'char';
STRING: 'string';

FIRST: 'fst';
SECOND: 'snd';

PAIR: 'pair';

NEWPAIR: 'newpair';

LEN: 'len';
ORD: 'ord';
CHR: 'chr';

PAIRLITERAL: 'null';

ESC_0: '0';
ESC_B: 'b';
ESC_T: 't';
ESC_N: 'n';
ESC_F: 'f';
ESC_R: 'r';


TRUE: 'true';
FALSE: 'false';
EOL: [\r\n] -> skip;
COMMENT: '#' .*? [\r\n] -> skip;

IDENT: ([a-zA-Z] | '_')(([a-zA-Z0-9]) | '_')* ;
DIGIT: [0-9] ;
LPAREN: '(' ;
RPAREN: ')' ;
LBRACKET: '[' ;
RBRACKET: ']' ;


COMMA: ',' ;
SEMI: ';' ;


FACTORIAL: '!';
MINUS: '-';

STAR: '*';
DIV: '/';
MOD: '%';
PLUS: '+';
GREATER: '>';
GREATEREQUAL: '>=';
LESS: '<';
LESSEQUAL: '<=';
EQUAL: '==';
NOTEQUAL: '!=';
AND: '&&';
OR: '||';
CHARLITERAL: '\'';
STRINGLITERAL: '\"';
WS: ' ' -> skip;
ESC_SLASH: '\\';
