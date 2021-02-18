/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;


import Modelo.Acciones;
import Modelo.AccionesSimce;
import Modelo.Escuela;
import Modelo.Principal;
import Modelo.Simce;
import com.jfoenix.controls.JFXButton;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Rodrigo - Martin
 */
public class VistaController implements Initializable {
    
    
    Principal principal = new Principal();
    
    Acciones acciones = new Acciones();

    ArrayList<String> nombresDeArchivos= new ArrayList();
    ArrayList<String> nombresDeArchivosSimce= new ArrayList();
    
    ArrayList<Escuela>[] objetos = new ArrayList[20];
    ArrayList<Simce>[] objetosSimces = new ArrayList[20];
    
    public ArrayList<ArrayList<Escuela>> Documentos = new ArrayList();
    public ArrayList<ArrayList<Simce>> DocumentosSimce = new ArrayList(); 
    
    int Indice =0;
    int IndiceSimce=0;
    
    public int PosI = 0;
    public int PosJ = 0;
    public int PosIS = 0;
    public int PosJS = 0;
    
    public int banderaCargar=0;
    
    public String nombreArchivo="";

    @FXML
    private JFXButton nuevoArchivo;

    @FXML
    private GridPane elementos;
    @FXML
    private JFXButton buttonGuardar;
    @FXML
    private GridPane elementos3;
    @FXML
    private GridPane elementos2;
    @FXML
    private JFXButton botonGuardarSimce;
    @FXML
    private Pane formatoSimce;
    @FXML
    private Pane formatoCDD;
    @FXML
    private Pane formatoId;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void nuevoArchivo(ActionEvent event) {
        try {
            nuevoArchivo.setDisable(true);
            FileChooser file = new FileChooser();
            file.getExtensionFilters().addAll(
            new ExtensionFilter("Excel", "*.xlsx"));
            
            File abre = file.showOpenDialog(null);
            System.out.println(abre);

            if (!"null".equals(abre.toString())) {
                
                nuevoArchivo.setDisable(false);
                
                String nombreFile = sacarName(abre);
                

                //Si el nombre es valido se carga
                
                principal.abrirExcel(abre);

                //Guardar TXT de CDD
                if (principal.isCDD) {
                    if (validarName(nombresDeArchivos, nombreFile)) {
                        agregarButtonCDD(nombreFile);
                        guardarTxt(principal.escuelas, nombreFile);
                    }
                    
                }
                
                //Guardar TXT de Simce
                if (principal.isSimce) {
                    if (validarName(nombresDeArchivosSimce, nombreFile)) {
                        agregarButtonSimce(nombreFile);
                    guardarTxtSimce(principal.simces, nombreFile);
                    }
                    
                }
                

                principal.escuelas.clear();
                principal.simces.clear();
                
                nombreArchivo="";

            }
            
        } catch (Exception e) {
            nuevoArchivo.setDisable(false);
        }
        
    }
    
    public boolean validarName(ArrayList<String> archivosName, String nombre){
        int ingresar = 1;
        
        for (int i = 0; i < archivosName.size(); i++) {

            System.out.println(archivosName.get(i) +"|"+ nombre);
            if (archivosName.get(i).equals(nombre)) {
                ingresar=0;
            }
        }

        if (ingresar==1) {
            archivosName.add(nombre);
            banderaCargar=1;
            
            return true;
            
        }else{
            mostrarAlertError();
            banderaCargar=0;
            
            return false;
        }
    }
    
    public String sacarName(File archivo){
        
        String[] aux = archivo.getName().split(".xlsx");
            
        for (int i = 0; i < aux.length; i++) {

            nombreArchivo = nombreArchivo +aux[i];
        }

        return nombreArchivo;

    }
    
    
    public void agregarButtonCDD(String nombre){
        
        JFXButton boton = new JFXButton(nombreArchivo);
        boton.textFillProperty().set(Paint.valueOf("white"));
        boton.setStyle("-fx-background-color: #4460b1;");
        boton.setPrefWidth(131);
        
//        boton.setOnAction((event) -> {
//            
//            try {
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Ayaya.wav").getAbsoluteFile());
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//            clip.start();
//           } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
//             System.out.println("Error al reproducir el sonido.");
//           }
//            
//        });

        elementos.add(boton, PosI, PosJ);
        
        PosI= PosI+1;
        if (PosI==4) {
            PosI=0;
            PosJ+=1;
        }

    }
    
