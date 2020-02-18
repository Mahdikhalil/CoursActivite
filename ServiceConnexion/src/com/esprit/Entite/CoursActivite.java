/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.sql.Date;

/**
 *
 * @author Formatik
 */
public class CoursActivite {
    private int id_cours;
    private String nom;
    private String date;
    private int heureDebut;
    private int heureFin;
    private String salle;
    private String type;
    private int id_user;

    public CoursActivite() {
    }

    public CoursActivite(int id_cours, String nom, String date, int heureDebut, int heureFin, String salle, String type, int id_user) {
        this.id_cours = id_cours;
        this.nom = nom;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
        this.type = type;
        this.id_user = id_user;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "CoursActivite{" + "id_cours=" + id_cours + ", nom=" + nom + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", salle=" + salle + ", type=" + type + ", id_user=" + id_user + '}';
    }
    
    
}
