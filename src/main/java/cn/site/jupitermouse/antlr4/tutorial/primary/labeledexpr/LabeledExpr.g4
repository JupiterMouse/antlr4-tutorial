grammar LabeledExpr;

/* The state rule; being parsing here. */
prog: stat+ ;

stat: expr NEWLINE          # printExpr
    | ID '=' expr NEWLINE   # assign
    | 'clear' NEWLINE       # clear
    | NEWLINE               # blank
    ;

expr: expr op=('*'|'/') expr # MulDiv
    | expr op=('+'|'-') expr # AddSub
    | INT                    # int
    | ID                     # id
    | '(' expr ')'           # parens
    ;

MUL : '*' ; //上诉语法中使用的*命名为MUL
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;

ID : [a-zA-Z]+ ; // match identifiers - - -
INT : [0-9]+ ; // match integers -
NEWLINE:'\r'? '\n' ; // return newlines to parser (is end-statement signal) 20
WS : [ \t]+ -> skip ; // toss out whitespace