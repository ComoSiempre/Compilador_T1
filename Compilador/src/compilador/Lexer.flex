package compilador;
import static compilador.Token.*;
%%
%class Lexer
%type Token
%line
%column
LETRA = [a-zA-Z]
DIGITO = [0-9]
NUMERO = 0 | [1-9][0-9]*
ELSE = ([eE])([lL])([sS])([eE])
IF = ("i"|"I")("f"|"F")
INT = ([Ii])([Nn])([Tt])
VOID = ([vV])([oO])([iI])([dD])
RETURN = ([rR])([eE])([tT])([uU])([rR])([nN])
WHILE = ([wW])([hH])([iI])([lL])([eE])
FOR = ([Ff])([Oo])([Rr]) 

SALTO=\n|\r|\r\n /*saltos de linea, que no nos interesa*/
InputCharacter = [^\r\n] /*cualquier cosa excepto /r/n*/
ESPACIOS     = {SALTO} | [ \t\f] /*tabulaciones o saltos de linea */
Commentario = {multiComment} | {lineComment}
multiComment   = "/#" ~"#/"
lineComment     = "%" {InputCharacter}* {SALTO}?

%{
public String lexeme;
public int fila;
public int columna;
%}
%%
{SALTO} {/*Ignore*/}
{ESPACIOS} {/*Ignore*/}
{Commentario} {lexeme=yytext(); fila=yyline; columna=yycolumn; return COMENTARIO;}
{IF} {lexeme=yytext(); fila=yyline; columna=yycolumn; return IF;}
{INT} {lexeme=yytext(); fila=yyline; columna=yycolumn; return INT;}
{ELSE} {lexeme=yytext(); fila=yyline; columna=yycolumn; return ELSE;}
{RETURN} {lexeme=yytext(); fila=yyline; columna=yycolumn; return RETURN;}
{VOID} {lexeme=yytext(); return VOID;}
{WHILE} {lexeme=yytext(); return WHILE;}

"+" {lexeme=yytext(); fila=yyline; columna=yycolumn; return SUMA;}
"-" {lexeme=yytext(); fila=yyline; columna=yycolumn; return RESTA;}
"*" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MULTIPLICACION;}
"/" {lexeme=yytext(); fila=yyline; columna=yycolumn; return DIVISION;}
"^" {lexeme=yytext(); fila=yyline; columna=yycolumn; return EXPONIENCIACION_1;}
"**" {lexeme=yytext(); fila=yyline; columna=yycolumn; return EXPONIENCIACION_2;}
"::=" {lexeme=yytext(); fila=yyline; columna=yycolumn; return ASSIGN;}
"(" {lexeme=yytext(); fila=yyline; columna=yycolumn; return PARENTHLEFT;}
")" {lexeme=yytext(); fila=yyline; columna=yycolumn; return PARENTHRIGHT;}
"[" {lexeme=yytext(); fila=yyline; columna=yycolumn; return BRACKETLEFT;}
"]" {lexeme=yytext(); fila=yyline; columna=yycolumn; return BRACKETRIGHT;}
"{" {lexeme=yytext(); fila=yyline; columna=yycolumn; return BRACELEFT;}
"}" {lexeme=yytext(); fila=yyline; columna=yycolumn; return BRACERIGHT;}
"LT" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MENOR;}
"LEQ" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MENIGUAL;}
"GT" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MAYOR;}
"GEQ" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MAYIGUAL;}
"EQ" {lexeme=yytext(); fila=yyline; columna=yycolumn; return IGUALIGUAL;}
"NEQ" {lexeme=yytext(); fila=yyline; columna=yycolumn; return DISTINTO;}
";" {lexeme=yytext(); fila=yyline; columna=yycolumn; return PUNTOCOMA;}
"," {lexeme=yytext(); fila=yyline; columna=yycolumn; return COMA;}



([A-Z]){LETRA}* {lexeme=yytext(); fila=yyline; columna=yycolumn; return ERROR;}
([a-z]){LETRA}*{DIGITO}* {lexeme=yytext();  fila=yyline; columna=yycolumn; return ID;}
("_"){LETRA}+ {lexeme=yytext(); fila=yyline; columna=yycolumn; return ID;}
("_"){LETRA}+{DIGITO} {lexeme=yytext(); fila=yyline; columna=yycolumn; return ID;}
{NUMERO} {lexeme=yytext(); fila=yyline; columna=yycolumn; return NUM;}
"[^]" {lexeme=yytext(); fila=yyline; columna=yycolumn; return ERROR;}
. {lexeme=yytext(); fila=yyline; columna=yycolumn; return ERROR;}
