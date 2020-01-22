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
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Tasos
 */
public class AdminParametricsController {
    
    @FXML
    private AnchorPane AdminParametrics;
    private ResultSet rs;
    User user = User.getInstance();

    @FXML
    private Button btroles;
    @FXML
    private Button btnationalities;
    @FXML
    private Button btmembers;
    @FXML
    private Button btinsuranceCompanies;
    @FXML
    private Button btGenders;
    @FXML
    private Button btEcoStatus;

    public void initialize(URL url, ResourceBundle rb) {
//        usernamebtn.setOnMouseClicked((MouseEvent event) -> {
//
//            
//            
//            
//        });

}
}
