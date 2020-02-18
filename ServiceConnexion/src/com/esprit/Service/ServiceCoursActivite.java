/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;
import com.esprit.Entite.CoursActivite;
import com.esprit.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.esprit.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Formatik
 */
public class ServiceCoursActivite {
    private Connection con;
    private Statement ste;

    public ServiceCoursActivite() {
        con = DataBase.getInstance().getConnection();

    }
    public void ajouter(CoursActivite ca) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `jardin`.`coursactivite` ( `id_cours`,`nom`,`date`,`heuredebut`,`heurefin`,`salle`,`type`,`id_user`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);");
    pre.setInt(1,ca.getId_cours());
    pre.setString(2,ca.getNom()); 
    pre.setString(3,ca.getDate());
    pre.setInt(4,ca.getHeureDebut());
    pre.setInt(5,ca.getHeureFin());
    pre.setString(6,ca.getSalle());
    pre.setString(7,ca.getType());
    pre.setInt(8,ca.getId_user());
    pre.executeUpdate();
    }

    public boolean search(CoursActivite ca) throws SQLException {
    
        ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from coursactivite");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==ca.getId_cours())
             ok=true;
     }
     return ok;
    }

    public boolean update(CoursActivite ca) throws SQLException {
        if (search(ca)==true){
        PreparedStatement pre=con.prepareStatement("UPDATE `jardin`.`coursactivite` SET `id_cours` = ?, `nom` = ?,`date` = ?,`heuredebut` = ?,`heurefin` = ?,`salle` = ?,`type` = ?,`id_user` = ? WHERE `id_cours`=? ;");
        pre.setInt(1,ca.getId_cours());
        pre.setString(2,ca.getNom()); 
        pre.setString(3,ca.getDate());
        pre.setInt(4,ca.getHeureDebut());
        pre.setInt(5,ca.getHeureFin());
        pre.setString(6,ca.getSalle());
        pre.setString(7,ca.getType());
        pre.setInt(8,ca.getId_user());
        pre.setInt(9,ca.getId_cours());
        pre.executeUpdate();
        System.out.println("Utilisateur Modifier");
        return true;}
        else{
           System.out.println("L'utulisateur n'existe pas");
           return true;
        }
    }
    
    public boolean delete(int id) throws SQLException {
        CoursActivite ca=new CoursActivite(id, null, null, 0, 0, null, null, 0);
        if (search(ca)==true){
        PreparedStatement pre=con.prepareStatement("DELETE FROM `jardin`.`coursactivite` WHERE `id_cours` = ? ");
        pre.setInt(1,ca.getId_cours());
        pre.executeUpdate();
           System.out.println("Utilisateur Supprimer");
        return true;}
        else{
           System.out.println("L'utulisateur n'existe pas");
           return true;
       }
       
    }
    
    public List<CoursActivite> readAll() throws SQLException {
    List<CoursActivite> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from coursactivite");
     while (rs.next()) {                
               int id_cours=rs.getInt(1);
               String nom=rs.getString(2);
               String date=rs.getString(3);
               int heuredebut=rs.getInt(4);
               int heurefin=rs.getInt(5);
               String salle=rs.getString(6);
               String type=rs.getString(7);
               int id_user=rs.getInt(8);
               CoursActivite p=new CoursActivite(id_cours,nom , date, heuredebut, heurefin, salle, type, id_user);
     arr.add(p);
     }
    return arr;
    }
}
