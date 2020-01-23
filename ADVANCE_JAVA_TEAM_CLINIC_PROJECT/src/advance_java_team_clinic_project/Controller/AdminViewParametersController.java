/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class AdminViewParametersController implements Initializable {

    @FXML
    private AnchorPane ParametricsPanel;
    @FXML
    private TableView<?> tableview;
    @FXML
    private Button backBtn;
    @FXML
    private Button createBtn;
    private String idButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        createBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../View/AdminNewDiscription.fxml"));
                Stage createB = new Stage();
                Scene scene = new Scene(root);
                createB.setTitle("Enter New Description ");
                createB.setScene(scene);
                createB.setResizable(false);
                createB.show();
            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setWindow(String tableName) {
        idButton = tableName;

        System.out.println(idButton);
    }

}
