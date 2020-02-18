/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmljardin;

import com.esprit.Entite.CoursActivite;
import com.esprit.Service.ServiceCoursActivite;
import com.sun.media.jfxmedia.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.zip.DataFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Formatik
 */
public class AjouterCoursActiviteController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField date;
    @FXML
    private TextField heureDebut;
    @FXML
    private TextField heureFin;
    @FXML
    private TextField salle;
    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private Button submit;
    @FXML
    private ImageView imghome;
    private static Matcher matcher;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();

    }

    private void loadData() {
        list.removeAll(list);
        String type1 = "Activite";
        String type2 = "Cours";
        list.addAll(type1, type2);
        type.getItems().addAll(list);
    }

//    public boolean isInt(String text) {
//        
//        try{
//        int x=Integer.parseInt(text);
//        return true;
//        }catch(NumberFormatException e){
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Saisie Erroner");
//        alert.setContentText("Veillez verifier ce que vous avez saisie");
//        alert.show();
//            return false;
//        }
//    }
    private int nbrPoint(String ch) {
        int s = 0;
        char c = '.';
        for (int i = 0; i < ch.length(); i++) {
            if (ch.charAt(i) == c) {
                s++;
            }
        }
        return s;
    }

  public boolean isDate(String ch) {
        boolean ok=true;
        if ((ch.length() != 10) | (nbrPoint(ch) != 2)) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saisie Erroner");
            alert.setContentText("Veillez saisir une date: dd.mm.yyyy");
            alert.showAndWait();
            ok=false;
        }
        else{
            String c = ".";
		ch = ch.replace(c, "");
		 try {
            int x = Integer.parseInt(ch);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saisie Erroner");
            alert.setContentText("Veillez saisir une date: dd.mm.yyyy");
            alert.showAndWait();
            ok=false; 
        }
    
        }
    return ok;
    }

    @FXML
    private void btn(ActionEvent event) throws SQLException, IOException {
        boolean ok = true;
        Parent root = FXMLLoader.load(getClass().getResource("/fxmljardin/Ajouter.fxml"));
        String n = nom.getText();
        String d = date.getText();
        ok = isDate(d);
        String hd = heureDebut.getText();
        try {
            int x = Integer.parseInt(hd);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Saisie Erroner");
            alert.setContentText("Veillez saisir un entier");
            alert.show();
        }
        int hdi = Integer.parseInt(hd);
        String hf = heureFin.getText();
        try {
            int x = Integer.parseInt(hf);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Saisie Erroner");
            alert.setContentText("Veillez Saisir un Entier");
            alert.show();
        }
        int hfi = Integer.parseInt(hf);
        String s = salle.getText();
        String t = type.getValue();
        if (ok == true) {
            CoursActivite ca = new CoursActivite(0, n, d, hdi, hfi, s, t, 0);
            ServiceCoursActivite sca = new ServiceCoursActivite();
            sca.ajouter(ca);
            Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
            nom.setText("");
            date.setText("");
            heureDebut.setText("");
            heureFin.setText("");
            salle.setText("");
        }
    }

    @FXML
    private void gohome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CoursActivite.fxml"));
            Parent root = loader.load();
            CoursActiviteController cac = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
