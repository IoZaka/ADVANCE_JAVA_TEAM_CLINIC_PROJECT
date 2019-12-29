/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class PatientsRecordsController implements Initializable{
    
 @FXML
private TableView tableList;

 
 DatabaseLoginRecords  ak;
 private ResultSet rs;
 private ObservableList<ObservableList> data;  
 
public void initialize(URL location, ResourceBundle resources) {
     try {
         ak = new DatabaseLoginRecords();
         ak.getObject();
         rs = ak.fetchBasicInfoData();
         data = FXCollections.observableArrayList();
         if (rs.next()) {
                        int Id_Appointment = rs.getInt( "id" );                                
                        String App_date_Appointment  = rs.getString("app_date");
                        String Comments_Appointment  = rs.getString("comments");
                        String App_code_Appointment  = rs.getString("app_code"); 
                        String Created_Appointment  = rs.getString("created");   
                        String Updated_Appointment  = rs.getString("updated");                        
                        String Patient = rs.getString("patient");
                        String Doctor   = rs.getString("doctor");
                        String Updated_by   = rs.getString("updated_by");
                        String Created_by   = rs.getString("created_by");
                        
                        System.out.println(Doctor);
                        //ObservableList<String> row = FXCollections.observableArrayList();
                      //  for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                          //  row.add(Id_Appointment,App_date_Appointment);
                      //  }
                      //  data.add(row); 
                       // tableList.setItems(data);
             
             
         }    
     } catch (SQLException ex) {
         Logger.getLogger(PatientsRecordsController.class.getName()).log(Level.SEVERE, null, ex);
     }
}
}


