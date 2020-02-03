/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.AppointmentsModel;
import advance_java_team_clinic_project.Model.CustomComboModel;
import advance_java_team_clinic_project.classes.CustomComboClass;
import advance_java_team_clinic_project.classes.LoggedInUserClass;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class AppointmentRecordInfoController implements Initializable {

    @FXML
    private TextField appDateInput;
    @FXML
    private TextField appCodeInput;
    @FXML
    private TextField createdInput;
    @FXML
    private TextArea commentsTextArea;

    private AppointmentsModel ak;
    private ResultSet rs;
    private Integer id;
    @FXML
    private Button backBtn;
    @FXML
    private Button diagnoseInfoBtn;
    @FXML
    private AnchorPane idRecordPane;

    LoggedInUserClass user = LoggedInUserClass.getInstance();
    private TextField doctorInput;
    @FXML
    private TextField patientInput;
    private Integer diagID = -1;

    private Statement stmt;
    private String sql;
    @FXML
    private ComboBox doctorComboBox;
    
    private CustomComboModel ed = new CustomComboModel();
    ObservableList<CustomComboClass> customCombo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(user.getRoleID() == 3){doctorComboBox.setDisable(true);}

        appDateInput.setEditable(false);
        appCodeInput.setEditable(false);
        createdInput.setEditable(false);
        commentsTextArea.setEditable(false);
        patientInput.setEditable(false);

        backBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(AppointmentRecordInfoController.this.getClass().getResource("../View/AppointmentRecords.fxml"));
                Parent root = (Parent) loader.load();
                idRecordPane.getChildren().clear();
                idRecordPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(AppointmentRecordInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    /**
     *
     * @param appID
     */
    public void setID(String appID) {

        try {
            ak = new AppointmentsModel();
            rs = ak.fetchBasicInfoData(Integer.parseInt(appID));
            ed.getObject();
            customCombo = ed.FetchUserFilterData(2);
            doctorComboBox.setItems(FXCollections.observableArrayList(customCombo));
            if (rs.next()) {
                doctorComboBox.setValue(rs.getString("doctor"));
                patientInput.setText(rs.getString("patient"));
                appDateInput.setText(rs.getString("app_date"));
                appCodeInput.setText(rs.getString("app_code"));
                createdInput.setText(rs.getString("created"));
                commentsTextArea.setText(rs.getString("comments"));
                diagID = rs.getInt("diagnosis");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentRecordInfoController.class.getName()).log(Level.SEVERE, null, ex);
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
                FXMLLoader loader = new FXMLLoader(AppointmentRecordInfoController.this.getClass().getResource("../View/DiagnosisInfoView.fxml"));
                Parent root = (Parent) loader.load();
                DiagnosisInfoController diagnosisID = loader.getController();
                diagnosisID.setDiagnosisID(appID, diagID);
                idRecordPane.getChildren().clear();
                idRecordPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(AppointmentRecordInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    /**
     *
     * @return
     */
    public Integer getID() {
        return this.id;
    }

}
