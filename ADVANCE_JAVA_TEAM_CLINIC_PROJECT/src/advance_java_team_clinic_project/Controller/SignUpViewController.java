/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;


import advance_java_team_clinic_project.Model.DatabaseLoginRegister;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Chris
 */
public class SignUpViewController extends NewStage implements Initializable {

    @FXML
    private TextField registerUsername;
    @FXML
    private PasswordField registerPassword;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Pane signUpPane;
    @FXML
    private Button registerBtn;

    private String passWord;
    private String confirmPassWord;
    private String userName;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static DatabaseLoginRegister ak;
    @FXML
    private ImageView backBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);

        registerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                passWord = registerPassword.getText();
                confirmPassWord = confirmPassword.getText();
                userName = registerUsername.getText();
                /**/
                Stage currentStage = (Stage) signUpPane.getScene().getWindow();;
                Parent root;
                Scene scene;
                /**/

                if (!userName.equals(null) && !userName.equals("")) {
                    ak = new DatabaseLoginRegister();
                    if (!passWord.equals(null) && !passWord.equals("") && !confirmPassWord.equals(null) && !confirmPassWord.equals("")) {
                        if (passWord.equals(confirmPassWord)) {
                            try {
                                ak.getObject();
                                if (ak.registerQuery(userName, passWord) == true) {
                                    root = FXMLLoader.load(getClass().getResource("../View/loginStyleFX.fxml"));
                                    scene = new Scene(root);
                                    currentStage.setScene(scene);
                                };
                            } catch (SQLException ex) {
                                Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else {
                            alert.setTitle("Incorrect password");
                            alert.setContentText("Passwords do not match!");
                            alert.showAndWait();
                        }
                    } else {
                        alert.setTitle("Incorrect password");
                        alert.setContentText("Please enter valid passwords!");
                        alert.showAndWait();
                    }
                } else {

                    alert.setTitle("Incorrect username");
                    alert.setContentText("Please enter a valid username!");
                    alert.showAndWait();
                }
            }
        });
        
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Stage currentStage = (Stage)signUpPane.getScene().getWindow();
                try {
                    setNewStage("../View/loginStyleFX.fxml",currentStage);
                } catch (IOException ex) {
                    Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
