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
public class AccionesIndicador {
    
    public ArrayList<ArrayList<Indicador>> elimnarVacio(ArrayList<ArrayList<Indicador>> documento){
        
        ArrayList<ArrayList<Indicador>> documentoNuevo = new ArrayList();
        
        
        
        
        for (ArrayList<Indicador> indicadores : documento) {
            
            ArrayList<Indicador> nuevo = new ArrayList();
            
            for (Indicador indicador: indicadores) {
                
                if (!indicador.Agno.equals("")) {

                    if(!indicador.Grado.equals("")){

                         if (!indicador.Ind_am.equals("")) {

                            if(!indicador.Ind_cc.equals("")){
                                if(!indicador.Ind_hv.equals("")){
                                    if (!indicador.Ind_pf.equals("")) {
                                    
                                        nuevo.add(indicador);
                                    }
                                }
                            }
                        }
                        
                    }
                }
                
            }
            
            documentoNuevo.add(nuevo);
        }
     
        documento.clear();
        return documentoNuevo;
        
    }
    
    public void subBuscar(ArrayList<Indicador> escuelas1,ArrayList<Indicador> escuelas2){
        reiniciarSeRepite(escuelas1);
        
        for (int i = 0; i < escuelas1.size(); i++) {
            
            Indicador escuela1 = (Indicador) escuelas1.get(i);
            
            for (int j = 0; j < escuelas2.size(); j++) {
                
                Indicador escuela2 = (Indicador) escuelas2.get(j);
                
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
    
    public void borrar(ArrayList<Indicador> escuelas, ArrayList<Indicador> escuelasBorrar ){
        
        for (int i = 0; i < escuelas.size(); i++) {
            
            Indicador escuela =(Indicador)  escuelas.get(i);
            
            for (int j = 0; j < escuelasBorrar.size(); j++) {
            
                    Indicador escuelaBorrar = (Indicador)  escuelasBorrar.get(j);
                
                    if (Objects.equals(escuela.RBD, escuelaBorrar.RBD)) {

                        escuelas.remove(i);
                        i--;
                    }
                    
               
                
            }
            
        }
    }

    public void buscarRepetidas(ArrayList<Indicador> escuelas, ArrayList<Indicador> borrar1) {
        int c1=-1;
        for (Indicador escuela: escuelas) {

            if (escuela.SeRepite==0) {

                borrar1.add(escuela);
                c1 +=1;
            }
        }
        
    }
    
    public void imprimirDocumentoCompleto(ArrayList<Indicador> escuelas){
        
        for (Indicador escuela: escuelas) {

            escuela.imprimir();

        }
    }
    
    public void imprimirBorrado(ArrayList<Indicador> escuelas){
        
        for (Indicador escuela: escuelas) {
            if (escuela.SeRepite==0) {
                escuela.imprimir();
            }
        }
    }

    public void exportarExcel(ArrayList<Indicador> escuelas,String direccion) {
        
        String hoja="Hoja1";

        XSSFWorkbook libro= new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);

        //poner negrita a la cabecera
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();

        //generar los datos para el documento
        for (int i = 0; i < escuelas.size(); i++) {
            try {
                Indicador escuela = (Indicador) escuelas.get(i);

                XSSFRow row= hoja1.createRow(i);//se crea las filas
                for (int j = 0; j < 7; j++) {

                    XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                    cell.setCellStyle(style); // se añade el style crea anteriormente 
                    
                    if (j==0) {
                        cell.setCellValue(escuela.Agno);
                    }
                    if (j==1) {
                        cell.setCellValue(escuela.Grado);
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
                        cell.setCellValue(escuela.Ind_am);
                    }
                    if (j==4) {
                        cell.setCellValue(escuela.Ind_cc);
                    }
                    if (j==5) {
                        cell.setCellValue(escuela.Ind_hv);
                    }
                    if (j==6) {
                        cell.setCellValue(escuela.Ind_pf);
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
    
    public void reiniciarSeRepite(ArrayList<Indicador> escuelas){
        
        for (Indicador escuela: escuelas) {
            escuela.SeRepite=0;
        }
        
    }
}
