/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Rodrigo
 */
public class AccionesComparar {
    
    public static ArrayList<Comparar> rbdEscuela = new ArrayList();
    public static ArrayList<Comparar> rbdSimce = new ArrayList();
    public static ArrayList<Comparar> rbdIndicador = new ArrayList();
    
    public void sacarListRBD(ArrayList<Escuela> escuelas, ArrayList<Simce> simces ,ArrayList<Indicador> indicadores){
        
        for (Escuela escuela: escuelas) {
            
            Comparar comparar = new Comparar();
            comparar.RBD = escuela.RBD;
            
            rbdEscuela.add(comparar);
            
        }
        
        for (Simce simce: simces) {
            
            Comparar comparar = new Comparar();
            comparar.RBD = simce.RBD;
            
            rbdSimce.add(comparar);
        }
        
        for (Indicador indicador : indicadores) {
            
            Comparar comparar = new Comparar();
            comparar.RBD = indicador.RBD;
            
            rbdIndicador.add(comparar);
        }
        
    }
    
  public void subBuscar(ArrayList<Comparar> escuelas1,ArrayList<Comparar> escuelas2){
        reiniciarSeRepite(escuelas1);
        
        for (int i = 0; i < escuelas1.size(); i++) {
            
            Comparar escuela1 = (Comparar) escuelas1.get(i);
            
            for (int j = 0; j < escuelas2.size(); j++) {
                
                Comparar escuela2 = (Comparar) escuelas2.get(j);
                //System.out.println(escuela1.RBD+" ||| "+ escuela2.RBD);
                
                if (Objects.equals(escuela1.RBD, escuela2.RBD)) {
                    escuela1.SeRepite += 1;
                    
                    j= 10000000;
                    //System.out.println("TERMINO");
                }
                else{
                    escuela1.SeRepite = 0;
                }
                
               
            }
        
        }
    }
    
    public void borrar(ArrayList<Comparar> escuelas, ArrayList<Comparar> escuelasBorrar ){
        
        for (int i = 0; i < escuelas.size(); i++) {
            
            Comparar escuela =(Comparar)  escuelas.get(i);
            
            for (int j = 0; j < escuelasBorrar.size(); j++) {
            
                    Comparar escuelaBorrar = (Comparar)  escuelasBorrar.get(j);
                
                    if (Objects.equals(escuela.RBD, escuelaBorrar.RBD)) {

                        escuelas.remove(i);
                        i--;
                    }
                    
               
                
            }
            
        }
    }

    public void buscarRepetidas(ArrayList<Comparar> escuelas, ArrayList<Comparar> borrar1) {
        int c1=-1;
        for (Comparar escuela: escuelas) {

            if (escuela.SeRepite==0) {

                borrar1.add(escuela);
                c1 +=1;
            }
        }
        
    }
    
    public void reiniciarSeRepite(ArrayList<Comparar> escuelas){
        
        for (Comparar escuela: escuelas) {
            escuela.SeRepite=0;
        }
        
    }

    public ArrayList<Escuela> armarEscuela(ArrayList<Comparar> escuelaRbd, ArrayList<Escuela> escuelas) {
        
        int[] rbd = new int[escuelaRbd.size()];
        ArrayList<Escuela> nuevo = new ArrayList();
        
        for (int i = 0; i < escuelaRbd.size(); i++) {
            Comparar temp = (Comparar) escuelaRbd.get(i);
            rbd[i] = temp.RBD;
        }
        
        
        for (int i = 0; i < escuelas.size(); i++) {
            
            Escuela escuela = (Escuela) escuelas.get(i);
            
            for (int j = 0; j < rbd.length; j++) {
                
                if (escuela.RBD==rbd[j]) {
                    
                    nuevo.add(escuela);
                    
                    j = rbd.length+1;
                }
            }
            
        }
        System.out.println("nuevo archico");
        System.out.println(nuevo.size());
        return nuevo;
        
    }
    
    public ArrayList<Indicador> armarIndicador(ArrayList<Comparar> escuelaRbd, ArrayList<Indicador> escuelas) {
        
        int[] rbd = new int[escuelaRbd.size()];
        ArrayList<Indicador> nuevo = new ArrayList();
        
        for (int i = 0; i < escuelaRbd.size(); i++) {
            Comparar temp = (Comparar) escuelaRbd.get(i);
            rbd[i] = temp.RBD;
        }
        
        
        for (int i = 0; i < escuelas.size(); i++) {
            
            Indicador escuela = (Indicador) escuelas.get(i);
            
            for (int j = 0; j < rbd.length; j++) {
                
                if (escuela.RBD==rbd[j]) {
                    
                    nuevo.add(escuela);
                    
                    j = rbd.length+1;
                }
            }
            
        }
        System.out.println("nuevo archico");
        System.out.println(nuevo.size());
        return nuevo;
        
    }
    public ArrayList<Simce> armarSimce(ArrayList<Comparar> escuelaRbd, ArrayList<Simce> escuelas) {
        
        int[] rbd = new int[escuelaRbd.size()];
        ArrayList<Simce> nuevo = new ArrayList();
        
        for (int i = 0; i < escuelaRbd.size(); i++) {
            Comparar temp = (Comparar) escuelaRbd.get(i);
            rbd[i] = temp.RBD;
        }
        
        
        for (int i = 0; i < escuelas.size(); i++) {
            
            Simce escuela = (Simce) escuelas.get(i);
            
            for (int j = 0; j < rbd.length; j++) {
                
                if (escuela.RBD==rbd[j]) {
                    
                    nuevo.add(escuela);
                    
                    j = rbd.length+1;
                }
            }
            
        }
        System.out.println("nuevo archico");
        System.out.println(nuevo.size());
        return nuevo;
        
    }
}