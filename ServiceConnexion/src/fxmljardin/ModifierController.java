/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmljardin;

import com.esprit.Entite.CoursActivite;
import com.esprit.Service.ServiceCoursActivite;
import com.esprit.Service.ServiceCoursActiviteMetier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Formatik
 */
public class ModifierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbid;
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

    public void initierCour(CoursActivite ca) {
        String sid = Integer.toString(ca.getId_cours());
        lbid.setText(sid);
        nom.setText(ca.getNom());
        date.setText(ca.getDate());
        String shd = Integer.toString(ca.getHeureDebut());
        heureDebut.setText(shd);
        String shf = Integer.toString(ca.getHeureFin());
        heureFin.setText(shf);
        salle.setText(ca.getSalle());

    }

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
        CoursActivite car;
        boolean ok = true;
        ServiceCoursActivite sca = new ServiceCoursActivite();
        ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
        int id = Integer.parseInt(lbid.getText());
        car = scam.rechercheId(id);
        car.setNom(nom.getText());
        car.setDate(date.getText());
        ok = isDate(date.getText());
        String hd = heureDebut.getText();
        try {
            int x = Integer.parseInt(hd);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saisie Erroner");
            alert.setContentText("Veillez saisir un entier");
            alert.show();
        }
        int hdi = Integer.parseInt(hd);
        car.setHeureDebut(hdi);
        String hf = heureFin.getText();
        try {
            int x = Integer.parseInt(hf);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saisie Erroner");
            alert.setContentText("Veillez saisir un entier");
            alert.show();
        }
        int hfi = Integer.parseInt(hf);
        car.setHeureFin(hfi);
        car.setSalle(salle.getText());
        car.setType(type.getValue());
        if (ok == true) {
            sca.update(car);
            Parent root = FXMLLoader.load(getClass().getResource("/fxmljardin/ModifierTerminer.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerModifier.fxml"));
            Parent root = loader.load();
            SupprimerModifierController cac = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
