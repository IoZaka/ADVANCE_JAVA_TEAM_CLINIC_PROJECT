/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Tasos
 */
public class AdminParametricsController extends NewStage implements Initializable {

    private ResultSet rs;
    User user = User.getInstance();
    @FXML
    private Button pm_roles;
    @FXML
    private Button pm_insurance_companies;
    @FXML
    private Button pm_genders;
    @FXML
    private Button pm_nationalities;
    @FXML
    private Button pm_eco_status;
    @FXML
    private Button pm_members;
    @FXML
    private AnchorPane AdminParametricsPanel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pm_roles.setOnMouseClicked((MouseEvent event) -> {
            goToNextPage("pm_roles",pm_roles.getText());
   
        });
        pm_insurance_companies.setOnMouseClicked((MouseEvent event) -> {
            goToNextPage("pm_insurance_companies",pm_insurance_companies.getText());
        });
        pm_genders.setOnMouseClicked((MouseEvent event) -> {
            goToNextPage("pm_genders",pm_genders.getText());
        });
        pm_nationalities.setOnMouseClicked((MouseEvent event) -> {
            goToNextPage("pm_nationalities",pm_nationalities.getText());
        });
        pm_eco_status.setOnMouseClicked((MouseEvent event) -> {
            goToNextPage("pm_eco_status",pm_eco_status.getText());
        });
        pm_members.setOnMouseClicked((MouseEvent event) -> {
            goToNextPage("pm_members",pm_members.getText());
        });

    }


    private void goToNextPage(String tableName, String lName) {
        try {
            FXMLLoader loader = new FXMLLoader(AdminParametricsController.this.getClass().getResource("../View/AdminViewParameters.fxml"));
            Parent root = (Parent) loader.load();
            AdminViewParametersController newWindow = loader.getController();
            newWindow.setWindow(tableName);
            AdminParametricsPanel.getChildren().clear();
            AdminParametricsPanel.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(AdminParametricsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
