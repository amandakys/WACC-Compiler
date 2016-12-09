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
FOR: 'for';
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

NOT: '!';
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

WS: [ \n\r\t] -> skip;
fragment ESCAPECHAR: '\\' ['"0btnfr\\];
ESCAPE: ESCAPECHAR;
ESC_SLASH: '\\';

CHARLITERAL: '\'' -> pushMode(CHARMODE);
STRINGLITERAL: '\"' -> pushMode(STRINGMODE);

mode CHARMODE;
EXCLUDECHAR: ~['"\\\r\n] | ESCAPECHAR ;
CHARCLOSE: '\'' -> popMode;

mode STRINGMODE;
EXCLUDESTRING: (~[''"\\] | ESCAPECHAR)+ ;
STRINGCLOSE: '\"' -> popMode;

OTHER: ' ' .. '~';
