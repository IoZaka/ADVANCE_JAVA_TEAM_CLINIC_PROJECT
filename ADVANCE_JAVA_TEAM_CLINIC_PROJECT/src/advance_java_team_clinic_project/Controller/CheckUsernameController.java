/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseProfileDetails;
import advance_java_team_clinic_project.Model.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CheckUsernameController implements Initializable {

    @FXML
    private TextField newUsernameInput;
    @FXML
    private Button submitBtn;
    @FXML
    private ImageView statusIcon;
    @FXML
    private Text statusText;

    ArrayList<String> usernames;

    private static final DatabaseProfileDetails ak = new DatabaseProfileDetails();
    private ResultSet rs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = User.getInstance();
        try {
            usernames = new ArrayList<>();
            ak.getObject();
            rs = ak.fetchAllUsernames();
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }

            usernames.stream().map((i) -> {
                return i;
            }).forEachOrdered((i) -> {
                newUsernameInput.addEventFilter(KeyEvent.KEY_RELEASED, usernameValidation(i));
            });

            statusIcon.setImage(null);
            submitBtn.setDisable(true);
            statusText.setText("Enter a username");
        } catch (SQLException ex) {
            Logger.getLogger(CheckUsernameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn.setOnMouseClicked((MouseEvent event) -> {
            ak.updateUsername(user.getId(), newUsernameInput.getText());
        });
    }
    public EventHandler<KeyEvent> usernameValidation(final String username) {
        return (KeyEvent e) -> {
            TextField txt_TextField = (TextField) e.getSource();
            if (txt_TextField.getText().equals(username)) {
                statusIcon.setImage(new Image(getClass().getResourceAsStream("../View/images/error.png")));
                submitBtn.setDisable(true);
                statusText.setText("Username already exists.");
            } else if (txt_TextField.getText().equals(null) || txt_TextField.getText().equals("")) {
                statusIcon.setImage(null);
                submitBtn.setDisable(true);
                statusText.setText("Enter a username");
            } else {
                statusIcon.setImage(null);
                statusText.setText("");
                submitBtn.setDisable(false);
            }
        };
    }

}
