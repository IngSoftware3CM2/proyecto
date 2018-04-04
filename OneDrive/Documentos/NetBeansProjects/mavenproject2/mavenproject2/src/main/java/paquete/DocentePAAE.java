/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

/**
 *
 * @author jonat
 */
public class DocentePAAE {
    private String rfc=null;
    private String nombre=null;
    private String apPaterno=null;
    private String numTrab=null;
    
    public DocentePAAE(String nombre, String apPat, String numTrab, String rfc) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.apPaterno = apPat;
        this.numTrab=numTrab;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getrfc() {
        return this.rfc;
    }
    
    public String getapPat(){
        return this.apPaterno;
    }
    
    public String getNumTrab(){
        return this.numTrab;
    }
       
    
}
