/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseLoginRegister;
import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class LoginViewController extends NewStage implements Initializable {

    @FXML
    private Button registerBtn;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private Button loginBtn;
    @FXML
    private Button forgotBtn;
    @FXML
    private Pane loginPane;
    private static DatabaseLoginRegister ak;
    @FXML
    private TextField userNameTxtField;
    @FXML
    private PasswordField passWordField;

    String userNameGiven;
    String passWordGiven;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerBtn.setOnAction((ActionEvent e) -> {
            try {
                handleRegisterAction(e);
            } catch (IOException ex) {
                Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        loginBtn.setOnAction((ActionEvent e) -> {
            try {
                handleLoginAction(e);
            } catch (IOException ex) {
                Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Function that triggers when the user presses the login button.
     * Connects with database and checks if the user exists or not.
     * @param event
     * @throws IOException 
     */
    private void handleLoginAction(ActionEvent event) throws IOException {
        ak = new DatabaseLoginRegister();
        userNameGiven = userNameTxtField.getText();
        passWordGiven = passWordField.getText();
        /**/
        Stage currentStage = (Stage) loginPane.getScene().getWindow();
        ak.getObject();
        if (ak.loginQuery(userNameGiven, passWordGiven) == true) {
            User user = User.getInstance();
            System.out.println(user.getRoleID());
            switch(user.getRoleID()){
                case 1:
                    setNewStage("../View/AdminDashboard.fxml", currentStage);
                    break;
                case 2:
                    setNewStage("../View/doctorsDashboard.fxml", currentStage);
                    break;
                case 3:
                    setNewStage("../View/patientsDashboard.fxml", currentStage);
                    break;
                case 5:
                    setNewStage("../View/ClinicCenterMenu.fxml", currentStage);
                    break;
            }
        }
    }
    
    /**
     * Function that triggers when the user presses the register button.
     * Returns a new page with the sign up selection.
     * @param event
     * @throws IOException 
     */
    @SuppressWarnings("empty-statement")
    private void handleRegisterAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) loginPane.getScene().getWindow();;
        setNewStage("../View/Sign_up.fxml", currentStage);
        currentStage.show();

    }
}
