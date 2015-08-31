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
        generarLexer(path);
        PrintWriter writer=new PrintWriter(new File ("resultados.txt"));
        evaluarEjemplos("ejemplo_1.txt",writer);
    }
    
            
    public static void generarLexer(String path){
        File file=new File(path);
        jflex.Main.generate(file);
    }
    
    public static void evaluarEjemplos(String nombreArch,PrintWriter writer) throws IOException{
		Reader reader = new BufferedReader(new FileReader(nombreArch));
		Lexer lexer = new Lexer (reader);
	    String resultado="";
	    System.out.println("RESULTADOS DEL ARCHIVO: "+nombreArch);
	    writer.println("RESULTADOS DEL ARCHIVO: "+nombreArch);
	    while (true){
	        Token token =lexer.yylex();
	        if (token == null){
	            resultado=resultado+"FIN DE ARCHIVO";
	            System.out.println(resultado);
	            resultado=resultado+"------------------------------";
	            System.out.println("------------------------------");
	            writer.println("FIN DE ARCHIVO");
	            writer.println("------------------------------------");
	            writer.println("");
	            return;
	        }
	        switch (token){
	            case ERROR:
	            	resultado=resultado+ "linea:"+lexer.fila+", "+"Columna: "+lexer.columna+";\n RESPUESTA: Error, simbolo no reconocido: '"+lexer.lexeme+"'\n";
	                writer.println("linea:"+lexer.fila+", "+"Columna: "+lexer.columna+";"); 
	                writer.println("RESPUESTA: Error, simbolo no reconocido: '"+lexer.lexeme);
	            	break;
	            case ID: case NUM:
	            	resultado=resultado+ "linea:"+lexer.fila+", "+"Columna: "+lexer.columna+";\n <TOKEN "+token+": '" + lexer.lexeme + "'> \n";
	            	writer.println("linea:"+lexer.fila+", "+"Columna: "+lexer.columna+";");
	            	writer.println("<TOKEN "+token+": '" + lexer.lexeme + "'>");
	            	break;
	            case IF: case VOID: case INT: case ELSE: case RETURN:
	            case WHILE:
	            	resultado=resultado+ "linea:"+lexer.fila+", "+"Columna: "+lexer.columna+";\n <palabra clave "+token+": '"+lexer.lexeme+"'>\n";
	            	writer.println("linea:"+lexer.fila+", "+"Columna: "+lexer.columna+";");
	            	writer.println("<palabra clave "+token+": '"+lexer.lexeme+"'>");
	            	break;
	            default:
	            	resultado=resultado+"linea:"+lexer.fila+", "+"Columna: "+lexer.columna+";\n <Simbolo especial "+token+": '" +lexer.lexeme+"'>\n";
	            	writer.println("linea:"+lexer.fila+", "+"Columna: "+lexer.columna+";");
	            	writer.println("<Simbolo especial "+token+": '" +lexer.lexeme+"'>");
	            	break;
	        }
	    }
	    	    
	}
    
}
