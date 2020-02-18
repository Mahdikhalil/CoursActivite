/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmljardin;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author Formatik
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button btnactivite;
    @FXML
    private Button btnGarde;
    @FXML
    private Button btncontine;
    @FXML
    private Button btnevenemen;
    @FXML
    private Button btndemande;
    @FXML
    private ImageView imgAcceuil;
    @FXML
    private Button btnreclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgAcceuil.setImage(new Image("/resources/images/jardin.png"));

      
    }    
    
    @FXML
    private void activite(ActionEvent event) {
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("CoursActivite.fxml"));
            Parent root = loader.load();
            CoursActiviteController dc=loader.getController();
            btnactivite.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void garde(ActionEvent event) {
    }

    @FXML
    private void contine(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void demande(ActionEvent event) {
        
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }
    
}
