/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.IService;
import com.esprit.Entite.CoursActivite;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Formatik
 */

public interface IServiceMetriers<CoursActivite> {
    CoursActivite rechercheCoursNom(String cours) throws SQLException;
    CoursActivite rechercheActiviteNom(String activite) throws SQLException;
    List<CoursActivite> coursJourJ(String date) throws SQLException;
    List<CoursActivite> activiteJourJ(String date) throws SQLException;
    void salleLibre(String salle) throws SQLException;
    List<CoursActivite> listCours() throws SQLException;
    List<CoursActivite> listActivite() throws SQLException;
    CoursActivite rechercheId(int id) throws SQLException;

}
