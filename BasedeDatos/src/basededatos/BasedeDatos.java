/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Rodrigo - Martin
 */
public class BasedeDatos extends Application {
    
    public static double offLayoutX =0;
    
    public static double offLayoutY =0;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.initStyle(StageStyle.UNDECORATED);
      
        Parent root = FXMLLoader.load(getClass().getResource("Vista.fxml"));
       
        
        Scene scene = new Scene(root);
        
        scene.setOnMousePressed((event) -> {
           offLayoutX = event.getX();
           offLayoutY = event.getY();

        });
        scene.setOnMouseDragged((event) -> {
           double x = event.getScreenX();
           double y = event.getScreenY();
           
           stage.setX(x-offLayoutX);
           stage.setY(y-offLayoutY);
        });

        VistaController.stage = stage;

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
