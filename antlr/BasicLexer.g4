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
PAIRLITERAL: 'null';
LEN: 'len';
ORD: 'ord';
CHR: 'chr';

TRUE: 'true';
FALSE: 'false';
EOL: [\n\r] -> skip;
COMMENT: '#' .*? [\r\n] -> skip;

DIGIT: [0-9] ;
IDENT: ([a-zA-Z] | '_')(([a-zA-Z0-9]) | '_')* ;

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

WS: [ \n\r] -> skip;

CHARLITERAL: '\'' -> pushMode(CHARMODE);
STRINGLITERAL: '\"' -> pushMode(STRINGMODE);

mode CHARMODE;
EXCLUDECHAR: ~[\'\"\\];
CHARCLOSE: '\'' -> popMode;

mode STRINGMODE;
EXCLUDESTRING: ~[\\\'\"]+;
STRINGCLOSE: '\"' -> popMode;

ESC_SLASH: '\\';
ESC_0: '0';
ESC_B: 'b';
ESC_T: 't';
ESC_N: 'n';
ESC_F: 'f';
ESC_R: 'r';

OTHER: ' ' .. '~';
