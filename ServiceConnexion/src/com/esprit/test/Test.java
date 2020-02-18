/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.Entite.CoursActivite;
import com.esprit.Service.ServiceCoursActivite;
import com.esprit.Service.ServiceCoursActiviteMetier;
import com.esprit.Utils.DataBase;
import fxmljardin.AjouterCoursActiviteController;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javafx.scene.control.Alert;

/**
 *
 * @author House
 */
public class Test {
    
    public static void main(String[] args) {
        ServiceCoursActivite ser = new ServiceCoursActivite();
        ServiceCoursActiviteMetier serm=new ServiceCoursActiviteMetier();
        
        CoursActivite ca = new CoursActivite(0, "mahdi","12/02/1997", 15, 18, "E205", "Cours", 1);
        CoursActivite ca1 = new CoursActivite(0, "nader","22/02/1997", 55, 88, "E208", "activite", 8);
        CoursActivite ca2 = new CoursActivite(0, "amine","12/02/1992", 22, 00, "E206", "Cours", 3);
        CoursActivite ca3 = new CoursActivite(0, "zizouuuu","12/05/1997", 11, 15, "ISG", "activite", 5);
        CoursActivite ca4 = new CoursActivite(0, "mahdi","12/05/1997", 11, 15, "ISG", "activite", 5);
        CoursActivite ca5 = new CoursActivite(94, "upda","12/05/1997", 11, 15, "ISG", "activite", 5);
        
        
       // try {
          // ser.ajouter(ca);
         // ser.delete(6);
         // ser.update(ca5);
           // System.out.println(serm.rechercheCoursNom("zizouuuu"));
            //System.out.println(serm.rechercheActiviteNom("zizouuuu"));
           // List<CoursActivite> list = ser.readAll();
           // System.out.println(list);
            //System.out.println(serm.activiteJourJ("12/05/1997"));
            //System.out.println(serm.coursJourJ("12/02/1997"));
           // serm.salleLibre("E207");
           // System.out.println(serm.rechercheId(5));
           
           String ch = "12.22.1265";
		String c = ".";
		ch = ch.replace(c, "");
		System.out.println(ch);
//       } catch (SQLException ex) {
//            System.out.println(ex);
//        }

       

        
    }
    
}
