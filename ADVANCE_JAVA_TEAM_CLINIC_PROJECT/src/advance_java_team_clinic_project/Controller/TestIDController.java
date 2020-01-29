/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.Tests;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    
    private Integer diagID = null;

    Tests tests = new Tests();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    public void setTestIDView(String appID, Integer diagID ,Integer testID){
        
        ResultSet rs = tests.getTestByID(testID);
        
        try {
            if(rs.next()){
                //diagID = rs.getInt("diag_id");
                descriptionInput.setText(rs.getString("description"));
                costInput.setText(rs.getString("cost"));
                resultsInput.setText(rs.getString("results"));
                isPaidInput.setText(rs.getString("Paid"));
                caseStatusInput.setText(rs.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestIDController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(TestIDController.this.getClass().getResource("../View/testsTableView.fxml"));
                    Parent root = (Parent)loader.load();
                    TestsTableViewController testID = loader.getController();
                    testID.setTestID(appID,diagID);
                    testIDPane.getChildren().clear();
                    testIDPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });  
    }
    
    //FOR CLINIC CENTER
    public void setTestIDView(Integer testID){
        
        ResultSet rs = tests.getTestByID(testID);
        
        try {
            if(rs.next()){
                //diagID = rs.getInt("diag_id");
                descriptionInput.setText(rs.getString("description"));
                costInput.setText(rs.getString("cost"));
                resultsInput.setText(rs.getString("results"));
                isPaidInput.setText(rs.getString("Paid"));
                caseStatusInput.setText(rs.getString("status"));
                createdByInput.setText(rs.getString("createdby"));
                createdDate.setValue(LOCAL_DATE(rs.getString("created")));
                updatedDate.setValue(LOCAL_DATE(rs.getString("updated")));
                updatedByInput.setText(rs.getString("updated_by"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestIDController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(TestIDController.this.getClass().getResource("../View/testsTableView.fxml"));
                    Parent root = (Parent)loader.load();
                    TestsTableViewController testID = loader.getController();
                    testID.setTests();
                    testIDPane.getChildren().clear();
                    testIDPane.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });  
    }
    
    public static final LocalDate LOCAL_DATE(String dateString) { 
        DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder().appendPattern("uuuu-MM-dd HH:mm:ss.S").toFormatter();
        LocalDate ldt = LocalDate.parse(dateString, DATE_FORMAT);
        return ldt;
    }
    
    
    
}
