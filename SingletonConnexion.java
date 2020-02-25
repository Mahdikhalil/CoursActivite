package com.esprit.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {

    private static Connection cnx;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jardin", "root", "");

        } catch (ClassNotFoundException e) {
            System.err.print("probleme de driver" + e);
        } catch (SQLException e) {
            System.err.print("probleme de connection" + e);
        }
    }

    public static Connection getCnx() {

        return cnx;
    }

}
