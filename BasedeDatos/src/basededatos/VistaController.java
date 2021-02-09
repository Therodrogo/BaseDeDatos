/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;


import Modelo.Acciones;
import Modelo.Escuela;
import Modelo.Principal;
import com.jfoenix.controls.JFXButton;
import java.applet.AudioClip;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Rodrigo - Martin
 */
public class VistaController implements Initializable {
    
    
    Principal principal = new Principal();
    @FXML
    private JFXButton nuevoArchivo;
    
    public int PosI = 0;
    public int PosJ = 0;
    public int banderaCargar=0;
    Acciones acciones = new Acciones();
    
    public ArrayList<ArrayList<Escuela>> Documentos = new ArrayList();
        
    public String nombreArchivo="";
    
    ArrayList<String> nombresDeArchivos= new ArrayList();
    
    ArrayList<Escuela>[] objetos = new ArrayList[20];
    
    int Indice =0;
    
    
    @FXML
    private GridPane elementos;
    @FXML
    private JFXButton buttonGuardar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
    }

    @FXML
    private void nuevoArchivo(ActionEvent event) {
        try {
            nuevoArchivo.setDisable(true);
            FileChooser file = new FileChooser();
            
            File abre = file.showOpenDialog(null);
            System.out.println(abre);

            if (abre.toString()!="null") {
                System.out.println("abrio");
                
                nuevoArchivo.setDisable(false);
                
                sacarName(abre);
                
                if (banderaCargar==1) {
                    agregarArchivo();
                }
                
                
                banderaCargar=0;
                principal.abrirExcel(abre);
                
                guardarTxt(principal.escuelas, nombreArchivo);
                principal.escuelas.clear();
                
                System.out.println(Documentos.size());
                
                nombreArchivo="";

                
            }
            
        } catch (Exception e) {
            nuevoArchivo.setDisable(false);
        }
        
    }
    public void sacarName(File archivo){
        
        String[] aux = archivo.getName().split(".xlsx");
            
        for (int i = 0; i < aux.length; i++) {

            nombreArchivo = nombreArchivo +aux[i];
        }
        
        
        int ingresar = 1;
        
        for (int i = 0; i < nombresDeArchivos.size(); i++) {
            
        
            System.out.println(nombresDeArchivos.get(i) +"|"+ nombreArchivo);
            if (nombresDeArchivos.get(i).equals(nombreArchivo)) {
                System.out.println("Son iguales");
                ingresar=0;
            }
        }
        
        
        if (ingresar==1) {
            System.out.println("nuevo");
            nombresDeArchivos.add(nombreArchivo);
            banderaCargar=1;
            
        }else{
            System.out.println("Ya existe el archivo");
            mostrarAlertError();
            banderaCargar=0;
            
        }
        
    }
    public void agregarArchivo(){
        
        JFXButton boton = new JFXButton(nombreArchivo);
        boton.setStyle("-fx-background-color: #f5ec8a;");
        boton.setPrefWidth(131);
        
        boton.setOnAction((event) -> {
            
            try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Ayaya.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
           } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
             System.out.println("Error al reproducir el sonido.");
           }
            
        });


        elementos.add(boton, PosI, PosJ);
        PosI= PosI+1;
        if (PosI==6) {
            PosI=0;
            PosJ+=1;
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
//            file.getExtensionFilters().addAll(
//            new ExtensionFilter("Excel", "*.xlsx"));
           
            File abre = file.showSaveDialog(null);
            
            if (abre.toString()!="null") {
                System.out.println(abre.toString());

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
                      
                
            }
            
        } catch (Exception e) {
            nuevoArchivo.setDisable(false);
        }
        
    }
    
    public void borrarElemento(ArrayList<Escuela> escuelas){
        
        if (Documentos.size()>2) {
            
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
    
    
    public void guardarTxt(ArrayList<Escuela> escuelas,String nombre) throws FileNotFoundException, IOException{

        ObjectOutputStream salida = new ObjectOutputStream( new FileOutputStream(nombre+".txt"));
        
        salida.writeObject(escuelas);
        salida.close();
        System.out.println("guadado");
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
            }
            
        }
        
        
    }
    
}
