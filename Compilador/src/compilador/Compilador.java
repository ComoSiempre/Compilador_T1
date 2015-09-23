/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.*;
/**
 * Proyecto correspondiente a la fase de analisis lexico de la implementacion de un compilador
 * para el lenguaje K*, utilizando el analizador lexico JFLEX.
 * @author:     Jonatham Vasquez - Eduardo Tapia
 * @version:    23/09/2015/1.0
 */
public class Compilador {

    /**
     * clase main donde comienza la aplicacion.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
        //variable donde obtengo la direccion del proyecto.
        final String dir =System.getProperty("user.dir");
        //variable usada para guardar la direccion del archivo .flex
        String pathNote=dir+"/src/compilador/Lexer.flex";
        //se llama a la funcion de generacion de lexer. 
        generarLexer(pathNote);
        //ciclo que recorre el testeo para cada ejemplo.
        for(int i=0; i<5; i++){
            //variable usada para generar los archovos de resultados.
            PrintWriter writer=new PrintWriter(new FileWriter ("resultado_"+(i+1)+".txt"));
            //llama a la funcion que evalua cada ejemplo.
            evaluarEjemplos("ejemplo_"+(i+1)+".txt",writer);
            //cierro la variable print.
            writer.close();
        }
    
        
    }
    
    /**
     * metodo que genera el archivo java lexer.java, que contiene la nomenclatura del analisis lexico.
     * @param path la direccion donde se encuentra el archivo .flex
     */       
    public static void generarLexer(String path){
        //se crea la variable File nesesaria para la generacion del lexer
        File file=new File(path);
        //se llama al paquete Jflex para la generacion del lexer enviando como parametro el archivo FIle.
        jflex.Main.generate(file);
    }
    /**
     * Metodo encargado de generar las cadenas de texto dependiendo del tipo de token y generar
     * la escritura en archivo.
     * @param nombreArch el nombre del archivo ejemplo.
     * @param writer la variable de escritura de archivo nesesaria la escritura de archivo.
     * @throws IOException 
     */
    public static void evaluarEjemplos(String nombreArch, PrintWriter writer) throws IOException {
        //variable para la lectura del archivo ejemplo.
        Reader reader = new BufferedReader(new FileReader(nombreArch));
        //genero variable Lexer para la deteccion de tokens
        Lexer lexer = new Lexer(reader);
        //cadena de texto que guarda el registro del testing.
        String resultado = "";
        System.out.println("RESULTADOS DEL ARCHIVO: " + nombreArch);
        writer.println("RESULTADOS DEL ARCHIVO: " + nombreArch+"\n");
        //ciclo que recorrera hasta el termino del archivo.
        while (true) {
            //genero una variable token para pa identificacion de tokens
            Token token = lexer.yylex();
            //condicionante usado para detectaar el fin del archivo.
            if (token == null) {
                resultado = resultado + "FIN DE ARCHIVO\n";
                resultado = resultado + "------------------------------";
                System.out.println(resultado);
                writer.print(resultado);
                return;
            }
            //condicionante usado para generar una cadena de texto dependiendo del tipo de token.
            switch (token) {
                //en caso de que se encuentre error lexico.
                case ERROR:
                    //se genera la cadena de error.
                    resultado = resultado + "Error Lexico, simbolo no reconocido: '" + lexer.lexeme + "'  linea: " + lexer.fila + ", Columna: " + lexer.columna + "\n";
                    System.out.println(resultado);
                    
                    writer.print(resultado);
                    return;
                case ID:
                case NUM:
                    resultado = resultado + "TOKEN " + token + ": '" + lexer.lexeme + "' \n";
                    
                    break;
                case IF:
                case VOID:
                case INT:
                case ELSE:
                case RETURN:
                case WHILE:
                    resultado = resultado + "palabra clave " + token + ": '" + lexer.lexeme + "'\n";
                    
                    break;
                default:
                    resultado = resultado + "Simbolo especial " + token + ": '" + lexer.lexeme + "' \n";
                    
                    break;
            }
        }

    }
    
}
