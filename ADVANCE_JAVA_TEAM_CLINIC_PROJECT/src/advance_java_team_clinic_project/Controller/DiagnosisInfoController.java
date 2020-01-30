/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DiagnosisInfo;
import advance_java_team_clinic_project.Model.LoggedInUser;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DiagnosisInfoController implements Initializable {

    @FXML
    private TextField createdByInput;
    @FXML
    private TextField updatedByInput;
    @FXML
    private Button backBtn;
    @FXML
    private Button admissionInfoBtn;
    @FXML
    private TextArea medicineText;
    @FXML
    private Button testsBtn;
    @FXML
    private TextArea commentsText;
    @FXML
    private TextField doctorInput;
    @FXML
    private TextField patientInput;
    @FXML
    private Button create_addmission;
    @FXML
    private Button createDiagnose;
    @FXML
    private Button updateDiagnose;
    @FXML
    private TextField createdInput;
    @FXML
    private TextField updatedInput;
    @FXML
    private TextField patientTypeInput;

    private Integer appInfoId, admissionId;
    LoggedInUser user = LoggedInUser.getInstance();
    @FXML
    private AnchorPane diagnosisPanel;

    private DiagnosisInfo diagInfoModel = new DiagnosisInfo();
    ;
    private ResultSet rs;
    @FXML
    private Button testCreate;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createdByInput.setEditable(false);
        updatedByInput.setEditable(false);
        doctorInput.setEditable(false);
        patientInput.setEditable(false);
        createdInput.setEditable(false);
        updatedInput.setEditable(false);
        patientTypeInput.setEditable(false);
        if (user.getRoleID() == 3) {
            medicineText.setEditable(false);
            commentsText.setEditable(false);
            create_addmission.setVisible(false);
            createDiagnose.setVisible(false);
            updateDiagnose.setVisible(false);
            createDiagnose.setVisible(false);
        }
    }

    /**
     *
     * @param id
     * @param diagID
     */
    public void setDiagnosisID(String id, Integer diagID) {
        appInfoId = Integer.valueOf(id);
        admissionId = -1;
//        diagInfoModel.getObject();

        if (diagID == -1) {
            testsBtn.setVisible(false);
            updateDiagnose.setVisible(false);
            create_addmission.setVisible(false);
            admissionInfoBtn.setVisible(false);
            testCreate.setVisible(false);
            if (user.getRoleID() != 3) {
                createDiagnose.setVisible(true);
            }
        } else {
            diagInfoModel.getObject();
            rs = diagInfoModel.fetchDiagnoseInfoData(diagID);
            try {
                while (rs.next()) {
                    commentsText.setText(rs.getString("comments"));
                    medicineText.setText(rs.getString("meds"));
                    patientTypeInput.setText(rs.getString("patient_type"));
                    createdInput.setText(rs.getString("created"));
                    createdByInput.setText(rs.getString("created_by"));
                    updatedInput.setText(rs.getString("updated"));
                    updatedByInput.setText(rs.getString("updated_by"));
                    admissionId = rs.getInt("admission_id");
                    doctorInput.setText(rs.getString("doctor"));
                    patientInput.setText(rs.getString("patient"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DiagnosisInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (user.getRoleID() != 3) {
                updateDiagnose.setVisible(true);
                testsBtn.setVisible(true);
                createDiagnose.setVisible(false);
                testCreate.setVisible(true);
            }
        }
        backBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(DiagnosisInfoController.this.getClass().getResource("../View/id_RecordView.fxml"));
                Parent root = (Parent) loader.load();
                Id_RecordViewController idRecord = loader.getController();
                idRecord.setID(id);
                diagnosisPanel.getChildren().clear();
                diagnosisPanel.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        updateDiagnose.setOnMouseClicked((MouseEvent event) -> {
            diagInfoModel.getObject();
            diagInfoModel.updateDiagnoseDetails(diagID, commentsText.getText(), medicineText.getText());
        });
        
        admissionInfoBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(DiagnosisInfoController.this.getClass().getResource("../View/AdmissionInfoView.fxml"));
                Parent root = (Parent) loader.load();
                AdmissionInfoController admissionInfoID = loader.getController();
                admissionInfoID.setAdmissionID(diagID);
                diagnosisPanel.getChildren().clear();
                diagnosisPanel.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        create_addmission.setOnMouseClicked((MouseEvent event) -> {
            diagInfoModel.getObject();
            diagInfoModel.createDiagnoseDetails(appInfoId, commentsText.getText(), medicineText.getText());
        });
        
        /* TESTS CONTEXT */
        testCreate.setOnMouseClicked((MouseEvent event) -> {
            FXMLLoader loader = new FXMLLoader(DiagnosisInfoController.this.getClass().getResource("../View/testIDView.fxml"));
            Parent root = null;
            try {
                root = (Parent) loader.load();
            } catch (IOException ex) {
                Logger.getLogger(DiagnosisInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestIDController testID = loader.getController();
            testID.setTestIDView(id, -1);
            //Scene
            diagnosisPanel.getChildren().clear();
            diagnosisPanel.getChildren().add(root);
        });
        testsBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(DiagnosisInfoController.this.getClass().getResource("../View/testsTableView.fxml"));
                Parent root = (Parent) loader.load();
                TestsTableViewController testID = loader.getController();
                testID.setTestID(id, diagID);
                diagnosisPanel.getChildren().clear();
                diagnosisPanel.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /* END -- TESTS CONTEXT */
    }
}
