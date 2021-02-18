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
public class AccionesSimce {
   
    public void subBuscar(ArrayList<Simce> escuelas1,ArrayList<Simce> escuelas2){
        reiniciarSeRepite(escuelas1);
        
        for (int i = 0; i < escuelas1.size(); i++) {
            
            Simce escuela1 = (Simce) escuelas1.get(i);
            
            for (int j = 0; j < escuelas2.size(); j++) {
                
                Simce escuela2 = (Simce) escuelas2.get(j);
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
    
    public void borrar(ArrayList<Simce> escuelas, ArrayList<Simce> escuelasBorrar ){
        
        for (int i = 0; i < escuelas.size(); i++) {
            
            Simce escuela =(Simce)  escuelas.get(i);
            
            for (int j = 0; j < escuelasBorrar.size(); j++) {
            
                    Simce escuelaBorrar = (Simce)  escuelasBorrar.get(j);
                
                    if (Objects.equals(escuela.RBD, escuelaBorrar.RBD)) {

                        escuelas.remove(i);
                        i--;
                    }
                    
               
                
            }
            
        }
    }

    public void buscarRepetidas(ArrayList<Simce> escuelas, ArrayList<Simce> borrar1) {
        int c1=-1;
        for (Simce escuela: escuelas) {

            if (escuela.SeRepite==0) {

                borrar1.add(escuela);
                c1 +=1;
            }
        }
        
    }
    
    public void imprimirDocumentoCompleto(ArrayList<Simce> escuelas){

        for (Simce escuela: escuelas) {

            escuela.imprimir();

        }
    }
    
    public void imprimirBorrado(ArrayList<Simce> escuelas){
        
        for (Simce escuela: escuelas) {
            if (escuela.SeRepite==0) {
                escuela.imprimir();
            }
        }
    }

    public void exportarExcel(ArrayList<Simce> escuelas,String direccion) {
        
        String hoja="Hoja1";

        XSSFWorkbook libro= new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);

        //poner negrita a la cabecera
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();

        //generar los datos para el documento
        for (int i = 0; i < escuelas.size(); i++) {
            try {
                Simce escuela = (Simce) escuelas.get(i);

                XSSFRow row= hoja1.createRow(i);//se crea las filas
                for (int j = 0; j < 19; j++) {

                    XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                    cell.setCellStyle(style); // se añade el style crea anteriormente 
                    if (j==0) {
                        cell.setCellValue(escuela.agno) ;
                    }
                    if (j==1) {
                        cell.setCellValue(escuela.grado) ;
                    }
                    if (j==2) {
                        if (i==0) {
                            cell.setCellValue("RBD") ;
                        }
                        else{
                            cell.setCellValue(escuela.RBD) ;
                        }
                    }
                    if (j==3) {
                        cell.setCellValue(escuela.dvrbd) ;
                    }
                    if (j==4) {
                        cell.setCellValue(escuela.nom_rbd);
                    }
                    if (j==5) {
                        cell.setCellValue(escuela.cod_reg_rbd);
                    }
                    if (j==6) {
                        cell.setCellValue(escuela.nom_reg_rbd);
                    }
                    if (j==7) {
                        cell.setCellValue(escuela.cod_pro_rbd);
                    }
                    if (j==8) {
                        cell.setCellValue(escuela.nom_pro_rbd);
                    }
                    if (j==9) {
                        cell.setCellValue(escuela.cod_com_rbd);
                    }
                    if (j==10) {
                        cell.setCellValue(escuela.nom_com_rbd);
                    }
                    if (j==11) {
                        cell.setCellValue(escuela.cod_depe1);
                    }
                    if (j==12) {
                        cell.setCellValue(escuela.cod_depe2);
                    }
                    if (j==13) {
                        cell.setCellValue(escuela.cod_grupo);
                    }
                    if (j==14) {
                        cell.setCellValue(escuela.cod_rural_rbd);
                    }
                    if (j==15) {
                        cell.setCellValue(escuela.nalu_lect4b_rbd);
                    }
                    if (j==16) {
                        cell.setCellValue(escuela.nalu_mate4b_rbd);
                    }
                    if (j==17) {
                        cell.setCellValue(escuela.prom_lect4b_rbd);
                    }
                    if (j==18) {
                        cell.setCellValue(escuela.prom_mate4b_rbd);
                    }
                }
            } catch (Exception e) {
            }
        }

        File file;
        file = new File(direccion+".xlsx");
        try {	
            FileOutputStream fileOuS = new FileOutputStream(file);				
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
    
    public void reiniciarSeRepite(ArrayList<Simce> escuelas){
        
        for (Simce escuela: escuelas) {
            escuela.SeRepite=0;
        }
        
    }
}
