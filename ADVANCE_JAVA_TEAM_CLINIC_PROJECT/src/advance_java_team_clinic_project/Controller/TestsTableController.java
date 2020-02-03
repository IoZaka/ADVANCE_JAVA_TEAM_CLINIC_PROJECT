/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.CustomComboModel;
import advance_java_team_clinic_project.Model.TestsModel;
import advance_java_team_clinic_project.classes.CustomComboClass;
import advance_java_team_clinic_project.classes.LoggedInUserClass;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class TestsTableController implements Initializable {

    private ObservableList data;
    private CustomComboModel ed = new CustomComboModel();

    @FXML
    private TableView<TestsModel> testsTable = new TableView<>();

    
    TableColumn idCol = new TableColumn("Edit");
    TableColumn descriptionCol = new TableColumn("Description");
    TableColumn isCompletedCol = new TableColumn("Completed");
    TableColumn costCol = new TableColumn("Cost");
    TableColumn isPaidCol = new TableColumn("Paid");
    TableColumn resultsCol = new TableColumn("Results");
    TableColumn statusCol = new TableColumn("Status");
    TableColumn createdCol = new TableColumn("Created");
    TableColumn createdByCol = new TableColumn("Created By");
    TableColumn updatedCol = new TableColumn("Updated");
    TableColumn updatedByCol = new TableColumn("Updated By");
    TableColumn patientCol = new TableColumn("Patient");
    TableColumn doctorCol = new TableColumn("Doctor");

    @FXML
    private Button backBtn;
    @FXML
    private AnchorPane testsPane;

    LoggedInUserClass user = LoggedInUserClass.getInstance();
    TestsModel tests = new TestsModel();
    @FXML
    private ComboBox patientComboBox;

    ObservableList<CustomComboClass> customCombo = FXCollections.observableArrayList();
    @FXML
    private ComboBox doctorComboBox;
    @FXML
    private DatePicker createdFromDate;
    @FXML
    private DatePicker createdToDate;
    @FXML
    private ComboBox paidComboBox;
    @FXML
    private ComboBox completedComboBox;
    @FXML
    private Button searchBtn;
    @FXML
    private Text doctorHeader;
    @FXML
    private Text patientHeader;
    
    private Integer patientID;
    private Integer doctorID;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        testsTable.setId("tables");
        //For All
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        isCompletedCol.setCellValueFactory(new PropertyValueFactory<>("is_completed"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        isPaidCol.setCellValueFactory(new PropertyValueFactory<>("is_paid"));
        resultsCol.setCellValueFactory(new PropertyValueFactory<>("results"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status_id"));
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        createdCol.setCellValueFactory(new PropertyValueFactory<>("created"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("created_by"));
        updatedCol.setCellValueFactory(new PropertyValueFactory<>("updated"));
        updatedByCol.setCellValueFactory(new PropertyValueFactory<>("updated_by"));
        patientCol.setCellValueFactory(new PropertyValueFactory<>("patient"));

        if(user.getRoleID() == 3){
           doctorComboBox.setVisible(false);
           patientComboBox.setVisible(false);
           doctorHeader.setVisible(false);
           patientHeader.setVisible(false);
        }else{
           doctorComboBox.setVisible(true);
           patientComboBox.setVisible(true);
           doctorHeader.setVisible(true);
           patientHeader.setVisible(true);
        }
        
        paidComboBox.getItems().addAll("Yes","No");
        completedComboBox.getItems().addAll("Yes","No");
        testsTable.getColumns().addAll(idCol, descriptionCol, isCompletedCol, costCol, isPaidCol, resultsCol, statusCol, doctorCol, createdCol, createdByCol, updatedCol, updatedByCol, patientCol);
    }

    /**
     *
     * @param diagID
     */
    public void setTestID(Integer diagID) {
        Callback<TableColumn<TestsModel, String>, TableCell<TestsModel, String>> cellFactory = (final TableColumn<TestsModel, String> param) -> {
            final TableCell<TestsModel, String> cell = new TableCell<TestsModel, String>() {
                final Button btn = new Button();

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        TestsModel test = new TestsModel();
                        test = getTableView().getItems().get(getIndex());
                        String testID = test.idProperty().getValue();
                        btn.setText("EDIT");
                        btn.setStyle("-fx-pref-width: 200px;");
                        btn.setOnAction(event -> {
                            FXMLLoader loader = new FXMLLoader(TestsTableController.this.getClass().getResource("../View/TestsInfoView.fxml"));
                            Parent root = null;
                            try {
                                root = (Parent) loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TestsTableController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            TestsInfoController id = loader.getController();
                            if(diagID == -1){
                                id.setTestIDView(false,Integer.valueOf(testID),-1);
                            }else{
                                id.setTestIDView(true,Integer.valueOf(testID),-1);
                            }
                            
                            //Scene
                            testsPane.getChildren().clear();
                            testsPane.getChildren().add(root);
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        idCol.setCellFactory(cellFactory);

        backBtn.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(TestsTableController.this.getClass().getResource("../View/DiagnosisInfoView.fxml"));
                Parent root = (Parent) loader.load();
                DiagnosisInfoController diagnosisID = loader.getController();
                //diagnosisID.setDiagnosisID(appID, diagID);
                testsPane.getChildren().clear();
                testsPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(TestsTableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        try {
            data = FXCollections.observableArrayList(databaseTests(tests.getTestByDiagID(diagID)));
            ed.getObject();
            
            customCombo = ed.FetchUserFilterData(3);
            patientComboBox.setItems(FXCollections.observableArrayList(customCombo));
            
            customCombo = ed.FetchUserFilterData(2);
            doctorComboBox.setItems(FXCollections.observableArrayList(customCombo));
            
            setComboEventListeners();
            
            testsTable.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(TestsTableController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
           searchBtn.setOnMouseClicked((MouseEvent event) -> {
             System.out.println("Created From:" + createdFromDate.getValue());
             System.out.println("Created To:" + createdToDate.getValue());
             System.out.println("Patient: " + patientComboBox.getSelectionModel().getSelectedItem());
             System.out.println("Doctor: " + doctorComboBox.getSelectionModel().getSelectedItem());
             System.out.println("Completed: " + completedComboBox.getSelectionModel().getSelectedItem());
             System.out.println("Paid: " + paidComboBox.getSelectionModel().getSelectedItem());
             
        });
        
    }
    
    private void setComboEventListeners(){
        patientComboBox.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomComboClass coPatient = (CustomComboClass) patientComboBox.getSelectionModel().getSelectedItem();
                patientID = coPatient.getId();
                System.out.println(coPatient.getId());
            }
        });
        
        doctorComboBox.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                CustomComboClass coDoctor = (CustomComboClass) doctorComboBox.getSelectionModel().getSelectedItem();
                doctorID = coDoctor.getId();
                System.out.println(coDoctor.getId());
                
            }
        });
    }
    
    
    private ArrayList databaseTests(ResultSet rs) throws SQLException {
        ArrayList<TestsModel> data = new ArrayList();

        while (rs.next()) {
            TestsModel test = new TestsModel();
            test.idProperty().set(rs.getString("id"));
            //test.diag_idProperty().set(rs.getString("diag_id"));
            test.descriptionProperty().set(rs.getString("description"));
            test.is_completedProperty().set(rs.getString("is_completed"));
            test.costProperty().set(rs.getString("cost"));
            test.is_paidProperty().set(rs.getString("paid"));
            test.resultsProperty().set(rs.getString("results"));
            test.status_idProperty().set(rs.getString("status"));
            test.doctorProperty().set(rs.getString("doctor"));
                test.createdProperty().set(rs.getString("created"));
                test.created_byProperty().set(rs.getString("created_by"));
                test.updatedProperty().set(rs.getString("updated"));
                test.updated_byProperty().set(rs.getString("updated_by"));
                test.patientProperty().set(rs.getString("patient"));
            

            data.add(test);
        }
        return data;
    }
    
    

}