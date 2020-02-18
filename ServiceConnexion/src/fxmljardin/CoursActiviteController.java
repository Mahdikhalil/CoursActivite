/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmljardin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Formatik
 */
public class CoursActiviteController implements Initializable {
    
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnConsulter;
    @FXML 
    private Button btnSupprimerModifier;
    @FXML
    private Label txtCoursActivite;
    @FXML
    private ImageView imghome;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imghome.setImage(new Image("/resources/images/home.png"));
    }    
    
     public Label getTxtCoursActivite() {
        return txtCoursActivite;
    }
     
     @FXML
    private void gohome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root = loader.load();
            AcceuilController dc = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    private void consultation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterCoursActivite.fxml"));
            Parent root = loader.load();
            ConsulterCoursActiviteController cdc = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
      private void suppModif(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerModifier.fxml"));
            Parent root = loader.load();
            SupprimerModifierController dc = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    private void Ajout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCoursActivite.fxml"));
            Parent root = loader.load();
            AjouterCoursActiviteController dc = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
