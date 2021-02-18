/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Rodrigo - Martin
 */
public class Simce implements Serializable{
    
    public String agno = "";
    public String grado = "";
    public Integer RBD = 0;
    public String dvrbd = "";
    public String nom_rbd = "";
    public String cod_reg_rbd = "";
    public String nom_reg_rbd = "";
    public String cod_pro_rbd = "";
    public String nom_pro_rbd = "";
    public String cod_com_rbd = "";
    public String nom_com_rbd = "";
    
    public String cod_depe1= "";
    public String cod_depe2 = "";
    public String cod_grupo = "";
    public String cod_rural_rbd = "";
    public String nalu_lect4b_rbd = "";
    public String nalu_mate4b_rbd = "";
    public String prom_lect4b_rbd = "";
    public String prom_mate4b_rbd = "";
    
    public Integer SeRepite = 0;

    public void imprimir(){
        
        System.out.println(RBD +" "+nom_rbd+" "+cod_depe1+" "+cod_depe2+" "+cod_grupo+" "+cod_rural_rbd+" "+nalu_lect4b_rbd+" "+nalu_mate4b_rbd+" "+prom_lect4b_rbd+" "+prom_mate4b_rbd);
    }
    
}
