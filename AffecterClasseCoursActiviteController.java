/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmljardin;

import com.esprit.Entite.Classe;
import com.esprit.Entite.CoursActivite;
import com.esprit.Service.ServiceClasse;
import com.esprit.Service.ServiceClasseCoursActivite;
import com.esprit.Service.ServiceCoursActivite;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Formatik
 */
public class AffecterClasseCoursActiviteController implements Initializable {

    @FXML
    private TableView<CoursActivite> lstCoursActivite;
    @FXML
    private TableColumn<CoursActivite, String> nomCA;
    @FXML
    private TableView<Classe> lstClasse;
    @FXML
    private TableColumn<Classe, String> nomC;
    @FXML
    private TextField tfNomCA;
    @FXML
    private TextField tfNomC;
    @FXML
    private Button affecterbtn;
    @FXML
    private ImageView imghome;
    @FXML
    private TableColumn<CoursActivite, String> idCoursActivite;
    @FXML
    private TableColumn<Classe, String> idClasse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceClasse sc = new ServiceClasse();
        ServiceCoursActivite sca = new ServiceCoursActivite();
        try {
            ObservableList<Classe> datac = FXCollections.observableArrayList(sc.getListC());
            nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idClasse.setCellValueFactory(new PropertyValueFactory<>("id_classe"));
            lstClasse.setItems(datac);
            ObservableList<CoursActivite> dataca = FXCollections.observableArrayList(sca.readAll());
            nomCA.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idCoursActivite.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
            lstCoursActivite.setItems(dataca);
        } catch (SQLException ex) {
            Logger.getLogger(AffecterClasseCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    @FXML
    private void Affecter(ActionEvent event) throws SQLException {
        ServiceClasseCoursActivite scca = new ServiceClasseCoursActivite();
        int a=Integer.parseInt(tfNomC.getText());
        int b=Integer.parseInt(tfNomCA.getText());
        scca.ajouter(a,b);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Classe");
            alert.setContentText("Cours / Activite Ajouter à cette classe");
            alert.show();
    }

    @FXML
    private void gohome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Classe.fxml"));
            Parent root = loader.load();
            ClasseController dc = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AffecterClasseCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
