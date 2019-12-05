/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseModel;
import advance_java_team_clinic_project.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class LoginViewController implements Initializable {

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
    private static DatabaseModel ak;
    @FXML
    private TextField userNameTxtField;
    @FXML
    private PasswordField passWordField;
    
    String userNameGiven;
    String passWordGiven;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    handleButtonAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        loginBtn.setOnAction(new EventHandler<ActionEvent>(){

    
    private void handleLoginAction(ActionEvent event) throws IOException {
        ak = new DatabaseModel();
        userNameGiven = userNameTxtField.getText();
        passWordGiven = passWordField.getText();
        try {
            ak.getObject();
            ak.loginQuery(userNameGiven, passWordGiven);
        } catch (SQLException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void handleButtonAction(ActionEvent event) throws IOException { 
        
        Stage currentStage = (Stage)loginPane.getScene().getWindow();;
        Parent root;
        Scene scene;
        
        final Node source = (Node) event.getSource();
        String id = source.getId();

        if(id.equals("registerBtn")){
            root = FXMLLoader.load(getClass().getResource("../View/Sign_up.fxml"));
            scene = new Scene(root);
            currentStage.setScene(scene);
        }else if(id.equals("loginBtn")){
            root = FXMLLoader.load(getClass().getResource("../View/patientsRecordsStyle.fxml"));
            scene = new Scene(root);
            currentStage.setScene(scene);
        }
        
        currentStage.show();
        
    }

    public Button getRegisterBtn() {
        return registerBtn;
    }

    public void setRegisterBtn(Button registerBtn) {
        this.registerBtn = registerBtn;
    }

    public AnchorPane getLeftPane() {
        return leftPane;
    }

    public void setLeftPane(AnchorPane leftPane) {
        this.leftPane = leftPane;
    }

    public AnchorPane getRightPane() {
        return rightPane;
    }

    public void setRightPane(AnchorPane rightPane) {
        this.rightPane = rightPane;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(Button loginBtn) {
        this.loginBtn = loginBtn;
    }

    public Button getForgotBtn() {
        return forgotBtn;
    }

    public void setForgotBtn(Button forgotBtn) {
        this.forgotBtn = forgotBtn;
    }

    public Pane getLoginPane() {
        return loginPane;
    }

    public void setLoginPane(Pane loginPane) {
        this.loginPane = loginPane;
    }

    

}
