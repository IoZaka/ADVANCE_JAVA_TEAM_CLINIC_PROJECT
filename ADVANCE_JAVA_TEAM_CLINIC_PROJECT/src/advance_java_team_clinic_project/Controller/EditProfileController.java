/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.CustomCombo;
import advance_java_team_clinic_project.Model.DatabaseProfileDetails;
import advance_java_team_clinic_project.Model.DatabaseProfileEdit;
import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class EditProfileController extends NewStage implements Initializable {

    @FXML
    private AnchorPane editProfilePane;
    private static DatabaseProfileDetails ak = new DatabaseProfileDetails();
    private static final DatabaseProfileEdit ed = new DatabaseProfileEdit();
    private ResultSet rs;
    User user = User.getInstance();

    @FXML
    private Button usernamebtn;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private Button insurancebtn;
    @FXML
    private TextField code;
    @FXML
    private TextField amka;
    @FXML
    private TextField ama;
    @FXML
    private TextField fathersName;
    @FXML
    private TextField mothersName;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private TextField placeOfBirth;
    @FXML
    private TextField profession;
    @FXML
    private Button passwordbtn;
    @FXML
    private Button addressbtn;
    @FXML
    private Button contactbtn;
    @FXML
    private ComboBox comboRole;
    ObservableList<CustomCombo> customCombo = FXCollections.observableArrayList();
    private Integer genderId, ecoStatusId, nationalityId, roleId;
    @FXML
    private ComboBox comboGender;
    @FXML
    private ComboBox comboEcoStatus;
    @FXML
    private ComboBox comboNationality;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;

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
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        usernamebtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                Parent root = FXMLLoader.load(EditProfileController.this.getClass().getResource("../View/checkUsernameWindow.fxml"));
                Stage checkUsername = new Stage();
                Scene scene = new Scene(root);
                checkUsername.setTitle("Enter New Username");
                checkUsername.setScene(scene);
                checkUsername.setResizable(false);
                checkUsername.show();
            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        passwordbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("../View/checkPasswordWindow.fxml"));
                        Stage checkPassword = new Stage();
                        Scene scene = new Scene(root);
                        checkPassword.setTitle("Enter New Password");
                        checkPassword.setScene(scene);
                        checkPassword.setResizable(false);
                        checkPassword.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
        
        cancelBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        Stage currentStage = (Stage) editProfilePane.getScene().getWindow();
                        if(roleId == 1){
                            setNewStage("../View/AdminDashboard.fxml", currentStage);
                        }else if(roleId == 2){
                            setNewStage("../View/doctorsDashboard.fxml", currentStage);
                        }else{
                            setNewStage("../View/patientsDashboard.fxml", currentStage);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });

        submitBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                ak = new DatabaseProfileDetails();
                ak.getObject();
                ak.updateBasicInfoData(user.getId(), roleId, surname.getText(), name.getText(), amka.getText(), ama.getText(), dateOfBirth.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), fathersName.getText(), mothersName.getText(), genderId, ecoStatusId, nationalityId, profession.getText(), placeOfBirth.getText()/*, memberId*/);
                User user1 = User.getInstance();
                rs = ak.fetchBasicInfoData(user1.getId());
                if (rs.next()) {
                    code.setText(rs.getString("global_code"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
     
       contactbtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                        Parent root = FXMLLoader.load(getClass().getResource("../View/checkContactDetails.fxml"));
                        Stage checkContact = new Stage();
                        Scene scene = new Scene(root);
                        checkContact.setTitle("Contact Details");
                        checkContact.setScene(scene);
                        checkContact.setResizable(false);
                        checkContact.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
       
       insurancebtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                        Parent root = FXMLLoader.load(getClass().getResource("../View/checkInsuranceDetails.fxml"));
                        Stage checkInsurance = new Stage();
                        Scene scene = new Scene(root);
                        checkInsurance.setTitle("Insurance Details");
                        checkInsurance.setScene(scene);
                        checkInsurance.setResizable(false);
                        checkInsurance.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
       
       addressbtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                        Parent root = FXMLLoader.load(getClass().getResource("../View/checkAddressDetails.fxml"));
                        Stage checkAddress = new Stage();
                        Scene scene = new Scene(root);
                        checkAddress.setTitle("Address Details");
                        checkAddress.setScene(scene);
                        checkAddress.setResizable(false);
                        checkAddress.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });

    }
    /**
     * Returns the user's data from the database and shows it on the screen.
     * @throws SQLException 
     */
    private void setData() throws SQLException {
        ak.getObject();
        rs = ak.fetchBasicInfoData(user.getId());
        
        while(rs.next()){
            for(int i=1;i<rs.getMetaData().getColumnCount();i++){
               
            }
        }
        
        if (rs.next()) {
            if(rs.getString("username") != null){
                usernamebtn.setText(rs.getString("username"));
            }else{
                usernamebtn.setText(rs.getString("den iparxei username"));
            }
            
            name.setText(rs.getString("firstname"));
            surname.setText(rs.getString("surname"));
            /*insurancebtn.setText(String.valueOf(rs.getInt("insurance_id")));*/
            code.setText(rs.getString("global_code"));
            amka.setText(rs.getString("amka"));
            ama.setText(rs.getString("ama"));
            fathersName.setText(rs.getString("fathers_name"));
            mothersName.setText(rs.getString("mothers_name"));
            dateOfBirth.setValue(LOCAL_DATE(rs.getString("date_of_birth")));
            dateOfBirth.setPromptText("dd-MM-yyyy");
            System.out.println("auto mou epistrefei i vasi:" + rs.getString("date_of_birth"));
            System.out.println("auto mou epistrefei i function:" + LOCAL_DATE(rs.getString("date_of_birth")));
            profession.setText(rs.getString("profession"));
                genderId = rs.getInt("gender_id");
                if(genderId==null){
                    genderId = 0;
                }
            ecoStatusId = rs.getInt("eco_status_id");
            nationalityId = rs.getInt("nationality_id");
            roleId = rs.getInt("role_id");
            placeOfBirth.setText(rs.getString("place_of_birth"));
            setComboValues();
            setComboEventListeners();
        }
    }

    /**
     * Returns the ComboBox Values from the database
     * @throws SQLException 
     */
    private void setComboValues() throws SQLException {
        ed.getObject();

        customCombo = ed.FetchData("PM_ROLES");
        comboRole.setItems(FXCollections.observableArrayList(customCombo));
        InitiateComboList(roleId, comboRole);
        customCombo = ed.FetchData("PM_NATIONALITIES");
        comboNationality.setItems(FXCollections.observableArrayList(customCombo));
        InitiateComboList(nationalityId, comboNationality);
        customCombo = ed.FetchData("PM_ECO_STATUS");
        comboEcoStatus.setItems(FXCollections.observableArrayList(customCombo));
        InitiateComboList(ecoStatusId, comboEcoStatus);
        customCombo = ed.FetchData("PM_GENDERS");
        comboGender.setItems(FXCollections.observableArrayList(customCombo));
        InitiateComboList(genderId, comboGender);
    }

    private void InitiateComboList(Integer lId, ComboBox lComboBox) {
        customCombo.stream().filter((r) -> (r.getId() == lId)).forEachOrdered((r) -> {
            lComboBox.setValue(r.getDescription());
        });
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy", Locale.FRENCH);
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    /**
     * Set the ComboBox Event Listeners
     */
    private void setComboEventListeners() {
        comboRole.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coRole = (CustomCombo) comboRole.getSelectionModel().getSelectedItem();
                roleId = coRole.getId();
            }
        });
        comboNationality.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coNationality = (CustomCombo) comboNationality.getSelectionModel().getSelectedItem();
                nationalityId = coNationality.getId();
            }
        });
        comboEcoStatus.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coEcoStatus = (CustomCombo) comboEcoStatus.getSelectionModel().getSelectedItem();
                ecoStatusId = coEcoStatus.getId();
            }
        });
        comboGender.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coGender = (CustomCombo) comboGender.getSelectionModel().getSelectedItem();
                genderId = coGender.getId();
            }
        });
    }
}
