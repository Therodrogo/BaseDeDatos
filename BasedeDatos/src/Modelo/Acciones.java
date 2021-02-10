/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Rodrigo -Martin
 */
public class Acciones {
   
    public void subBuscar(ArrayList<Escuela> escuelas1,ArrayList<Escuela> escuelas2){
        reiniciarSeRepite(escuelas1);
        
        for (int i = 0; i < escuelas1.size(); i++) {
            
            Escuela escuela1 = (Escuela) escuelas1.get(i);
            
            for (int j = 0; j < escuelas2.size(); j++) {
                
                Escuela escuela2 = (Escuela) escuelas2.get(j);
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
    
    public void borrar(ArrayList<Escuela> escuelas, ArrayList<Escuela> escuelasBorrar ){
        
        for (int i = 0; i < escuelas.size(); i++) {
            
            Escuela escuela =(Escuela)  escuelas.get(i);
            
            for (int j = 0; j < escuelasBorrar.size(); j++) {
            
                    Escuela escuelaBorrar = (Escuela)  escuelasBorrar.get(j);
                
                    if (Objects.equals(escuela.RBD, escuelaBorrar.RBD)) {

                        escuelas.remove(i);
                        i--;
                    }
                    
               
                
            }
            
        }
    }

    public void buscarRepetidas(ArrayList<Escuela> escuelas, ArrayList<Escuela> borrar1) {
        int c1=-1;
        for (Escuela escuela: escuelas) {

            if (escuela.SeRepite==0) {

                borrar1.add(escuela);
                c1 +=1;
            }
        }
        System.out.println("Cantidad de Repetidas: "+c1);
        
    }
    
    public void imprimirDocumentoCompleto(ArrayList<Escuela> escuelas){
        System.out.println("Nueva Lista   ");
        
        for (Escuela escuela: escuelas) {

            escuela.imprimir();

        }
    }
    
    public void imprimirBorrado(ArrayList<Escuela> escuelas){
        
        for (Escuela escuela: escuelas) {
            if (escuela.SeRepite==0) {
                escuela.imprimir();
            }
        }
    }

    public void exportarExcel(ArrayList<Escuela> escuelas,String direccion) {
        
        String hoja="Hoja1";

        XSSFWorkbook libro= new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);

        //poner negrita a la cabecera
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();

        //generar los datos para el documento
        for (int i = 0; i < escuelas.size(); i++) {
            try {
                Escuela escuela = (Escuela) escuelas.get(i);

                XSSFRow row= hoja1.createRow(i);//se crea las filas
                for (int j = 0; j < 7; j++) {

                    XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                    cell.setCellStyle(style); // se añade el style crea anteriormente 
                    if (j==0) {
                        if (i==0) {
                            cell.setCellValue("RBD") ;
                        }
                        else{
                            cell.setCellValue(escuela.RBD) ;
                        }
                    }
                    if (j==1) {
                        cell.setCellValue(escuela.Nombre);
                    }
                    if (j==2) {
                        cell.setCellValue(escuela.Comuna);
                    }
                    if (j==3) {
                        cell.setCellValue(escuela.Dependencia);
                    }
                    if (j==4) {
                        cell.setCellValue(escuela.Matricula);
                    }
                    if (j==5) {
                        cell.setCellValue(escuela.Rendimiento);
                    }


                }
            } catch (Exception e) {
            }
        }

        File file;
        file = new File(direccion+".xlsx");
        try (FileOutputStream fileOuS = new FileOutputStream(file)){						
            if (file.exists()) {// si el archivo existe se elimina
                    file.delete();
                    System.out.println("Archivo eliminado");
            }
            libro.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
            System.out.println("Archivo Creado");

        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    public void reiniciarSeRepite(ArrayList<Escuela> escuelas){
        
        for (Escuela escuela: escuelas) {
            escuela.SeRepite=0;
        }
        
    }
}
