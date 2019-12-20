/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.CustomCombo;
import advance_java_team_clinic_project.Model.DatabaseLoginRegister;
import advance_java_team_clinic_project.Model.DatabaseProfileDetails;
import advance_java_team_clinic_project.Model.DatabaseProfileEdit;
import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class EditProfileController implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private ImageView detailsBtn;
    @FXML
    private ImageView logoutBtnIcon;
    
    @FXML
    private AnchorPane editProfilePane;
    
    private static DatabaseProfileDetails ak;
    private ResultSet rs;
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

    private static DatabaseProfileEdit ed;
    @FXML
    private ComboBox comboGender;
    @FXML
    private ComboBox comboEcoStatus;
    @FXML
    private ComboBox comboNationality;
    @FXML
    private ComboBox comboRole;
    ObservableList<CustomCombo> customCombo = FXCollections.observableArrayList();
    
    private Integer genderId,ecoStatusId,nationalityId,roleId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            User user = User.getInstance();
            ak = new DatabaseProfileDetails();
            ak.getObject();
            rs = ak.fetchBasicInfoData(user.getId());
            if (rs.next()) {
                usernamebtn.setText(rs.getString("username"));
                name.setText(rs.getString("name"));
                surname.setText(rs.getString("surname"));
                /*insurancebtn.setText(String.valueOf(rs.getInt("insurance_id")));*/
                code.setText(rs.getString("global_code"));
                amka.setText(rs.getString("amka"));
                ama.setText(rs.getString("ama"));
                fathersName.setText(rs.getString("fathers_name"));
                mothersName.setText(rs.getString("mothers_name"));
                /* dateOfBirth.setValue(LOCAL_DATE(rs.getDate("date_of_birth")));*/
                placeOfBirth.setText(rs.getString("place_of_birth"));
                profession.setText(rs.getString("profession"));
                genderId = rs.getInt("gender_id");
                ecoStatusId = rs.getInt("eco_status_id");
                nationalityId = rs.getInt("nationality_id");
                roleId = rs.getInt("role_id");
                System.out.println("genderId: "+genderId +" ecostatusid: "+ ecoStatusId+" nationalityid: "+ nationalityId + " roleId"+ roleId);
                
                setComboValues();
                setComboEventListeners();
                 
            }

            usernamebtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("../View/checkUsernameWindow.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Check Username");
                        stage.setScene(new Scene(root, 400, 200));
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
      /*  contactbtn.setOnAction((ActionEvent e) -> {
            try {
                handleEditAction(e);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
        
        
    }

    private void setComboValues() throws SQLException {
        ed = new DatabaseProfileEdit();
        ed.getObject();
        customCombo = ed.FetchData("PM_ROLES");
        comboRole.setItems(FXCollections.observableArrayList(customCombo));
        customCombo = ed.FetchData("PM_NATIONALITIES");
        comboNationality.setItems(FXCollections.observableArrayList(customCombo));
        customCombo = ed.FetchData("PM_ECO_STATUS");
        comboEcoStatus.setItems(FXCollections.observableArrayList(customCombo));
        customCombo = ed.FetchData("PM_GENDERS");
        comboGender.setItems(FXCollections.observableArrayList(customCombo));
        
        //comboRole.getSelectionModel().select(roleId);

    }

    private void setComboEventListeners() {
        comboRole.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coRole = (CustomCombo) comboRole.getSelectionModel().getSelectedItem();
                System.out.println(coRole.getId() + coRole.getDescription());
            }
        });
        comboNationality.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coNationality = (CustomCombo) comboNationality.getSelectionModel().getSelectedItem();
                System.out.println(coNationality.getId() + coNationality.getDescription());
            }
        });
        comboEcoStatus.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coEcoStatus = (CustomCombo) comboEcoStatus.getSelectionModel().getSelectedItem();
                System.out.println(coEcoStatus.getId() + coEcoStatus.getDescription());
            }
        });
        comboGender.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coGender = (CustomCombo) comboGender.getSelectionModel().getSelectedItem();
                System.out.println(coGender.getId() + coGender.getDescription());
            }
        });
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
        
    }
     private void handleEditAction(ActionEvent event) throws IOException, SQLException {

    }




}
