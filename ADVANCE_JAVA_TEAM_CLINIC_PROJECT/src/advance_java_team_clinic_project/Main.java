/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Beast
 */
public class Main {
    
    public static void main(String[] args) {
        Main pin = new Main();
    }
        public Main(){
            
        
        
            try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@25.57.202.173:1521:orcl", "javadev", "javadev")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }
}
