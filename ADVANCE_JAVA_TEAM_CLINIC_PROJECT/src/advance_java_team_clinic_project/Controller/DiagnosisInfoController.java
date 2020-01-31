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
    private Button createDiagnose;
    @FXML
    private Button updateDiagnose;
    @FXML
    private TextField createdInput;
    @FXML
    private TextField updatedInput;
    @FXML
    private TextField patientTypeInput;

    private Integer appInfoId, admissionID;
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
            createDiagnose.setVisible(false);
            updateDiagnose.setVisible(false);
            createDiagnose.setVisible(false);
        }
    }

    /**
     *
     * @param app_id
     * @param diagID
     */
    public void setDiagnosisID(String app_id, Integer diagID) {
        appInfoId = Integer.valueOf(app_id);
        System.out.println("first step");
        admissionID = -1;
//        diagInfoModel.getObject();

        if (diagID == -1) {
            System.out.println("second step");
            testsBtn.setVisible(false);
            updateDiagnose.setVisible(false);
            admissionInfoBtn.setVisible(false);
            testCreate.setVisible(false);
            System.out.println("third step");
            if (user.getRoleID() != 3) {
                System.out.println("fourth step");
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
                    admissionID = rs.getInt("admission_id");
                    doctorInput.setText(rs.getString("doctor"));
                    patientInput.setText(rs.getString("patient"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DiagnosisInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (user.getRoleID() != 3) {
                if (admissionID == -1) {
                    admissionInfoBtn.setText("Create admission");
                } else if (admissionID > 0) {
                    admissionInfoBtn.setText("Admission info");
                }
                admissionInfoBtn.setVisible(true);
                updateDiagnose.setVisible(true);
                testsBtn.setVisible(true);
                testCreate.setVisible(true);
            } else if (user.getRoleID() == 3) {
                if (admissionID == -1) {
                    admissionInfoBtn.setVisible(false);
                } else if (admissionID > 0) {
                    admissionInfoBtn.setVisible(true);
                    admissionInfoBtn.setText("Admission info");
                }
            }
            createDiagnose.setVisible(false);
        }
        System.out.println("six step");
        backBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(DiagnosisInfoController.this.getClass().getResource("../View/id_RecordView.fxml"));
                Parent root = (Parent) loader.load();
                Id_RecordViewController idRecord = loader.getController();
                idRecord.setID(app_id);
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
                admissionInfoID.setAdmissionID(diagID, admissionID);
                diagnosisPanel.getChildren().clear();
                diagnosisPanel.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        createDiagnose.setOnMouseClicked((MouseEvent event) -> {
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
            testID.setTestIDView(app_id, -1);
            //Scene
            diagnosisPanel.getChildren().clear();
            diagnosisPanel.getChildren().add(root);
        });
        testsBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(DiagnosisInfoController.this.getClass().getResource("../View/testsTableView.fxml"));
                Parent root = (Parent) loader.load();
                TestsTableViewController testID = loader.getController();
                testID.setTestID(app_id, diagID);
                diagnosisPanel.getChildren().clear();
                diagnosisPanel.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(Id_RecordViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /* END -- TESTS CONTEXT */
    }
}
