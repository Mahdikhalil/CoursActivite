/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Classe;
import com.esprit.Entite.CoursActivite;
import com.esprit.Utils.SingletonConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Formatik
 */
public class ServiceClasse {

    private Connection con = SingletonConnexion.getCnx();
    private Statement ste;

    public ServiceClasse() {

    }

    public void ajouter(Classe ca) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `jardin`.`classe` ( `id_classe`,`nbrMax`,`nom`) VALUES (?, ?, ?);");
        pre.setInt(1, ca.getId_classe());
        pre.setInt(2, ca.getNbrMax());
        pre.setString(3, ca.getNom());
        pre.executeUpdate();
    }

    public boolean search(Classe ca) throws SQLException {

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from classe");
        boolean ok = false;
        while (rs.next() && (ok == false)) {
            if (rs.getInt(1) == ca.getId_classe()) {
                ok = true;
            }
        }
        return ok;
    }

    public ArrayList<Classe> getListC() throws SQLException {

        ArrayList<Classe> idc = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from jardin.classe");
        while (rs.next()) {
            int id_classe = rs.getInt(1);
            int nbrMax = rs.getInt(2);
            String nom = rs.getString(3);
            Classe p = new Classe(id_classe, nbrMax, nom);
            idc.add(p);
        }
        return idc;
    }

    public boolean update(Classe ca) throws SQLException {
        if (search(ca) == true) {
            PreparedStatement pre = con.prepareStatement("UPDATE `jardin`.`classe` SET `id_classe` = ?, `nbrMax` = ?,`nom` = ? WHERE `id_classe`=? ;");
            pre.setInt(1, ca.getId_classe());
            pre.setInt(2, ca.getNbrMax());
            pre.setString(3, ca.getNom());
            pre.setInt(4,ca.getId_classe());
            pre.executeUpdate();
            System.out.println("Classe Modifier");
            return true;
        } else {
            System.out.println("Classe n'existe pas");
            return true;
        }
    }

    public boolean delete(int id) throws SQLException {
        Classe ca = new Classe(id,0, null);
        if (search(ca) == true) {
            PreparedStatement pre0 = con.prepareStatement("DELETE FROM `jardin`.`enfant` WHERE `id_classe` = ? ");
            pre0.setInt(1, ca.getId_classe());
            pre0.executeUpdate();
            PreparedStatement pre1 = con.prepareStatement("DELETE FROM `jardin`.`classecoursactivite` WHERE `id_classe` = ? ");
            pre1.setInt(1, ca.getId_classe());
            pre1.executeUpdate();
            PreparedStatement pre = con.prepareStatement("DELETE FROM `jardin`.`classe` WHERE `id_classe` = ? ");
            pre.setInt(1, ca.getId_classe());
            pre.executeUpdate();
            System.out.println("Classe Supprimer");
            return true;
        } else {
            System.out.println("Classe n'existe pas");
            return true;
        }

    }

    public Classe searchById(int id) throws SQLException {
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from classe");
    while (rs.next()) {         
         if (rs.getInt(1)==id)
             break; 
     }
        int idc = rs.getInt(1);
        String nom = rs.getString(3);
        int nbrMax = rs.getInt(2);
        Classe ca=new Classe(idc, nbrMax, nom);
        return ca;
        
    }
    }


