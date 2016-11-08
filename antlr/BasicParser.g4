parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

program: BEGIN function* statement END;

function: type IDENT LPAREN paramlist? RPAREN IS statement END;

paramlist: param (COMMA param)*;

param: type IDENT;

statement: SKIP                                         #skip
| type IDENT ASSIGN assignrhs                           #var_decl
| assignlhs ASSIGN assignrhs                            #assignment
| READ assignlhs                                        #read
| FREE expression                                       #free
| RETURN expression                                     #return
| EXIT expression                                       #exit
| PRINT expression                                      #print
| PRINTLN expression                                    #println
| IF expression THEN statement ELSE statement ENDIF     #if
| WHILE expression DO statement DONE                    #while
| BEGIN statement END                                   #begin
| statement SEMI statement                              #sequence
;

assignlhs: IDENT | arrayelem | pairelem ;

assignrhs: expression                                   #expr
| arrayliter                                            #arraylit
| NEWPAIR LPAREN expression COMMA expression RPAREN     #newpair
| pairelem                                              #pairelement
| CALL IDENT LPAREN arglist? RPAREN                     #functioncall
;

arglist: expression (COMMA expression)*;

pairelem: FIRST expression | SECOND expression;

type: basetype | arraytype | pairtype;

basetype: INT | BOOL | CHAR | STRING;

arraytype: basetype (LBRACKET RBRACKET)+ | pairtype (LBRACKET RBRACKET)+;

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