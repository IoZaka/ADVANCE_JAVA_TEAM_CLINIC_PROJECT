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
public class Parametrics {
    
    LoggedInUser user = LoggedInUser.getInstance();
    
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private DatabaseConnection object;
    
    private StringProperty id = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty created = new SimpleStringProperty();
    private StringProperty createdby = new SimpleStringProperty();
    private StringProperty updated = new SimpleStringProperty();
    private StringProperty updatedby = new SimpleStringProperty();
    
    public StringProperty descriptionProperty(){return description;}
    public StringProperty idProperty(){return id;}
    public StringProperty createdProperty(){return created;}
    public StringProperty createdbyProperty(){return createdby;}
    public StringProperty updatedProperty(){return updated;}
    public StringProperty updatedbyProperty(){return updatedby;}
    
    public ResultSet getDesc(String tableName){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select a.id, a.description, b.SURNAME || ' ' || b.FIRSTNAME createdBy, c.SURNAME || ' ' || c.FIRSTNAME updatedby, a.created,"
                    + " a.updated from "  + tableName + " a , pm_users b, pm_users c where a.created_by = b.id and a.updated_by = c.id";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void createComponent(String tableName, String desc){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "insert into " + tableName + " (description,created_by,updated_by) values ('" + desc + "',"+user.getId()+","+user.getId()+")";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void editComponent(String tableName, String desc, Integer id){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "update " + tableName + " set description = '" + desc + "' , updated_by = "+  user.getId() + "  where id = " + id;
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteComponent(String tableName, Integer id){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "delete from " + tableName  +" where id = " + id;
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Parametrics(){};
}
