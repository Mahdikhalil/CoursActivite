/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmljardin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Formatik
 */
public class Fxmljardin extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       try {
            Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
           

            Scene scene = new Scene(root, 600, 400);
            Image img=new Image("/resources/images/iconApp.png");
            stage.getIcons().add(img);
            stage.setTitle("Weldek");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Fxmljardin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
