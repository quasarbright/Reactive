grammar Reactive;

fragment DIGIT : '0'..'9';

DOUBLE
    : (DIGIT)+'.'
    | '.'(DIGIT)+
    | (DIGIT)+'.'(DIGIT)+
    ;

INTEGER
    : (DIGIT)+
    ;

fragment ID_START
    : 'a' .. 'z' | 'A' .. 'Z' | '_'
    ;

fragment ID_CHAR
    : ID_START
    | '0' .. '9'
    ;

IDENTIFIER
    : ID_START ID_CHAR*
    ;

LPAREN : '(';
RPAREN : ')';

PLUS : '+';
MINUS : '-';
TIMES : '*';
DIVIDE : '/';
EQUALS : '=';

WS : [ \n\r\t] -> skip;

program : assignment* EOF;

assignment : IDENTIFIER EQUALS expr;

expr: e=sumDiff;

exprProgram : expr EOF;

sumDiff
    : left=sumDiff PLUS right=prodDiv # Plus
    | left=sumDiff MINUS right=prodDiv # Minus
    | prodDiv # SDChild
    ;

prodDiv
    : left=prodDiv TIMES right=uminus # Times
    | left=prodDiv DIVIDE right=uminus # Divide
    | uminus # PDChild
    ;

uminus
    : MINUS child=uminus # UMinus
    | parenExpr # UMChild
    ;

parenExpr
    : atomic
    | LPAREN expr RPAREN
    ;

atomic
    : DOUBLE # Double
    | INTEGER # Integer
    | IDENTIFIER # Identifier
    ;