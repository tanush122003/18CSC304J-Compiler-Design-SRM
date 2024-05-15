%{
#include <stdio.h>
%}

%token NUMBER
%left '+' '-'
%left '*' '/'

%%

input: /* empty */
     | input line
     ;

line: '\n'
    | exp '\n' { printf("= %d\n", $1); }
    ;

exp: exp '+' exp { $$ = $1 + $3; }
   | exp '-' exp { $$ = $1 - $3; }
   | exp '*' exp { $$ = $1 * $3; }
   | exp '/' exp { if ($3 != 0) $$ = $1 / $3; else yyerror("Division by zero"); }
   | '(' exp ')' { $$ = $2; }
   | NUMBER      { $$ = $1; }
   ;

%%

int yylex() {
    int c = getchar();
    if (c == '\n')
        return 0;
    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')')
        return c;
    if (isdigit(c)) {
        ungetc(c, stdin);
        scanf("%d", &yylval);
        return NUMBER;
    }
    return c;
}

void yyerror(char *s) {
    printf("%s\n", s);
}

int main() {
    yyparse();
    return 0;
}
