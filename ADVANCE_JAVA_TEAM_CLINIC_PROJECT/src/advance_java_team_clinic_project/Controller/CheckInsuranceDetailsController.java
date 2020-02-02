/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import static advance_java_team_clinic_project.Controller.EditProfileController.LOCAL_DATE;
import advance_java_team_clinic_project.Model.CustomCombo;
import advance_java_team_clinic_project.Model.DatabaseInsuranceDetails;
import advance_java_team_clinic_project.Model.DatabaseCustomCombo;
import advance_java_team_clinic_project.Model.LoggedInUser;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CheckInsuranceDetailsController implements Initializable {

    @FXML
    private Button submitBtn;
    @FXML
    private DatePicker insuranceExpiredDate;
    @FXML
    private ComboBox european;
    @FXML
    private ComboBox ekas;
    @FXML
    private ComboBox Insurancec;
    @FXML
    private TextArea insureanceComments;
    private static final DatabaseCustomCombo ed = new DatabaseCustomCombo();
    private int euro = 0, eka = 0, euroc = 0, ekac = 0, insuId;
    private static DatabaseInsuranceDetails ak = new DatabaseInsuranceDetails();
    private ResultSet rs;
    LoggedInUser user = LoggedInUser.getInstance();
    ObservableList<CustomCombo> customCombo = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setData();
        } catch (SQLException ex) {
            Logger.getLogger(CheckAddressDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn.setOnMouseClicked((MouseEvent event) -> {
            euro = european.getSelectionModel().getSelectedItem().toString().compareTo("no"); //An einai oxi to euro ginete 0
            if (euro != 0) {
                euro = 1;
            }
            eka = ekas.getSelectionModel().getSelectedItem().toString().compareTo("no"); //An einai oxi to eka ginete 0
            if (eka != 0) {
                eka = 1;
            }
            ak.updateInsuranceDetails(user.getId(), insuranceExpiredDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), euro, eka, insureanceComments.getText(), insuId);
        });
    }

    private void setData() throws SQLException {
        ak.getObject();
        rs = ak.fetchInsuranceInfoData(user.getId());
        System.out.println("irthe");
        if (rs.next()) {
            insuId = rs.getInt("ins_comp_id");
            insuranceExpiredDate.setValue(LOCAL_DATE(rs.getString("ins_expire_date")));
            euroc = rs.getInt("european");
            european.getItems().addAll("yes", "no");
            ekas.getItems().addAll("yes", "no");
            if (euroc == 0) {
                european.setValue("no");
            } else {
                european.setValue("yes");
            }
            ekac = rs.getInt("ekas");
            if (ekac == 0) {
                ekas.setValue("no");
            } else {
                ekas.setValue("yes");
            }
            insureanceComments.setText(rs.getString("ins_comments"));

        }
        ed.getObject();
        customCombo = ed.FetchData("PM_INSURANCE_COMPANIES");
        Insurancec.setItems(FXCollections.observableArrayList(customCombo));
        InitiateComboList(insuId, Insurancec);
        setComboEventListeners();

    }

    private void InitiateComboList(Integer lId, ComboBox lComboBox) {
        customCombo.stream().filter((r) -> (r.getId() == lId)).forEachOrdered((r) -> {
            lComboBox.setValue(r.getDescription());
        });
    }

    private void setComboEventListeners() {
        Insurancec.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo Co_comp = (CustomCombo) Insurancec.getSelectionModel().getSelectedItem();
                insuId = Co_comp.getId();
            }
        });
    }
}
