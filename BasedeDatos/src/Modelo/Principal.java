package Modelo;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 *
 * @author Rodrigo - Martin
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Iterator;
 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class Principal implements Serializable {
    
    public ArrayList<Escuela> escuelas = new ArrayList();
    public ArrayList<Simce> simces = new ArrayList();
    public ArrayList<Indicador> indicadores = new ArrayList();
    
    
    public int[] indices = new int[20];
    public int[] indicesId = new int[20];
    
    
    public boolean isSimce = false;
    public boolean isCDD = false;
    public boolean isId=false;
    
    public void abrirExcel(File fileName){

        List cellData= new ArrayList();
        try {
            
            FileInputStream fileInput = new FileInputStream(fileName);//okey
            XSSFWorkbook workBook = new XSSFWorkbook(fileInput);//okey
            
            XSSFSheet hssfSheet =  workBook.getSheetAt(0);//okey
            
            Iterator rowIterator = hssfSheet.rowIterator();//okey
            
            while(rowIterator.hasNext()){//okey
                
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();//okey
                
                Iterator iterator = hssfRow.cellIterator();//okey
                
                List cellTemp = new ArrayList();//okey
                
                while(iterator.hasNext()){//okey
                    
                    XSSFCell hssfCell = (XSSFCell) iterator.next();//okey
                    cellTemp.add(hssfCell);//okey
                     
                }
                cellData.add(cellTemp);//okey
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        obtener(cellData);//okey
        
    }
    public void cddDocument(Escuela escuela, int j, String stringCellValue){
        if (j==0) {
            try {
                Double temp = Double.parseDouble(stringCellValue);
                Integer temp2 = temp.intValue();

                escuela.RBD = temp2;
            } catch (Exception e) {

            }
        }
        if (j==1) {
            escuela.Nombre = stringCellValue;
        }
        if (j==2) {
            escuela.Region = stringCellValue;
        }
        if (j==3) {
            escuela.Comuna = stringCellValue;
        }
        if (j==4) {
            escuela.Dependencia = stringCellValue;
        }
        if (j==5) {
            escuela.Matricula = stringCellValue;
        }
        if (j==6) {
            escuela.Rendimiento = stringCellValue;
        }
    }
    public void sinceDocument(Simce simce, int j, String stringCellValue){

        if (indices[0]==j) {
            simce.agno = stringCellValue;
        }
        if (indices[1]==j) {
            simce.grado = stringCellValue;
        }
        if (indices[2]==j) {
            try {
                Double temp = Double.parseDouble(stringCellValue);
                Integer temp2 = temp.intValue();

                simce.RBD = temp2;
            } catch (Exception e) {
            }
        }
        if (indices[3]==j) {
            simce.dvrbd = stringCellValue;
        }
        if (indices[4]==j) {
            simce.nom_rbd = stringCellValue;
        }
        if (indices[5]==j) {
            simce.cod_reg_rbd = stringCellValue;
        }
        if (indices[6]==j) {
            simce.nom_reg_rbd = stringCellValue;
        }
        if (indices[7]==j) {
            simce.cod_pro_rbd = stringCellValue;
        }
        if (indices[8]==j) {
            simce.nom_pro_rbd = stringCellValue;
        }
        if (indices[9]==j) {
            simce.cod_com_rbd = stringCellValue;
        }
        if (indices[10]==j) {
            simce.nom_com_rbd =stringCellValue;
        }
        if (indices[11]==j) {
            simce.cod_depe1 = stringCellValue;
        }
        if (indices[12]==j) {
            simce.cod_depe2 = stringCellValue;
        }
        if (indices[13]==j) {
            simce.cod_grupo = stringCellValue;
        }
        if (indices[14]==j) {
            simce.cod_rural_rbd = stringCellValue;
        }
        if (indices[15]==j) {
            simce.nalu_lect4b_rbd = stringCellValue;
        }
        if (indices[16]==j) {
            simce.nalu_mate4b_rbd = stringCellValue;
        }
        if (indices[17]==j) {
            simce.prom_lect4b_rbd = stringCellValue;
        }
        if (indices[18]==j) {
            simce.prom_mate4b_rbd = stringCellValue;
        }
    }
    public void buscarIndices( int j, String stringCellValue){
        
        if (stringCellValue.equals("agno")) {
            indices[0] = j;
        }
        if (stringCellValue.equals("grado")) {
            indices[1] = j;
        }
        if (stringCellValue.equals("rbd")) {
            indices[2] = j;
        }
        if (stringCellValue.equals("dvrbd")) {
            indices[3] = j;
        }
        if (stringCellValue.equals("nom_rbd")) {
            indices[4] = j;
        }
        if (stringCellValue.equals("cod_reg_rbd")) {
            indices[5] = j;
        }
        if (stringCellValue.equals("nom_reg_rbd")) {
            indices[6] = j;
        }
        if (stringCellValue.equals("cod_pro_rbd")) {
            indices[7] = j;
        }
        if (stringCellValue.equals("nom_pro_rbd")) {
            indices[8] = j;
        }
        if (stringCellValue.equals("cod_com_rbd")) {
            indices[9] = j;
        }
        if (stringCellValue.equals("nom_com_rbd")) {
            indices[10] = j;
        }
        if (stringCellValue.equals("cod_depe1")) {
            indices[11] = j;
        }
        if (stringCellValue.equals("cod_depe2")) {
            indices[12] = j;
        }
        if (stringCellValue.equals("cod_grupo")) {
            indices[13] = j;
        }
        if (stringCellValue.equals("cod_rural_rbd")) {
            indices[14] = j;
        }
        if (stringCellValue.equals("nalu_lect4b_rbd")) {
            indices[15] = j;
        }
        if (stringCellValue.equals("nalu_mate4b_rbd")) {
            indices[16] = j;
        }
        if (stringCellValue.equals("prom_lect4b_rbd")) {
            indices[17] = j;
        }
        if (stringCellValue.equals("prom_mate4b_rbd")) {
            indices[18] = j;
        }
 
        
    }
    
    public void buscarIndicadoresId(){
        
    }
    
    private void obtener(List cellDataList){//okey
        
        
        for (int i = 0; i < cellDataList.size(); i++) {//okey
            
            List cellTempList = (List) cellDataList.get(i);//okey

            Escuela escuela = new Escuela(); 
            Simce simce = new Simce();
            for (int j = 0; j < cellTempList.size(); j++) {//okey

                XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);//okey
                String stringCellValue = "";
                
                stringCellValue = hssfCell.toString();//celda tomada
                
                if (i==0) {
                   
                    if (stringCellValue.equals("ind_am")) {
                        isId = true;
                        
                        isCDD = false;
                        isSimce =false;
                    }
                    if (stringCellValue.equals("Dependencia")) {
                        isCDD = true;
                        
                        isId=false;
                        isSimce=false;
                    }
                    if (stringCellValue.equals("prom_mate4b_rbd")) {
                        isSimce = true;
                        buscarIndices(j, stringCellValue);
                        isCDD = false;
                        isId=false;
                        
                    }
//                    System.out.println("CDD: "+isCDD);
//                    System.out.println("Simce: "+isSimce);
//                    System.out.println("Indicadores: "+isId);
                }

                
                if (isCDD || i==0) {
                    cddDocument(escuela, j, stringCellValue);
                }
                if (isSimce || i==0) {
                    
                    if (i==0) {
                        buscarIndices(j, stringCellValue);
                    }
                    sinceDocument(simce, j, stringCellValue);
                }
                if (isId || i==0) {
                    
                }
                
            }
            
            //Guardar Elementos identificando el tipo de archivo
            if (isCDD || i==0) {
                escuelas.add(escuela);
            }
            if (isSimce || i==0) {
                simces.add(simce);
            }
        }
         
    }

}
    

