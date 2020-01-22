/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.Tests;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class TestIDController implements Initializable {

    @FXML
    private AnchorPane testIDPane;
    @FXML
    private TextField costInput;
    @FXML
    private TextField resultsInput;
    @FXML
    private TextField isPaidInput;
    @FXML
    private TextField caseStatusInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private TextField createdByInput;
    @FXML
    private TextField updatedByInput;
    @FXML
    private DatePicker createdDate;
    @FXML
    private DatePicker updatedDate;
    @FXML
    private Button backBtn;

    Tests tests = new Tests();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    public void setTestIDView(Integer testID){
        
        ResultSet rs = tests.getTestByID(testID);
        try {
            if(rs.next()){
                descriptionInput.setText(rs.getString("description"));
                costInput.setText(rs.getString("cost"));
                resultsInput.setText(rs.getString("results"));
                isPaidInput.setText(rs.getString("is_paid"));
                //caseStatusInput.setText(rs.getString("status"));
                createdByInput.setText(rs.getString("created_by"));
                updatedByInput.setText(rs.getString("updated_by"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestIDController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
