/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author Formatik
 */
public class Classe {
    private int id_classe;
    private int nbrMax;
    private String nom;
    
    public Classe(){
       
    }

    public Classe(int id_classe, int nbrMax, String nom) {
        this.id_classe = id_classe;
        this.nbrMax = nbrMax;
        this.nom = nom;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
