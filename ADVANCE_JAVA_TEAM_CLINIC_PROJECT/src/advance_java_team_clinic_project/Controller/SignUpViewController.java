/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.CustomCombo;
import advance_java_team_clinic_project.Model.DatabaseLoginRegister;
import advance_java_team_clinic_project.Model.DatabaseProfileEdit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox FirstQuestionCombo;
    @FXML
    private ComboBox SecondQuestionCombo;
    @FXML
    private TextField FirstAnswerQuestion;
    @FXML
    private TextField SecondAnswerQuestion;

    ObservableList<CustomCombo> customCombo = FXCollections.observableArrayList();
    private static final DatabaseProfileEdit ed = new DatabaseProfileEdit();

    private String passWord, confirmPassWord, userName, answer1, answer2;
    private int question1 = -1, question2 = -1;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static DatabaseLoginRegister ak;
    @FXML
    private ImageView backBtn;

    @Override
    @SuppressWarnings("empty-statement")
    public void initialize(URL location, ResourceBundle resources) {
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);

        registerBtn.setOnAction((ActionEvent e) -> {
            passWord = registerPassword.getText();
            confirmPassWord = confirmPassword.getText();
            userName = registerUsername.getText();
            answer1 = FirstAnswerQuestion.getText();
            answer2 = SecondAnswerQuestion.getText();
            /**/
            Stage currentStage = (Stage) signUpPane.getScene().getWindow();
            Parent root;
            Scene scene;
            /**/
            if (!userName.equals(null) && !userName.equals("")) {
                ak = new DatabaseLoginRegister();
                if (!passWord.equals(null) && !passWord.equals("") && !confirmPassWord.equals(null) && !confirmPassWord.equals("")) {
                    if (passWord.equals(confirmPassWord)) {
                        if (!answer1.equals(null) && !answer1.equals("") && !answer2.equals(null) && !answer2.equals("") && (question1 > 0) && (question2 > 0)) {
                            if (question1 != question2) {
                                try {
                                    ak.getObject();
                                    if (ak.registerQuery(userName, passWord, question1, question2, answer1, answer2) == true) {
                                        root = FXMLLoader.load(SignUpViewController.this.getClass().getResource("../View/loginStyleFX.fxml"));
                                        scene = new Scene(root);
                                        currentStage.setScene(scene);
                                    };
                                } catch (IOException ex) {
                                    Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                alert.setTitle("Same questions.");
                                alert.setContentText("You must choose different questions.");
                                alert.showAndWait();
                            }
                        } else {
                            alert.setTitle("Questions or answers are empty.");
                            alert.setContentText("Questions must be picked and answered for password recovery.");
                            alert.showAndWait();
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
        });

        backBtn.setOnMouseClicked((MouseEvent event) -> {
            Stage currentStage = (Stage) signUpPane.getScene().getWindow();
            try {
                setNewStage("../View/loginStyleFX.fxml", currentStage);
            } catch (IOException ex) {
                Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        try {
            setComboValues();
            setComboEventListeners();
        } catch (SQLException ex) {
            Logger.getLogger(SignUpViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setComboValues() throws SQLException {
        ed.getObject();
        customCombo = ed.FetchData("PM_QUESTIONS");
        FirstQuestionCombo.setItems(FXCollections.observableArrayList(customCombo));
        SecondQuestionCombo.setItems(FXCollections.observableArrayList(customCombo));
    }

    private void setComboEventListeners() {
        FirstQuestionCombo.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coQuest1 = (CustomCombo) FirstQuestionCombo.getSelectionModel().getSelectedItem();
                question1 = coQuest1.getId();
                System.out.println(question1);
            }
        });

        SecondQuestionCombo.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomCombo coQuest2 = (CustomCombo) SecondQuestionCombo.getSelectionModel().getSelectedItem();
                question2 = coQuest2.getId();
                System.out.println(question2);
            }
        });
    }
}
