package compilador;
import static compilador.Token.*;
%%
%class Lexer
%type Token
%line
%column
LETRA = [a-zA-Z]
DIGITO = [0-9]
NUMERO = DIGITO | [1-9]{DIGITO}*
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
multiComment   = "/#" [^"/#"]"#/"
lineComment     = "%" {InputCharacter}* {SALTO}?

%{
public String lexeme;
public int fila;
public int columna;
%}
%%
{SALTO} {/*Ignorar*/}
{ESPACIOS} {/*Ignorar*/}
{Commentario} {lexeme=yytext(); fila=yyline; columna=yycolumn; return COMENTARIO;}
{IF} {lexeme=yytext(); fila=yyline; columna=yycolumn; return IF;}
{INT} {lexeme=yytext(); fila=yyline; columna=yycolumn; return INT;}
{ELSE} {lexeme=yytext(); fila=yyline; columna=yycolumn; return ELSE;}
{RETURN} {lexeme=yytext(); fila=yyline; columna=yycolumn; return RETURN;}
{VOID} {lexeme=yytext(); return VOID;}
{WHILE} {lexeme=yytext(); return WHILE;}
{FOR} {lexeme=yytext(); return FOR;}

"+" {lexeme=yytext(); fila=yyline; columna=yycolumn; return SUMA;}
"-" {lexeme=yytext(); fila=yyline; columna=yycolumn; return RESTA;}
"*" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MULTIPLICACION;}
"/" {lexeme=yytext(); fila=yyline; columna=yycolumn; return DIVISION;}
"^" {lexeme=yytext(); fila=yyline; columna=yycolumn; return EXPONENCIACION_1;}
"**" {lexeme=yytext(); fila=yyline; columna=yycolumn; return EXPONENCIACION_2;}
"::=" {lexeme=yytext(); fila=yyline; columna=yycolumn; return ASSIGN;}
"(" {lexeme=yytext(); fila=yyline; columna=yycolumn; return PARENT_IZQ;}
")" {lexeme=yytext(); fila=yyline; columna=yycolumn; return PARENT_DER;}
"[" {lexeme=yytext(); fila=yyline; columna=yycolumn; return CORCH_IZQ;}
"]" {lexeme=yytext(); fila=yyline; columna=yycolumn; return CORCH_DER;}
"{" {lexeme=yytext(); fila=yyline; columna=yycolumn; return LLAVE_IZQ;}
"}" {lexeme=yytext(); fila=yyline; columna=yycolumn; return LLAVE_DER;}
"LT" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MENOR;}
"LEQ" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MENIGUAL;}
"GT" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MAYOR;}
"GEQ" {lexeme=yytext(); fila=yyline; columna=yycolumn; return MAYIGUAL;}
"EQ" {lexeme=yytext(); fila=yyline; columna=yycolumn; return IGUALIGUAL;}
"NEQ" {lexeme=yytext(); fila=yyline; columna=yycolumn; return DISTINTO;}
";" {lexeme=yytext(); fila=yyline; columna=yycolumn; return PUNTOCOMA;}
"," {lexeme=yytext(); fila=yyline; columna=yycolumn; return COMA;}

([a-z]){LETRA}*_?{LETRA}*{DIGITO}* {lexeme=yytext();  fila=yyline; columna=yycolumn; return ID;}
{NUMERO} {lexeme=yytext(); fila=yyline; columna=yycolumn; return NUM;}
. {lexeme=yytext(); fila=yyline; columna=yycolumn; return ERROR;}

