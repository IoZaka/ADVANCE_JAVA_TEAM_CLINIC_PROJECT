/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Tests {
    
    User user = User.getInstance();
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private DatabaseConnection object;
    
    private StringProperty id = new SimpleStringProperty();
    private StringProperty diag_id = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty is_completed = new SimpleStringProperty();
    private StringProperty cost = new SimpleStringProperty();
    private StringProperty is_paid = new SimpleStringProperty();
    
    public StringProperty idProperty(){ return id;}
    public StringProperty diag_idProperty(){return diag_id;}
    public StringProperty descriptionProperty(){return description;}
    public StringProperty is_completedProperty(){return is_completed;}
    public StringProperty costProperty(){return cost;}
    public StringProperty is_paidProperty(){return is_paid;}
    
    public Tests(){}
    
    public ResultSet getTestByDiagID(Integer diagID){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select * from pm_diag_tests where diag_id= " + diagID;
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public ResultSet getTestByID(Integer testID){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select * from pm_diag_tests where id= " + testID;
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public ResultSet getAllTests(){
        try{
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select * from pm_diag_tests";
            rs = stmt.executeQuery(sql);
        }catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
        
}
