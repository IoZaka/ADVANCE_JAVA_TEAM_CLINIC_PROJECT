/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Beast
 */

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnection object = DatabaseConnection.getInstance();
        
        Statement stmt = object.connection.createStatement();
        String sql = "select a.username as \"user\",a.password pass,b.description role,c.name||' ' || c.surname created_by,d.name||' ' || d.surname updated_by from pm_users a, pm_roles b, pm_users c, pm_users d where a.created_by = c.id and a.updated_by = d.id and a.role_id = b.id";
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
        String username = rs.getString("user");
        String password = rs.getString("pass");
        String role = rs.getString("role");
        String created = rs.getString("created_by");
        String updated = rs.getString("updated_by");
        
        System.out.println("User: " + username );
        System.out.println("Password: "+ password);   
        System.out.println("Role: " + role);
        System.out.println("Created: " + created);
        System.out.println("Updated: " + updated);
        }
        rs.close();
    }

            
    }

