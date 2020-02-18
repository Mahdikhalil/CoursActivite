/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.CoursActivite;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.esprit.Service.ServiceCoursActivite;
import com.esprit.IService.IService;
import java.sql.DriverManager;

/**
 *
 * @author Formatik
 */
public class ServiceCoursActiviteMetier {

    private Connection con;
    private Statement ste;

    public ServiceCoursActiviteMetier() {
        con = DataBase.getInstance().getConnection();
    }

    public CoursActivite rechercheCoursNom(String nom) throws SQLException {
        ServiceCoursActivite sca = new ServiceCoursActivite();
        List<CoursActivite> l = new ArrayList<>();
        l = sca.readAll();
        CoursActivite can = new CoursActivite();
        boolean ok = false;
        for (CoursActivite ca : l) {
            if ((ca.getNom().equals(nom)) && (ca.getType().equals("Cours"))) {
                can = ca;
            }
        }
        if (can.getId_cours() == 0) {
            System.out.println("il y'a pas de cours avec ce nom");
            return null;
        }
        return can;
    }

    public CoursActivite rechercheId(int id) throws SQLException {
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from coursactivite");
    while (rs.next()) {         
         if (rs.getInt(1)==id)
             break; 
     }
    int idc = rs.getInt(1);
        String nom = rs.getString(2);
        String date = rs.getString(3);
        int heuredebut = rs.getInt(4);
        int heurefin = rs.getInt(5);
        String salle = rs.getString(6);
        String type = rs.getString(7);
        int id_user = rs.getInt(8);
        CoursActivite ca=new CoursActivite(idc, nom, date, heuredebut, heurefin, salle, type, id_user);
        return ca;
        
    }

    public CoursActivite rechercheActiviteNom(String nom) throws SQLException {
        ServiceCoursActivite sca = new ServiceCoursActivite();
        List<CoursActivite> l = new ArrayList<>();
        l = sca.readAll();
        CoursActivite can = new CoursActivite();
        for (CoursActivite ca : l) {
            if ((ca.getNom().equals(nom)) && (ca.getType().equals("Activite"))) {
                can = ca;
            }
        }
        if (can.getId_cours() == 0) {
            System.out.println("il y'a pas d'activite avec ce nom");
            return null;
        }
        return can;
    }

    public List<CoursActivite> activiteJourJ(String date) throws SQLException {
        ServiceCoursActivite sca = new ServiceCoursActivite();
        List<CoursActivite> l = new ArrayList<>();
        List<CoursActivite> lf = new ArrayList<>();
        l = sca.readAll();
        for (CoursActivite ca : l) {
            if ((ca.getDate().equals(date)) && (ca.getType().equals("Activite"))) {
                lf.add(ca);
            }
        }
        return lf;
    }

    public List<CoursActivite> coursJourJ(String date) throws SQLException {
        ServiceCoursActivite sca = new ServiceCoursActivite();
        List<CoursActivite> l = new ArrayList<>();
        List<CoursActivite> lf = new ArrayList<>();
        l = sca.readAll();
        for (CoursActivite ca : l) {
            if ((ca.getDate().equals(date)) && (ca.getType().equals("Cours"))) {
                lf.add(ca);
            }
        }
        return lf;
    }

    public void salleLibre(String salle) throws SQLException {
        ServiceCoursActivite sca = new ServiceCoursActivite();
        List<CoursActivite> l = new ArrayList<>();
        l = sca.readAll();
        boolean ok = true;
        for (CoursActivite ca : l) {
            if (ca.getSalle().equals(salle)) {
                ok = false;
            }
        }
        if (ok == true) {
            System.out.println("la salle est libre");
        } else {
            System.out.println("la salle n'est pas libre");
        }

    }

    public List<CoursActivite> listCours() throws SQLException {
        List<CoursActivite> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from coursactivite where upper(type)='Cours'");
        while (rs.next()) {
            int id_cours = rs.getInt(1);
            String nom = rs.getString(2);
            String date = rs.getString(3);
            int heuredebut = rs.getInt(4);
            int heurefin = rs.getInt(5);
            String salle = rs.getString(6);
            String type = rs.getString(7);
            int id_user = rs.getInt(8);
            CoursActivite p = new CoursActivite(id_cours, nom, date, heuredebut, heurefin, salle, type, id_user);
            arr.add(p);
        }
        return arr;
    }

    public List<CoursActivite> listActivite() throws SQLException {
        List<CoursActivite> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from coursactivite where upper(type)='ACTIVITE'");
        while (rs.next()) {
            int id_cours = rs.getInt(1);
            String nom = rs.getString(2);
            String date = rs.getString(3);
            int heuredebut = rs.getInt(4);
            int heurefin = rs.getInt(5);
            String salle = rs.getString(6);
            String type = rs.getString(7);
            int id_user = rs.getInt(8);
            CoursActivite p = new CoursActivite(id_cours, nom, date, heuredebut, heurefin, salle, type, id_user);
            arr.add(p);
        }
        return arr;
    }
}
