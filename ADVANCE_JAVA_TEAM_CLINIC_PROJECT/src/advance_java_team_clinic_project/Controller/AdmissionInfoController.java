/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.Admission;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class AdmissionInfoController implements Initializable {

    @FXML
    private DatePicker admissionDateInput;
    @FXML
    private DatePicker dischargeDateInput;
    @FXML
    private TextField roomInput;
    @FXML
    private TextField bedInput;
    @FXML
    private TextField costPerDayinput;
    @FXML
    private TextField totalCostInput;
    @FXML
    private TextField createdByInput;
    @FXML
    private TextField updatedByInput;
    @FXML
    private DatePicker createdDateInput;
    @FXML
    private DatePicker updatedDateInput;
    @FXML
    private AnchorPane admissionInfoPane;
    @FXML
    private Text doctorLayer;
    @FXML
    private Text PatientLayout;
    @FXML
    private TextField doctorsName;
    @FXML
    private TextField patientsName;

    private Admission admissionModel = new Admission();
    private ResultSet rs;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setAdmissionID(Integer diagID, Integer ID) {
        admissionModel.getObject();
        rs = admissionModel.fetchAdmissionData(ID);
        try {
            if(rs.next()){
                roomInput.setText(rs.getString("room"));
                bedInput.setText(rs.getString("bed"));
                costPerDayinput.setText(rs.getString("cost_per_day"));
                createdByInput.setText(rs.getString("created_by"));
                updatedByInput.setText(rs.getString("updated_by"));
                //System.out.println(rs.getString("discharge_date"));
                System.out.println(rs.getString("admission_date"));
                admissionDateInput.setValue(LOCAL_DATE(rs.getString("admission_date")));
                if(!rs.getString("discharge_date").equals("-1")){
                    dischargeDateInput.setValue(LOCAL_DATE(rs.getString("discharge_date")));
                }
                createdDateInput.setValue(LOCAL_DATE(rs.getString("created")));
                updatedDateInput.setValue(LOCAL_DATE(rs.getString("updated")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static final LocalDate LOCAL_DATE(String dateString) {
         
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate dateTime  = LocalDate.parse(dateString, formatter);
        return dateTime;
    }

}
