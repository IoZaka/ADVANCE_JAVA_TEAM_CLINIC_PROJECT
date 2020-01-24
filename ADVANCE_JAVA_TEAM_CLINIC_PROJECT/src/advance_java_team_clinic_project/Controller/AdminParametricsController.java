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
    @FXML
    private AnchorPane AdminParametricsPanel;
    private String idButton;
    private AdminViewParametersController newWindow = new AdminViewParametersController();

    public String getIdButton() {
        return idButton;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btroles.setOnMouseClicked((MouseEvent event) -> {
            idButton = "pm_roles";
            goToNextPage(idButton);
        });

    }

    private void goToNextPage(String tableName) {
        try {
            newWindow.setWindow(tableName);
            FXMLLoader loader = new FXMLLoader(AdminParametricsController.this.getClass().getResource("../View/AdminViewParameters.fxml"));
            Parent root = (Parent) loader.load();
            AdminParametricsPanel.getChildren().clear();
            AdminParametricsPanel.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(AdminParametricsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
