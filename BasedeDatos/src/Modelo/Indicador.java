/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Rodrigo
 */
public class Indicador implements Serializable{
    
    
    
    public String Agno= "";
    public String Grado= "";
    public Integer RBD = 0;
    public String Ind_am= "";
    public String Ind_cc="";
    public String Ind_hv= "";
    public String Ind_pf= "";
    
    public Integer SeRepite = 0;

    public void imprimir(){
        
        System.out.println(RBD+" "+Agno+" " +Grado +" "+Ind_am+" "+Ind_cc+" " +Ind_hv+" "+Ind_pf);
    }
    
    
    
}
