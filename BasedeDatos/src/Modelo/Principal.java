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
    
    public int[] indices = new int[15];
    public int tipoArchivo = 0 ;
    
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
            
            try {
                Double temp = Double.parseDouble(stringCellValue);
                Integer temp2 = temp.intValue();

                simce.RBD = temp2;
            } catch (Exception e) {

            }
        }
        if (indices[1]==j) {
            simce.nom_rbd = stringCellValue;
        }
        if (indices[2]==j) {
            simce.cod_depe1 = stringCellValue;
        }
        if (indices[3]==j) {
            simce.cod_depe2 = stringCellValue;
        }
        if (indices[4]==j) {
            simce.cod_grupo = stringCellValue;
        }
        if (indices[5]==j) {
            simce.cod_rural_rbd = stringCellValue;
        }
        if (indices[6]==j) {
            simce.nalu_lect4b_rbd = stringCellValue;
        }
        
        if (indices[7]==j) {
            simce.nalu_mate4b_rbd = stringCellValue;
        }
        if (indices[8]==j) {
            simce.prom_lect4b_rbd = stringCellValue;
        }
        if (indices[9]==j) {
            simce.prom_mate4b_rbd = stringCellValue;
        }
    }
    public void buscarIndices( int j, String stringCellValue){
        
        if (stringCellValue.equals("rbd")) {
            indices[0] = j;
        }
        if (stringCellValue.equals("nom_rbd")) {
            indices[1] = j;
        }
        if (stringCellValue.equals("cod_depe1")) {
            indices[2] = j;
        }
        if (stringCellValue.equals("cod_depe2")) {
            indices[3] = j;
        }
        if (stringCellValue.equals("cod_grupo")) {
            indices[4] = j;
        }
        if (stringCellValue.equals("cod_rural_rbd")) {
            indices[5] = j;
        }
        if (stringCellValue.equals("nalu_lect4b_rbd")) {
            indices[6] = j;
        }
        if (stringCellValue.equals("nalu_mate4b_rbd")) {
            indices[7] = j;
        }
        if (stringCellValue.equals("prom_lect4b_rbd")) {
            indices[8] = j;
        }
        if (stringCellValue.equals("prom_mate4b_rbd")) {
            indices[9] = j;
        }
 
        
        
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
                
                //System.out.print(stringCellValue+" ");
                
                if (cellTempList.size()==7) {
                    cddDocument(escuela, j, stringCellValue);
                }
                if (cellTempList.size()<=41 && cellTempList.size()>7) {
                    
                    if (i==0) {
                        buscarIndices(j, stringCellValue);
                    }
                    sinceDocument(simce, j, stringCellValue);

                }
                
            }
            if (cellTempList.size()==7) {
                tipoArchivo=7;
                escuelas.add(escuela);
            }
            if (cellTempList.size()<=41 && cellTempList.size()>7) {
                tipoArchivo=41;
                simces.add(simce);
            }
        }
         
    }
        
//        for (Escuela escuela: escuelas2017) {
//            
//            escuela.imprimir();
//        }
        
        
//---------------------------------------------------------------------------------------------------------------        
        //Leer archibos
//        File file = new File("C:/Users/Rodrigo/Desktop/CDD2019.xlsx");
//        if (file.exists()) {
//            JavaApplication1 obj = new JavaApplication1(file);
//        }
//        ObjectOutputStream salida = new ObjectOutputStream( new FileOutputStream("Datos2019.txt"));
//        
//        salida.writeObject(escuelas);
//        salida.close();
//        System.out.println("guadado");
//---------------------------------------------------------------------------------------------------------------            

}
    

