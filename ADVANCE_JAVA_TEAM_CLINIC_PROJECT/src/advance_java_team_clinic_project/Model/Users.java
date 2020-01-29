/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Chris
 */
public class Users {
    
    public Users(){}
    
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private DatabaseConnection object;
    
    private StringProperty id = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty role = new SimpleStringProperty();
    
    public StringProperty idProperty(){return id;}
    public StringProperty nameProperty(){return name;}
    public StringProperty roleProperty(){return role;}
    
    public ResultSet getUsers(String searchText){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            //sql = "select a.id, a.SURNAME || ' ' || a.FIRSTNAME uName, b.description role from pm_users a , pm_roles b where a.role_id = b.id";
            
            sql = "select a.id, a.SURNAME || ' ' || a.FIRSTNAME uName, b.description role from pm_users a , pm_roles b where a.id!=1 and a.role_id = b.id and "
                    + "(UPPER(a.firstname) like ( '%' || UPPER('"+searchText+"') || '%') or "
                    + "UPPER(a.SURNAME) like ( '%' || UPPER('"+searchText+"') || '%'))";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
}
