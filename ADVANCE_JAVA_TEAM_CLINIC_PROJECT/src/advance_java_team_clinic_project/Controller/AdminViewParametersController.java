/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.Parametrics;
import advance_java_team_clinic_project.Model.Records;
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
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Tasos
 */
public class AdminViewParametersController extends NewStage implements Initializable {

    @FXML
    private AnchorPane ParametricsPanel;
    @FXML
    private Button backBtn;
    @FXML
    private Button createBtn;
    
    @FXML
    private TableView<Parametrics> parametricsTable;
    
   
    @FXML
    private Text DescriptionLayout;
    
    
    private ObservableList data;

    TableColumn idCol = new TableColumn("CODE");
    TableColumn descrCol = new TableColumn("DESCRIPTION");
    TableColumn createdDateCol = new TableColumn("CREATED");
    TableColumn updatedDateCol = new TableColumn("UPDATED");
    TableColumn updatedByCol = new TableColumn("UPDATED BY");
    TableColumn createdByCol = new TableColumn("CREATED BY");
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parametricsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        descrCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("created"));
        updatedDateCol.setCellValueFactory(new PropertyValueFactory<>("updated"));
        updatedByCol.setCellValueFactory(new PropertyValueFactory<>("updatedby"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdby"));
                
       
     
        backBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(AdminViewParametersController.this.getClass().getResource("../View/AdminParametrics.fxml"));
                Parent root = (Parent) loader.load();
                ParametricsPanel.getChildren().clear();
                ParametricsPanel.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        parametricsTable.getColumns().addAll(idCol,descrCol,createdDateCol,createdByCol,updatedDateCol,updatedByCol);
   
     
    }
    

    public void setWindow(String tableName, String lName) {  
        Parametrics pm = new Parametrics();
        ResultSet rs = null;
        rs = pm.getDesc(tableName);
        System.out.println(lName);
        DescriptionLayout.setText(lName);
        try {
            data = FXCollections.observableArrayList(fillParametricsTable(rs));
        } catch (SQLException ex) {
            Logger.getLogger(AdminViewParametersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        createBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(AdminViewParametersController.this.getClass().getResource("../View/AdminNewComponent.fxml"));
                Parent root = (Parent) loader.load();  
                Stage createB = new Stage();
                Scene scene = new Scene(root);
                createB.setTitle("Enter New Description ");
                createB.setScene(scene);
                createB.setResizable(false);
                createB.setOnCloseRequest((WindowEvent event1) -> {setWindow(tableName, lName);});
                createB.show();
                
                AdminNewComponentController createComp = loader.getController();
                createComp.createComponent(tableName);
            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Callback<TableColumn<Parametrics, Void>, TableCell<Parametrics, Void>>  cellFactory = new Callback<TableColumn<Parametrics,Void>, TableCell<Parametrics,Void>>(){
           @Override
           public TableCell<Parametrics, Void> call(TableColumn<Parametrics, Void> param) {
             TableCell<Parametrics, Void> cell = new TableCell<Parametrics, Void>() {
                    private Button btn = new Button();

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Parametrics data = new Parametrics();
                            Integer id;
                            data = getTableView().getItems().get(getIndex());
                            id = Integer.valueOf(data.idProperty().getValue());
                            String desc = data.descriptionProperty().getValue();
                            btn.setText("EDIT");
                            btn.setOnMouseClicked((MouseEvent event) -> {
                            
                            
                                try {
//                                    Parent root = FXMLLoader.load(getClass().getResource("../View/AdminNewComponent.fxml"));
                                    Stage createB = new Stage();
                                    
                                    FXMLLoader loader = new FXMLLoader(AdminViewParametersController.this.getClass().getResource("../View/AdminNewComponent.fxml"));
                                    Parent root = (Parent) loader.load();  
                                    Scene scene = new Scene(root);
                                    createB.setTitle("EDIT");
                                    createB.setScene(scene);
                                    createB.setResizable(false);
                                    createB.setOnCloseRequest((WindowEvent event1) -> {setWindow(tableName, lName);});
                                    createB.show();
                                   
                                    AdminNewComponentController editComp = loader.getController();
                                    editComp.editComponent(tableName, id, desc);
                                } catch (IOException ex) {
                                    Logger.getLogger(PatientsRecordsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            setGraphic(btn);
                        }
                    }
                    
             };
            return cell;
           }
        };
        idCol.setCellFactory(cellFactory);
        
        parametricsTable.setItems(data);
    }
    
    public ArrayList fillParametricsTable(ResultSet rs) throws SQLException{
        
        ArrayList<Parametrics> data = new ArrayList();
        
        while (rs.next()) {
                Parametrics pm = new Parametrics();
                pm.idProperty().set(rs.getString("id"));
                pm.descriptionProperty().set(rs.getString("description"));
                pm.createdProperty().set(rs.getString("created"));
                pm.createdbyProperty().set(rs.getString("createdby"));
                pm.updatedProperty().set(rs.getString("updated"));
                pm.updatedbyProperty().set(rs.getString("updatedby"));
                data.add(pm);
            }
       return data;
    }
     
}
