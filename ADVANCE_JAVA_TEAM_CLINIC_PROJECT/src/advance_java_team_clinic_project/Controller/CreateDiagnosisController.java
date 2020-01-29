/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseConnection;
import advance_java_team_clinic_project.Model.LoggedInUser;
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
    LoggedInUser user = LoggedInUser.getInstance();
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private DatabaseConnection object;
    @FXML
    private Button updateBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        patientType.getItems().add("Inpatient");
        patientType.getItems().add("Outpatient");

    }

    public void getAPPID(String id) {
        try {
            object = DatabaseConnection.getInstance();
            stmt = object.connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDiagnosisController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //An to app_info_id iparxei sto pm_diagnosis, tha emfanizei to updateBtn
        sql = "select * from pm_diagnosis where app_info_id= " + Integer.valueOf(id);
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                updateBtn.setVisible(true);
                createBtn.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateDiagnosisController.class.getName()).log(Level.SEVERE, null, ex);
        }

        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(CreateDiagnosisController.this.getClass().getResource("../View/id_RecordView.fxml"));
                    Parent root = (Parent) loader.load();
                    Id_RecordViewController idRecord = loader.getController();
                    idRecord.setID(id);
                    diagnosisPanel.getChildren().clear();
                    diagnosisPanel.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        createBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    sql = "insert into pm_diagnosis(app_info_id, created_by, updated_by) values"
                            + " (" + Integer.valueOf(id) + ", " + user.getId() + ", " + user.getId() + ")";
                    rs = stmt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateDiagnosisController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

}