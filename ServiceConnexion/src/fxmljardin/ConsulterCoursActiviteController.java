/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmljardin;

import com.esprit.Entite.CoursActivite;
import com.esprit.Service.ServiceCoursActivite;
import com.esprit.Service.ServiceCoursActiviteMetier;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TextElementArray;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Formatik
 */
public class ConsulterCoursActiviteController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private TextField nom;
    @FXML
     TableView<CoursActivite> lstCoursActivite;
    @FXML
    private ImageView imghome;
    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private Button sa;
    @FXML
    private Button btnrech1;
    @FXML
     TableColumn<CoursActivite, String> idc;
    @FXML
     TableColumn<CoursActivite, String> nom1;
    @FXML
     TableColumn<CoursActivite, String> date;
    @FXML
     TableColumn<CoursActivite, String> hd;
    @FXML
     TableColumn<CoursActivite, String> hf;
    @FXML
     TableColumn<CoursActivite, String> salle;
    @FXML
     TableColumn<CoursActivite, String> type1;
    @FXML
     TableColumn<CoursActivite, String> idu;
    @FXML
    private Button Imp;
    @FXML
    private Button qrView;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceCoursActivite sca = new ServiceCoursActivite();
            ObservableList<CoursActivite> data = FXCollections.observableArrayList(sca.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
            nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
            hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
            salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
            type1.setCellValueFactory(new PropertyValueFactory<>("type"));
            idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            lstCoursActivite.setItems(data);
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() {
        list.removeAll(list);
        String c1 = "Cours par nom";
        String c2 = "Activite par nom";
        String c3 = "Cours jour donnee";
        String c4 = "Activite jour donnee";
        String c5 = "List cours";
        String c6 = "List activite";
        String c7 = "Recherche par id";
        list.addAll(c1, c2, c3, c4, c5, c6, c7);
        type.getItems().addAll(list);
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
    private void btn(ActionEvent event) throws SQLException {
        try {
            ServiceCoursActivite sca = new ServiceCoursActivite();
            ObservableList<CoursActivite> data = FXCollections.observableArrayList(sca.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
            nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
            hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
            salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
            type1.setCellValueFactory(new PropertyValueFactory<>("type"));
            idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            lstCoursActivite.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
public CoursActivite envCA(){
        String nom =lstCoursActivite.getSelectionModel().getSelectedItem().getNom();
        String date =lstCoursActivite.getSelectionModel().getSelectedItem().getDate();
        String salle=lstCoursActivite.getSelectionModel().getSelectedItem().getSalle();
        String type=lstCoursActivite.getSelectionModel().getSelectedItem().getType();
        CoursActivite ca=new CoursActivite(0, nom, date, 0, 0, salle, type, 0);
        return ca;
}
    @FXML
    private void qrCode(ActionEvent event) throws SQLException{
       QrCodeController q=new QrCodeController();
       /* this.envCA();
         if (lstCoursActivite.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setContentText("Aucun element n'est selectionné");
                alert.showAndWait();
            }
            else{*/
        q.ini();
    }/*}*/
    @FXML
    private void rech1(ActionEvent event) throws SQLException {
        String s = type.getValue();
        String param = nom.getText();
        try {
            ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
            ServiceCoursActivite sca = new ServiceCoursActivite();
            ObservableList<CoursActivite> data = FXCollections.observableArrayList(sca.readAll());
            idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
            nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
            hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
            salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
            type1.setCellValueFactory(new PropertyValueFactory<>("type"));
            idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            lstCoursActivite.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (s) {
            case "Cours par nom":
                try {
                    lstCoursActivite.getSelectionModel().clearSelection();
                    ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
                    ObservableList<CoursActivite> data = FXCollections.observableArrayList(scam.rechercheCoursNom(param));

                    idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
                    nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
                    hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
                    salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
                    type1.setCellValueFactory(new PropertyValueFactory<>("type"));
                    idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
                    lstCoursActivite.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Recherche par id":
                try {
                    int id = Integer.parseInt(param);
                    ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
                    ObservableList<CoursActivite> data = FXCollections.observableArrayList(scam.rechercheId(id));
                    idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
                    nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
                    hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
                    salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
                    type1.setCellValueFactory(new PropertyValueFactory<>("type"));
                    idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
                    lstCoursActivite.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Activite par nom":
                try {
                    ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
                    ObservableList<CoursActivite> data = FXCollections.observableArrayList(scam.rechercheActiviteNom(param));
                    idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
                    nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
                    hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
                    salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
                    type1.setCellValueFactory(new PropertyValueFactory<>("type"));
                    idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
                    lstCoursActivite.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Cours jour donnee":
                try {
                    ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
                    ObservableList<CoursActivite> data = FXCollections.observableArrayList(scam.coursJourJ(param));
                    idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
                    nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
                    hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
                    salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
                    type1.setCellValueFactory(new PropertyValueFactory<>("type"));
                    idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
                    lstCoursActivite.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Activite jour donnee":
                try {
                    ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
                    ObservableList<CoursActivite> data = FXCollections.observableArrayList(scam.activiteJourJ(param));
                    idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
                    nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
                    hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
                    salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
                    type1.setCellValueFactory(new PropertyValueFactory<>("type"));
                    idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
                    lstCoursActivite.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "List cours":
                try {
                    ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
                    ObservableList<CoursActivite> data = FXCollections.observableArrayList(scam.listCours());
                    idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
                    nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
                    hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
                    salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
                    type1.setCellValueFactory(new PropertyValueFactory<>("type"));
                    idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
                    lstCoursActivite.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "List activite":
                try {
                    ServiceCoursActiviteMetier scam = new ServiceCoursActiviteMetier();
                    ObservableList<CoursActivite> data = FXCollections.observableArrayList(scam.listActivite());
                    idc.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
                    nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    hd.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
                    hf.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
                    salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
                    type1.setCellValueFactory(new PropertyValueFactory<>("type"));
                    idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
                    lstCoursActivite.setItems(data);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsulterCoursActiviteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }

 

    @FXML
    private void print(ActionEvent event) throws DocumentException, FileNotFoundException {
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("doc.pdf"));
        d.open();
        if(type.getValue()!=null)
        d.add(new Phrase(type.getValue()+"\n"));
        if(!(nom.getText().equals("")))
        d.add(new Phrase("Element chercher :" + nom.getText()+"\n"));
        for (int i = 0; i < lstCoursActivite.getItems().size(); i++) {
            CoursActivite a = new CoursActivite();
            a=lstCoursActivite.getItems().get(i);
            d.add(new Phrase("Id_Cous="+a.getId_cours()+" nom="+a.getNom()+" date="+a.getDate()+" heure_debut="+a.getHeureDebut()+" heure_fin="+a.getHeureFin()+" salle="+a.getSalle()+" type="+a.getType()+" user_id="+a.getId_user()+"\n"));
        }
        d.close();
    }

}
