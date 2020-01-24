/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.Records;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class AdminViewParametersController implements Initializable {

    @FXML
    private AnchorPane ParametricsPanel;
    @FXML
    private TableView<?> tableview /* = new TableView<>()*/;
    @FXML
    private Button backBtn;
    @FXML
    private Button createBtn;
    private String idButton;
    @FXML
    private Text DescriptionLayout;
    
    private String descName;
  /*  TableColumn idCol = new TableColumn("CODE");
    TableColumn descrCol = new TableColumn("DESCRIPTION");
    TableColumn createdDateCol = new TableColumn("CREATED");
    TableColumn updatedDateCol = new TableColumn("UPDATED");
    TableColumn updatedByCol = new TableColumn("UPDATED BY");
    TableColumn createdByCol = new TableColumn("CREATED BY");*/

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    public void myInitialize (){
         createBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
            /*    idCol.setCellValueFactory(new PropertyValueFactory<>("code"));
                descrCol.setCellValueFactory(new PropertyValueFactory<>("description"));
                createdDateCol.setCellValueFactory(new PropertyValueFactory<>("created"));
                updatedDateCol.setCellValueFactory(new PropertyValueFactory<>("updated"));
                updatedByCol.setCellValueFactory(new PropertyValueFactory<>("updated_by"));
                createdByCol.setCellValueFactory(new PropertyValueFactory<>("created_by"));*/


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
            System.out.println(tableName);
    }

}
