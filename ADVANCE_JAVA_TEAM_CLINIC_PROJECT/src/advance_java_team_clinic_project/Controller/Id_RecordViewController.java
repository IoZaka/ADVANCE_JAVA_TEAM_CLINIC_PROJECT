/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.Records;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class Id_RecordViewController implements Initializable {

    
    @FXML
    private TextField appDateInput;
    @FXML
    private TextField appCodeInput;
    @FXML
    private TextField createdInput;
    @FXML
    private TextArea commentsTextArea;

    private DatabaseLoginRecords  ak;
    private ResultSet rs;
    private Integer id;
    @FXML
    private Button backBtn;
    @FXML
    private Button diagnoseInfoBtn;
    @FXML
    private AnchorPane idRecordPane;
    
    User user = User.getInstance();
    @FXML
    private TextField doctorInput;
    @FXML
    private TextField patientInput;
    @FXML
    private Button createDiagnosisBtn;
    
     
    private Statement stmt;
    private String sql;
    private DatabaseConnection object;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appDateInput.setEditable(false);
        appCodeInput.setEditable(false);
        createdInput.setEditable(false);
        commentsTextArea.setEditable(false);
        doctorInput.setEditable(false);
        patientInput.setEditable(false);
        
        switch(user.getRoleID()){
                    case 2:
                        createDiagnosisBtn.setVisible(true);
                        break;
                    case 3:
                        diagnoseInfoBtn.setVisible(true);
                        break;             
                }
        
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(Id_RecordViewController.this.getClass().getResource("../View/patientsRecords.fxml"));
                    Parent root = (Parent)loader.load();
                    idRecordPane.getChildren().clear();
                    idRecordPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }    
    
    public void setID(String id){
        
        this.id = Integer.valueOf(id);
         
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement(); 
            ak = new DatabaseLoginRecords();
            ak.getObject();
            rs = ak.fetchBasicInfoData(Integer.parseInt(id));
            if(rs.next()){
                doctorInput.setText(rs.getString("doctor"));
                patientInput.setText(rs.getString("patient"));
                appDateInput.setText(rs.getString("app_date"));
                appCodeInput.setText(rs.getString("app_code"));
                createdInput.setText(rs.getString("created"));
                commentsTextArea.setText(rs.getString("comments"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "select app_info_id from pm_diagnosis where app_info_id=" + Integer.valueOf(id);
        try {
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                diagnoseInfoBtn.setDisable(false);
                 diagnoseInfoBtn.setText("Diagnose Info");
            }else{
                diagnoseInfoBtn.setDisable(true);
                diagnoseInfoBtn.setText("No diagnose");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        diagnoseInfoBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(Id_RecordViewController.this.getClass().getResource("../View/DiagnosisInfoView.fxml"));
                    Parent root = (Parent)loader.load();    
                    DiagnosisInfoController diagnosisID = loader.getController();
                    diagnosisID.setDiagnosisID(id);
                    idRecordPane.getChildren().clear();
                    idRecordPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        createDiagnosisBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(Id_RecordViewController.this.getClass().getResource("../View/CreateDiagnosisView.fxml"));
                    Parent root = (Parent)loader.load();    
                    CreateDiagnosisController appID = loader.getController();
                    appID.getAPPID(id);
                    idRecordPane.getChildren().clear();
                    idRecordPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    public Integer getID(){
        return this.id;
    }
    
}
