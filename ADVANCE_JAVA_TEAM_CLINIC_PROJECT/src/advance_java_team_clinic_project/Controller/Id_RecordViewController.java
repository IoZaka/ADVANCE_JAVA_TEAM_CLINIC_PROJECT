/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.LoggedInUser;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

    private DatabaseLoginRecords ak;
    private ResultSet rs;
    private Integer id;
    @FXML
    private Button backBtn;
    @FXML
    private Button diagnoseInfoBtn;
    @FXML
    private AnchorPane idRecordPane;

    LoggedInUser user = LoggedInUser.getInstance();
    @FXML
    private TextField doctorInput;
    @FXML
    private TextField patientInput;
    private Integer diagID = -1;

    private Statement stmt;
    private String sql;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appDateInput.setEditable(false);
        appCodeInput.setEditable(false);
        createdInput.setEditable(false);
        commentsTextArea.setEditable(false);
        doctorInput.setEditable(false);
        patientInput.setEditable(false);

        backBtn.setOnMouseClicked((MouseEvent event) -> {
            FXMLLoader loader = new FXMLLoader(Id_RecordViewController.this.getClass().getResource("../View/patientsRecords.fxml"));
            Parent root = null;
            try {
                root = (Parent) loader.load();
            } catch (IOException ex) {
                Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setID(String appID) {

        try {
            ak = new DatabaseLoginRecords();
            ak.getObject();
            rs = ak.fetchBasicInfoData(Integer.parseInt(appID));
            if (rs.next()) {
                doctorInput.setText(rs.getString("doctor"));
                patientInput.setText(rs.getString("patient"));
                appDateInput.setText(rs.getString("app_date"));
                appCodeInput.setText(rs.getString("app_code"));
                createdInput.setText(rs.getString("created"));
                commentsTextArea.setText(rs.getString("comments"));
                diagID = rs.getInt("diagnosis");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (diagID == -1) {
            if (user.getRoleID() == 3) {
                diagnoseInfoBtn.setVisible(false);
            } else {
                if (user.getRoleID() != 3) {
                    diagnoseInfoBtn.setVisible(true);
                    diagnoseInfoBtn.setText("Create diagnose");
                }
            }
        } else {
            diagnoseInfoBtn.setVisible(true);
            diagnoseInfoBtn.setText("Diagnose Info");
        }

        diagnoseInfoBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(Id_RecordViewController.this.getClass().getResource("../View/DiagnosisInfoView.fxml"));
                Parent root = (Parent) loader.load();
                DiagnosisInfoController diagnosisID = loader.getController();
                diagnosisID.setDiagnosisID(appID, diagID);
                idRecordPane.getChildren().clear();
                idRecordPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


    }

    public Integer getID() {
        return this.id;
    }

}
