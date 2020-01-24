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
    
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private DatabaseConnection object;
    
    private StringProperty description = new SimpleStringProperty();
    public StringProperty descriptionProperty(){return description;}
    
    public ResultSet getDesc(String tableName){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
            sql = "select id,description from " + tableName;
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public Parametrics(){};
}
