/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;


import advance_java_team_clinic_project.Model.DatabaseModel;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class SignUpViewController implements Initializable {

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
    private static DatabaseModel ak;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                passWord = registerPassword.getText();
                confirmPassWord = confirmPassword.getText();
                userName = registerUsername.getText();
                alert.setHeaderText(null);
                alert.initStyle(StageStyle.UTILITY);
                if (userName != null && !userName.isEmpty()) {
                    ak = new DatabaseModel();
                    if (passWord != null && !passWord.isEmpty() && confirmPassword != null && !confirmPassWord.isEmpty()) {
                        if (passWord.equals(confirmPassWord)) {
                            try {
                                ak.getObject();
                                ak.registerQuery(userName, passWord);
                            } catch (SQLException ex) {
                                Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            alert.setTitle("Incorrect password");
                            alert.setContentText("Passwords do not match1");
                            alert.showAndWait();
                        }
                    } else {
                        alert.setTitle("Incorrect password");
                        alert.setContentText("Please enter valid passwords");
                        alert.showAndWait();
                    }
                } else {

                    alert.setTitle("Incorrect username");
                    alert.setContentText("Please enter a valid username!");
                    alert.showAndWait();
                }
            }
        });
    }
}
