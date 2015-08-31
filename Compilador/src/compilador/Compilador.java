/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.*;
/**
 *
 * @author jona
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
        // TODO code application logic here
        String path="E:/Documentos/Proyectos de Programacion/Compilador_T1/Compilador/src/compilador/Lexer.flex";
        String path2="C:/Users/jona/Dropbox/Compiladores (2da vez)/Talleres/Taller 1/Compilador_T1/Compilador/src/compilador/Lexer.flex";
        generarLexer(path2);
        PrintWriter writer=new PrintWriter(new FileWriter ("resultados.txt"));
        evaluarEjemplos("ejemplo_1.txt",writer);
        writer.close();
    }
    
            
    public static void generarLexer(String path){
        File file=new File(path);
        jflex.Main.generate(file);
    }
    
    public static void evaluarEjemplos(String nombreArch, PrintWriter writer) throws IOException {
        Reader reader = new BufferedReader(new FileReader(nombreArch));
        Lexer lexer = new Lexer(reader);
        String resultado = "";
        System.out.println("RESULTADOS DEL ARCHIVO: " + nombreArch);
        writer.println("RESULTADOS DEL ARCHIVO: " + nombreArch);
        while (true) {
            Token token = lexer.yylex();
            if (token == null) {
                resultado = resultado + "FIN DE ARCHIVO\n";
                resultado = resultado + "------------------------------";
                System.out.println(resultado);
                writer.print(resultado);
                //writer.println("FIN DE ARCHIVO");
                //writer.println("------------------------------------");
                //writer.println("");
                return;
            }
            switch (token) {
                case ERROR:
                    resultado = resultado + "Error Lexico, simbolo no reconocido: '" + lexer.lexeme + "'  linea: " + lexer.fila + ", Columna: " + lexer.columna + "\n";
                    //writer.println("RESPUESTA: Error, simbolo no reconocido: '" + lexer.lexeme+"'");
                    //writer.println("linea:" + lexer.fila + ", " + "Columna: " + lexer.columna + ";");
                    break;
                case ID:
                case NUM:
                    resultado = resultado + "TOKEN " + token + ": '" + lexer.lexeme + "' \n";
                    //writer.println("linea:" + lexer.fila + ", " + "Columna: " + lexer.columna + ";");
                    //writer.println("<TOKEN " + token + ": '" + lexer.lexeme + "'>");
                    break;
                case IF:
                case VOID:
                case INT:
                case ELSE:
                case RETURN:
                case WHILE:
                    resultado = resultado + "palabra clave " + token + ": '" + lexer.lexeme + "'\n";
                    //writer.println("linea:" + lexer.fila + ", " + "Columna: " + lexer.columna + ";");
                    //writer.println("<palabra clave " + token + ": '" + lexer.lexeme + "'>");
                    break;
                default:
                    resultado = resultado + "Simbolo especial " + token + ": '" + lexer.lexeme + "'\n";
                    //writer.println("linea:" + lexer.fila + ", " + "Columna: " + lexer.columna + ";");
                    //writer.println("<Simbolo especial " + token + ": '" + lexer.lexeme + "'>");
                    break;
            }
        }

    }
    
}
