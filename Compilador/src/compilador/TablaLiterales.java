/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.Hashtable;

/**
 *
 * @author jonathan Vasquez - Eduardo Tapia
 */
public class TablaLiterales {
    //tabla que almacena literales
    private Hashtable<String,String> literales;
    //variable de instancia de la clase.
    private static TablaLiterales instanciaTabla;
    
    /**
     * constructor interno de la clase.
     */
    private TablaLiterales(){ 
    }
    
    /**
     * metodo donde obtengo la instancia de la clase.
     * 
     * @return instancia de la clase
     */
    public TablaLiterales getInstancia(){
        if(instanciaTabla==null)
            instanciaTabla=new TablaLiterales();
        return instanciaTabla;
    }
    /**
     * metodo que retorna la tabla de literales
     * @return tabla hash de literales.
     */
    public Hashtable<String,String> getLiterales(){
        return literales;
    }
}
