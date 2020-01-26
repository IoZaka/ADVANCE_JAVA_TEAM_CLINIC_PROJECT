/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.Parametrics;
import advance_java_team_clinic_project.Model.LoggedInUser;
import advance_java_team_clinic_project.Model.Records;
import advance_java_team_clinic_project.Model.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class SearchUserController implements Initializable {

    @FXML
    private AnchorPane searchUserPane;
    @FXML
    private TableView<Users> searchUserTable;

    TableColumn idCol = new TableColumn("EDIT");
    TableColumn nameCol = new TableColumn("NAME");
    TableColumn roleCol = new TableColumn("ROLE");
    private ObservableList data;
    @FXML
    private TextField searchUserInput;
    @FXML
    private Button searchBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchUserTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        
       
       searchUserTable.getColumns().addAll(idCol,nameCol,roleCol);
       
    }
    
    
    public void setResults(String searchText){
        Users allUsers;
        allUsers = new Users();
        ResultSet rs = null;
        rs = allUsers.getUsers(searchText);
        //idCol.setCellFactory(cellFactory);
        try {
            data = FXCollections.observableArrayList(fillUsersTable(rs));
        } catch (SQLException ex) {
            Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        searchBtn.setOnMouseClicked((MouseEvent event) -> {
           setResults(searchUserInput.getText());
        });
        
        Callback<TableColumn<Users, String>, TableCell<Users, String>>  cellFactory = new Callback<TableColumn<Users,String>, TableCell<Users,String>>(){
           @Override
           public TableCell<Users, String> call(TableColumn<Users, String> param) {
             TableCell<Users, String> cell = new TableCell<Users, String>() {
                    private Button btn = new Button();

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Users user = new Users();
                            user = getTableView().getItems().get(getIndex());
                            Integer id = Integer.valueOf(user.idProperty().getValue());
                            btn.setText("EDIT");
                            btn.setOnMouseClicked((MouseEvent event) -> {
                                FXMLLoader loader = new FXMLLoader(SearchUserController.this.getClass().getResource("../View/editProfile.fxml"));
                                Parent root = null;
                                try {
                                    root = (Parent)loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                EditProfileController editController = loader.getController();
                                editController.myInit(id);
                                searchUserPane.getChildren().clear();
                                searchUserPane.getChildren().add(root);
                                
                            });
                            setGraphic(btn);
                        }
                    }
                    
             };
            return cell;
           }
        };
       idCol.setCellFactory(cellFactory);
       searchUserTable.setItems(data);
    }
    
    public ArrayList fillUsersTable(ResultSet rs) throws SQLException{
        
        ArrayList<Users> data = new ArrayList<>();
        
        while (rs.next()) {
                Users user = new Users();
                user.idProperty().set(rs.getString("id"));
                user.nameProperty().set(rs.getString("uName"));
                user.roleProperty().set(rs.getString("role"));
                data.add(user);
            }
       return data;
    }
    
}
