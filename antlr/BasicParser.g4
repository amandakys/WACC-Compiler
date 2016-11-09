parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

program: BEGIN function* statement END EOF;

function: type IDENT LPAREN paramlist? RPAREN IS statement END;

paramlist: param (COMMA param)*;

param: type IDENT;

statement: SKIP
| type IDENT ASSIGN assignrhs
| assignlhs ASSIGN assignrhs
| READ assignlhs
| FREE expression
| RETURN expression
| EXIT expression
| PRINT expression
| PRINTLN expression
| IF expression THEN statement ELSE statement ENDIF
| WHILE expression DO statement DONE
| BEGIN statement END
| statement SEMI statement
;

assignlhs: IDENT | arrayelem | pairelem ;

assignrhs: expression
| arrayliter
| NEWPAIR LPAREN expression COMMA expression RPAREN
| pairelem
| CALL IDENT LPAREN arglist? RPAREN
;

arglist: expression (COMMA expression)*;

pairelem: (FIRST | SECOND) expression;

type: basetype | arraytype | pairtype;

basetype: INT | BOOL | CHAR | STRING;

arraytype: (basetype | pairtype) (LBRACKET RBRACKET)+;

pairtype: PAIR LPAREN pairelemtype COMMA pairelemtype RPAREN;

pairelemtype: basetype | arraytype | PAIR;

expression: intliter
| boolliter
| charliter
| strliter
| PAIRLITERAL
| IDENT
| arrayelem
| unop expression
| expression binop expression
| LPAREN expression RPAREN
;


binop: STAR | DIV | MOD | PLUS | MINUS | GREATER | GREATEREQUAL
| LESS | LESSEQUAL | EQUAL | NOTEQUAL | AND | OR
;

unop: FACTORIAL | MINUS | LEN | ORD | CHR ;

arrayelem: IDENT (LBRACKET expression RBRACKET)+ ;

intliter: intsign? DIGIT+ ;

intsign: PLUS | MINUS ;

boolliter: TRUE | FALSE;

charliter: CHARLITERAL EXCLUDECHAR CHARCLOSE;

strliter: STRINGLITERAL EXCLUDESTRING* STRINGCLOSE;

character: ~(ESC_SLASH | STRINGLITERAL | CHARLITERAL) | ESCAPE ;

arrayliter: LBRACKET (expression (COMMA expression)*)? RBRACKET;
