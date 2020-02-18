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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Formatik
 */
public class SupprimerModifierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField id;
    @FXML
    private TableView<CoursActivite> lstCoursActivite;
    @FXML
    private ImageView imghome;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button rech;
    @FXML
    private Button mt;
    @FXML
    private TableColumn<CoursActivite, String> idc;
    @FXML
    private TableColumn<CoursActivite, String> nom;
    @FXML
    private TableColumn<CoursActivite, String> date;
    @FXML
    private TableColumn<CoursActivite, String> hd;
    @FXML
    private TableColumn<CoursActivite, String> hf;
    @FXML
    private TableColumn<CoursActivite, String> salle;
    @FXML
    private TableColumn<CoursActivite, String> type;
    @FXML
    private TableColumn<CoursActivite, String> idu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceCoursActivite sca = new ServiceCoursActivite();
            montrer();
            ObservableList<CoursActivite> data = FXCollections.observableArrayList(sca.readAll());
            lstCoursActivite.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void montrer() throws SQLException {
        try {
            ServiceCoursActivite sca = new ServiceCoursActivite();
            ObservableList<CoursActivite> data = FXCollections.observableArrayList(sca.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
            hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
            salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            lstCoursActivite.setItems(data);
            id.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gohome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CoursActivite.fxml"));
            Parent root = loader.load();
            CoursActiviteController dc = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
        try {
            int ids = Integer.parseInt(id.getText());
            ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
            ObservableList<CoursActivite> data = FXCollections.observableArrayList(scam.rechercheId(ids));
            lstCoursActivite.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, IOException {
        try {
            if (lstCoursActivite.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setContentText("Aucun element n'est selectionné");
                alert.showAndWait();
            }
            else{
            Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
            dialogC.setTitle("Confimation");
            dialogC.setHeaderText(null);
            dialogC.setContentText("voulez vous supprimer ?");
            Optional<ButtonType> answer = dialogC.showAndWait();
            if (answer.get() == ButtonType.OK) {
                CoursActivite ca = lstCoursActivite.getSelectionModel().getSelectedItem();
                ServiceCoursActivite sca = new ServiceCoursActivite();
                sca.delete(ca.getId_cours());
                ObservableList<CoursActivite> data = FXCollections.observableArrayList(sca.readAll());
                lstCoursActivite.setItems(data);
                Parent root = FXMLLoader.load(getClass().getResource("/fxmljardin/Supprimer.fxml"));
                Scene sc = new Scene(root);
                Stage st = new Stage();
                st.setScene(sc);
                st.show();
            }}
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Modif(ActionEvent event) throws SQLException, Exception {
        try {
            if (lstCoursActivite.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setContentText("Aucun element n'est selectionné");
                alert.showAndWait();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier.fxml"));
            Parent root = loader.load();
            ModifierController dcc = loader.getController();
            CoursActivite ca = lstCoursActivite.getSelectionModel().getSelectedItem();
            ServiceCoursActivite sca = new ServiceCoursActivite();
            int id = ca.getId_cours();
            dcc.initierCour(ca);
            modifier.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(CoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
