
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Rodrigo
 */
public class Escuela implements Serializable {
    
    public Integer RBD = 0;
    public String Nombre= "";
    public String Region= "";
    public String Comuna= "";
    public String Dependencia="";
    public String Matricula= "";
    public String Rendimiento= "";
    public Integer SeRepite = 0;
    
    
    public void imprimir(){
        
        System.out.println(RBD+" "+Nombre+" " +Region +" "+ Comuna+" "+ Dependencia +" " + Matricula +" "+ Rendimiento);
    }
    
}
