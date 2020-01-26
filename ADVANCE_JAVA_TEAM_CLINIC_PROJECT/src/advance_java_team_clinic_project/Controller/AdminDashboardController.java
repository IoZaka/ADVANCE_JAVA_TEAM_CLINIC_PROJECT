/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.LoggedInUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class AdminDashboardController extends NewStage implements Initializable {

    @FXML
    private BorderPane adminPane;
    @FXML
    private ToggleButton profileBtn;
    @FXML
    private TextFlow usernamePane;
    @FXML
    private Text usernameText;
    @FXML
    private ToggleButton editProfileBtn;
    @FXML
    private ToggleButton enterNewUserBtn;
    @FXML
    private ToggleButton searchUserBtn;
    @FXML
    private ToggleButton logoutBtn;

    LoggedInUser user = LoggedInUser.getInstance();
    @FXML
    private ToggleButton parametricsBtn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUIonSamePane("../View/profilePane.fxml", adminPane);
        profileBtn.setSelected(true);
        usernameText.setText(usernameText.getText() + user.getUsername());
        usernamePane.setTextAlignment(TextAlignment.CENTER);

        profileBtn.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/profilePane.fxml", adminPane);
            clearSelectedButtons();
            profileBtn.setSelected(true);
        });

        parametricsBtn.setOnMouseClicked((MouseEvent event) -> {
            loadUIonSamePane("../View/AdminParametrics.fxml", adminPane);
            clearSelectedButtons();
            parametricsBtn.setSelected(true);
        });

        searchUserBtn.setOnMouseClicked((MouseEvent event) -> {
            clearSelectedButtons();
            searchUserBtn.setSelected(true);
            FXMLLoader loader = new FXMLLoader(AdminDashboardController.this.getClass().getResource("../View/searchUserView.fxml"));
            Parent root = null;
            try {
                root = (Parent)loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            SearchUserController searchController = loader.getController();
            searchController.setResults("");
            adminPane.setCenter(root);
            adminPane.setCenter(root);
        });
        
        editProfileBtn.setOnMouseClicked((MouseEvent event) -> {
            clearSelectedButtons();
            editProfileBtn.setSelected(true);
            FXMLLoader loader = new FXMLLoader(AdminDashboardController.this.getClass().getResource("../View/editProfile.fxml"));
            Parent root = null;
            try {
                root = (Parent)loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            EditProfileController editController = loader.getController();
            editController.myInit(user.getId());
            adminPane.setCenter(root);
            adminPane.setCenter(root);
        
        });

        logoutBtn.setOnMouseClicked((MouseEvent event) -> {
            Stage currentStage = (Stage) adminPane.getScene().getWindow();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Do you want to Logout?");
            alert.initStyle(StageStyle.UTILITY);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    setNewStage("../View/loginStyleFX.fxml", currentStage);
                } catch (IOException ex) {
                    Logger.getLogger(PatientsDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                clearSelectedButtons();
            }
        });
    }

    private void clearSelectedButtons() {
        profileBtn.setSelected(false);
        editProfileBtn.setSelected(false);
        enterNewUserBtn.setSelected(false);
        searchUserBtn.setSelected(false);
        parametricsBtn.setSelected(false);
        logoutBtn.setSelected(false);
    }
}
