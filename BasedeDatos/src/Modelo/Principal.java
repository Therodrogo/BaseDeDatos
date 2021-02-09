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
    private void obtener(List cellDataList){//okey
         
        for (int i = 0; i < cellDataList.size(); i++) {//okey
            
            List cellTempList = (List) cellDataList.get(i);//okey
            Escuela escuela = new Escuela(); 
            for (int j = 0; j < cellTempList.size(); j++) {//okey
                
                XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);//okey
                String stringCellValue = "";
                stringCellValue = hssfCell.toString();//okey
                //System.out.print(stringCellValue+" ");
                
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
            escuelas.add(escuela);
            //System.out.println("");
            
            
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
    

