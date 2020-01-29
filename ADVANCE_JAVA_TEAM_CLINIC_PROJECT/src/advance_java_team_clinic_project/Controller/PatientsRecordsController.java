/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Controller;

import advance_java_team_clinic_project.Model.DatabaseLoginRecords;
import advance_java_team_clinic_project.Model.Records;
import advance_java_team_clinic_project.Model.LoggedInUser;
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
 * @author Tasos
 */
public class PatientsRecordsController extends NewStage implements Initializable {

    private DatabaseLoginRecords ak;
    private ResultSet rs;
    private ObservableList data;
    @FXML
    private TableView<Records> recordsTable = new TableView<>();

    TableColumn idCol = new TableColumn("CODE");
    TableColumn appDateCol = new TableColumn("APP DATE");
    TableColumn commentsCol = new TableColumn("COMMENTS");
    TableColumn createdDateCol = new TableColumn("CREATED");
    TableColumn updatedDateCol = new TableColumn("UPDATED");
    TableColumn patientCol = new TableColumn("PATIENT");
    TableColumn doctorCol = new TableColumn("DOCTOR");
    TableColumn updatedByCol = new TableColumn("UPDATED BY");
    TableColumn createdByCol = new TableColumn("CREATED BY");
    @FXML
    private Text textHead;
    LoggedInUser user = LoggedInUser.getInstance();
    @FXML
    private AnchorPane recordsPane;

    public void initialize(URL location, ResourceBundle resources) {

        recordsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        idCol.setCellValueFactory(new PropertyValueFactory<>("app_code"));
        appDateCol.setCellValueFactory(new PropertyValueFactory<>("app_date"));
        commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("created"));
        updatedDateCol.setCellValueFactory(new PropertyValueFactory<>("updated"));
        patientCol.setCellValueFactory(new PropertyValueFactory<>("patient"));
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        updatedByCol.setCellValueFactory(new PropertyValueFactory<>("updated_by"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("created_by"));

        Callback<TableColumn<Records, Void>, TableCell<Records, Void>> cellFactory = (TableColumn<Records, Void> param) -> {
            TableCell<Records, Void> cell = new TableCell<Records, Void>() {
                private Button btn = new Button();

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        Records data = new Records();
                        data = getTableView().getItems().get(getIndex());
                        btn.setText(data.idProperty().getValue());
                        btn.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                FXMLLoader loader = new FXMLLoader(PatientsRecordsController.this.getClass().getResource("../View/id_RecordView.fxml"));
                                Parent root = (Parent) loader.load();
                                Id_RecordViewController id = loader.getController();
                                String getID = btn.getText().substring(4);
                                id.setID(getID);
                                //Scene
                                recordsPane.getChildren().clear();
                                recordsPane.getChildren().add(root);
                            } catch (IOException ex) {
                                Logger.getLogger(PatientsRecordsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(btn);
                    }
                }

            };
            return cell;
        };

        switch (user.getRoleID()) {
            case 2:
                textHead.setText("YOUR APPOINTMENTS");
                break;
            case 3:
                textHead.setText("YOUR RECORDS");
        }

        try {
            ak = new DatabaseLoginRecords();
            ak.getObject();
            rs = ak.fetchBasicInfoData(user.getRoleID(), user.getId());
            data = FXCollections.observableArrayList(databaseRecords(rs));

            idCol.setCellFactory(cellFactory);
            recordsTable.getColumns().add(idCol);
            recordsTable.getColumns().addAll(appDateCol, commentsCol,
                    createdDateCol, updatedDateCol, patientCol, doctorCol, updatedByCol, createdByCol);
            recordsTable.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(PatientsRecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns data from a database
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private ArrayList databaseRecords(ResultSet rs) throws SQLException {
        ArrayList<Records> data = new ArrayList();

        while (rs.next()) {
            Records record = new Records();
            record.idProperty().set(rs.getString("app_code"));
            record.app_dateProperty().set(rs.getString("app_date"));
            record.commentsProperty().set(rs.getString("comments"));
            record.createdProperty().set(rs.getString("created"));
            record.updatedProperty().set(rs.getString("updated"));
            record.patientProperty().set(rs.getString("patient"));
            record.doctorProperty().set(rs.getString("doctor"));
            record.updated_byProperty().set(rs.getString("updated_by"));
            record.created_byProperty().set(rs.getString("created_by"));
            data.add(record);
        }
        return data;
    }

}
