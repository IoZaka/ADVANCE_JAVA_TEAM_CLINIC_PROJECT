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
    private TextField createdDate;
    @FXML
    private TextField updatedDate;
    @FXML
    private Button backBtn;

    private Integer diagID = null;

    Tests tests = new Tests();
    @FXML
    private ComboBox isCompleted;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isCompleted.getItems().addAll("Yes","No");
    }

    public void setTestIDView(boolean fromDiag, Integer testID) {

        ResultSet rs = tests.getTestByID(testID);

        try {
            if(rs.next()){
                diagID = rs.getInt("diag_id");
                descriptionInput.setText(rs.getString("description"));
                costInput.setText(rs.getString("cost"));
                resultsInput.setText(rs.getString("results"));
                isPaidInput.setText(rs.getString("Paid"));
                caseStatusInput.setText(rs.getString("status"));
                isCompleted.setValue(rs.getString("is_completed"));
                createdByInput.setText(rs.getString("createdby"));
                updatedByInput.setText(rs.getString("updated_by"));
                createdDate.setText(rs.getString("created"));
                updatedDate.setText(rs.getString("updated"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestIDController.class.getName()).log(Level.SEVERE, null, ex);
        }

        backBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(TestIDController.this.getClass().getResource("../View/testsTableView.fxml"));
                Parent root = (Parent) loader.load();
                TestsTableViewController testID1 = loader.getController();
                if(fromDiag){
                    testID1.setTestID(diagID);
                }else{
                    testID1.setTestID(-1);
                }
                testIDPane.getChildren().clear();
                testIDPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(TestsTableViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }  
}
