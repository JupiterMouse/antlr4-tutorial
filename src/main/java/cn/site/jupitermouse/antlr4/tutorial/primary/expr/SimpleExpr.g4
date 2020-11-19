grammar SimpleExpr;
/* The state rule; being parsing here. */
prog: stat+ ;

stat: expr NEWLINE
    | ID '=' expr NEWLINE
    | NEWLINE
    ;

expr: expr ('*'|'/') expr
    | expr ('+'|'-') expr
    | INT
    | ID
    | '(' expr ')'
    ;

ID : [a-zA-Z]+ ; // match identifiers - - -
INT : [0-9]+ ; // match integers -
NEWLINE:'\r'? '\n' ; // return newlines to parser (is end-statement signal) 20
WS : [ \t]+ -> skip ; // toss out whitespace


// 语法分析器 以小写字母开头，词法分析器以大写字母开头
// 使用｜分割若个干备选分支
// 词法符号定义中的标记和正则表达式的元字符非常相似。
// 现在,唯一不寻常的地方在于WS词法规则后面的“->skip”操作。
// 它是一条指令,告诉词法分析器匹配并丢弃空白字符(每个输入的字符都必须被至少一条词法规则匹配)。