    public void agregarButtonSimce(String nombre){
        
        JFXButton boton = new JFXButton(nombre);
        boton.textFillProperty().set(Paint.valueOf("white"));
        boton.setStyle("-fx-background-color: #5CDB2C;");
        boton.setPrefWidth(131);
        
//        boton.setOnAction((event) -> {
//            
//            try {
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Ayaya.wav").getAbsoluteFile());
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//            clip.start();
//           } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
//             System.out.println("Error al reproducir el sonido.");
//           }
//            
//        });

        elementos3.add(boton, PosIS, PosJS);

        PosIS= PosIS+1;
        if (PosIS==4) {
            PosIS=0;
            PosJS+=1;
        }
        
    }
    
    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        alert.setContentText("El Archivo ya esta cargado.");
        alert.showAndWait();
    }

    @FXML
    private void guardarExcel(ActionEvent event) {
        try {
            buttonGuardar.setDisable(true);
            
            FileChooser file = new FileChooser();
            File abre = file.showSaveDialog(null);
            if (!"null".equals(abre.toString())) {
                
                cargarTxt(nombresDeArchivos);
                for (int i = 0; i < Documentos.size(); i++) {
                    System.out.println(Documentos.get(i));
                    borrarElemento(Documentos.get(i));
                }
                for (int i = 0; i < Documentos.size(); i++) {
                    acciones.exportarExcel(Documentos.get(i),abre.toString()+nombresDeArchivos.get(i));

                }

                buttonGuardar.setDisable(false);

                elementos.getChildren().clear();
                Documentos.clear();
                nombresDeArchivos.clear();
                PosI=0;
                PosJ=0;
                
                PosIS=0;
                PosJS=0;
            }
           
                 
            
        } catch (Exception e) {
            buttonGuardar.setDisable(false);
        }
        
    }
    
    public void borrarElemento(ArrayList<Escuela> escuelas){
        
        if (Documentos.size()>=2) {
            
            Acciones acciones = new Acciones();
            
            for (int i = 0; i < Documentos.size(); i++) {
                try {
                    ArrayList<Escuela> borrar = new ArrayList();
                    System.out.println(escuelas.size()+"||||");
                    
                    acciones.subBuscar(escuelas, (ArrayList<Escuela>)Documentos.get(i));
                    acciones.buscarRepetidas(escuelas,borrar);
                    acciones.borrar(escuelas, borrar);
                    
                } catch (Exception e) {
                    System.out.println("Borrado completado");
                }
                
            }
           
        }
        else{
            System.out.println("FALTAN ELEMENTOS");
        }
    }
    
    public void borrarElementoSimce(ArrayList<Simce> simces){
        
        if (DocumentosSimce.size()>=2) {
            
            AccionesSimce acciones = new AccionesSimce ();
            
            for (int i = 0; i < DocumentosSimce.size(); i++) {
                try {
                    ArrayList<Simce> borrar = new ArrayList();
                    System.out.println(simces.size()+"||||");
                    
                    acciones.subBuscar(simces, (ArrayList<Simce>)DocumentosSimce.get(i));
                    acciones.buscarRepetidas(simces,borrar);
                    acciones.borrar(simces, borrar);
                    
                } catch (Exception e) {
                    System.out.println("Borrado completado");
                }
                
            }
           
        }
        else{
            System.out.println("FALTAN ELEMENTOS");
        }
    }
    
    
    public void guardarTxt(ArrayList<Escuela> escuelas,String nombre) throws FileNotFoundException, IOException{

        ObjectOutputStream salida = new ObjectOutputStream( new FileOutputStream(nombre+".txt"));
        
        salida.writeObject(escuelas);
        salida.close();
    }
    public void guardarTxtSimce(ArrayList<Simce> simces,String nombre) throws FileNotFoundException, IOException{

        ObjectOutputStream salida = new ObjectOutputStream( new FileOutputStream(nombre+".txt"));
        
        salida.writeObject(simces);
        salida.close();
    }
    
    public void cargarTxt(ArrayList<String> nombresDeArchivos) throws FileNotFoundException, IOException, ClassNotFoundException{
        for (int i = 0; i < nombresDeArchivos.size(); i++) {
            try {
                System.out.println(nombresDeArchivos.get(i)+".txt");
            
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombresDeArchivos.get(i)+".txt"));
            
                objetos[Indice] = (ArrayList) entrada.readObject();
            
                Documentos.add(objetos[Indice]);
                
                Indice = Indice + 1;

                entrada.close();
            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println("SE CAYP ###21321");
            } 
        }
    
    }
    
    public void cargarTxtSimce(ArrayList<String> nombresDeArchivos) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        System.out.println(nombresDeArchivos.size());
        for (int i = 0; i < nombresDeArchivos.size(); i++) {
            try {
                System.out.println(nombresDeArchivos.get(i)+".txt");
            
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombresDeArchivos.get(i)+".txt"));
            
                objetosSimces[IndiceSimce] = (ArrayList<Simce>) entrada.readObject();
            
                DocumentosSimce.add(objetosSimces[IndiceSimce]);
                
                IndiceSimce = IndiceSimce + 1;

                entrada.close();
            } catch (Exception e) {
                System.out.println("Se cayo");
            } 
        }
    
    }

    @FXML
    private void guardarExcelSimce(ActionEvent event) {
        try {
            botonGuardarSimce.setDisable(true);
            
            FileChooser file = new FileChooser();
            File abre = file.showSaveDialog(null);
            AccionesSimce accionesSimce = new AccionesSimce();
            if (!"null".equals(abre.toString())) {
                
                cargarTxtSimce(nombresDeArchivosSimce);
                
                //Borrar Elementos que se repiten
                for (int i = 0; i < DocumentosSimce.size(); i++) {
                    System.out.println(DocumentosSimce.get(i));
                    borrarElementoSimce(DocumentosSimce.get(i));
                }
                
                //Exportar Excel
                for (int i = 0; i < DocumentosSimce.size(); i++) {
                    accionesSimce.exportarExcel(DocumentosSimce.get(i),abre.toString()+nombresDeArchivosSimce.get(i));

                }

                botonGuardarSimce.setDisable(false);

                elementos3.getChildren().clear();
                DocumentosSimce.clear();
                nombresDeArchivosSimce.clear();
                
                PosIS=0;
                PosJS=0;
            }
           
                 
            
        } catch (Exception e) {
            botonGuardarSimce.setDisable(false);
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
        
    }
    public static boolean maximized=false;
    public static Stage stage;
    
    @FXML
    private void minimizar(ActionEvent event) {
        maximized=true;
        stage.setIconified(maximized);
        maximized=false;
        
    }
    
    //Abrir y cerrar ventanas de formato 
    
    boolean Cdd = true;
    boolean Id = true;
    boolean Simce= true;
    
    @FXML
    private void verFormatoCDD(ActionEvent event) {
        if (Cdd) {
            formatoCDD.setVisible(true);
            Cdd= false;
        }
        else{
            formatoCDD.setVisible(false);
            Cdd= true;
        }
        
    }

    @FXML
    private void verFormatoId(ActionEvent event) {
        if (Id) {
            formatoId.setVisible(true);
            Id=false;
        }
        else{
            formatoId.setVisible(false);
            Id=true;
        }
        
        
    }

    @FXML
    private void verFormatoSimce(ActionEvent event) {
        if (Simce) {
            formatoSimce.setVisible(true);
            Simce= false;
        }
        else{
            formatoSimce.setVisible(false);
            Simce= true;
        }
        
    }

    @FXML
    private void cerrarFormatoSimce(ActionEvent event) {
        formatoSimce.setVisible(false);
        Simce= true;
    }

    @FXML
    private void cerrarFormatoCDD(ActionEvent event) {
        formatoCDD.setVisible(false);
        Cdd =true;
    }

    @FXML
    private void cerrarFormatoId(ActionEvent event) {
        formatoId.setVisible(false);
        Id=true;
    }
    
}
