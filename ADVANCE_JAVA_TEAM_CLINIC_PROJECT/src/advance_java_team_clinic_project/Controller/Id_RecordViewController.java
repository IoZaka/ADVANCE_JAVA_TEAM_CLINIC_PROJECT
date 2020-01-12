/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class Id_RecordViewController implements Initializable {

    
    @FXML
    private Label recordID;
    @FXML
    private TextField appDateInput;
    @FXML
    private TextField appCodeInput;
    @FXML
    private TextField createdInput;
    @FXML
    private TextField doctorInput;
    @FXML
    private TextArea commentsTextArea;

    private DatabaseLoginRecords  ak;
    private ResultSet rs;
    private Integer id;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appDateInput.setDisable(true);
        appCodeInput.setDisable(true);
        createdInput.setDisable(true);
        doctorInput.setDisable(true);
        commentsTextArea.setDisable(true);
        
        System.out.println(recordID.getText());
//        try {
//            ak = new DatabaseLoginRecords();
//            ak.getObject();
//            System.out.println(id);
//            rs = ak.fetchBasicInfoData(id);
//            if(rs.next()){
//                System.out.println(rs.getString("app_date"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        
    }    
    
    public void setLabelText(String id){
        recordID.setText(id);
    }
}
