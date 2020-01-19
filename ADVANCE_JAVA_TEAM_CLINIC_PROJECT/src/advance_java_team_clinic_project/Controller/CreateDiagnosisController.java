/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CreateDiagnosisController implements Initializable {

    @FXML
    private AnchorPane diagnosisPanel;
    @FXML
    private ComboBox patientType;
    @FXML
    private Button backBtn;
    @FXML
    private TextArea medicineText;
    @FXML
    private Button createBtn;
    @FXML
    private TextArea commentsText;
    @FXML
    private TextField doctorInput;
    @FXML
    private TextField patientInput;

    //Database
    User user = User.getInstance();
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private DatabaseConnection object;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void getAPPID(String id){
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDiagnosisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        patientType.getItems().add("Inpatient");
        patientType.getItems().add("Outpatient");
       
        
        
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(CreateDiagnosisController.this.getClass().getResource("../View/id_RecordView.fxml"));
                    Parent root = (Parent)loader.load();
                    Id_RecordViewController idRecord = loader.getController();
                    idRecord.setID(id);
                    diagnosisPanel.getChildren().clear();
                    diagnosisPanel.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        createBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    sql = "insert into pm_diagnosis(app_info_id, created_by, updated_by) values"
                            + " ("+Integer.valueOf(id)+", "+user.getId()+", "+user.getId()+")";
                     rs = stmt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateDiagnosisController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }         
        });
        
    }
    
    
}
