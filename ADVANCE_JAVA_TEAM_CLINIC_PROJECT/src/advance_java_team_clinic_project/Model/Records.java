/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Chris
 */
public class Records {

    private StringProperty id = new SimpleStringProperty();
    private StringProperty app_date = new SimpleStringProperty();
    private StringProperty comments = new SimpleStringProperty();
    private StringProperty app_code = new SimpleStringProperty();
    private StringProperty created = new SimpleStringProperty();
    private StringProperty updated = new SimpleStringProperty();
    private StringProperty patient = new SimpleStringProperty();
    private StringProperty doctor = new SimpleStringProperty();
    private StringProperty updated_by = new SimpleStringProperty();
    private StringProperty created_by = new SimpleStringProperty();

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty app_dateProperty() {
        return app_date;
    }

    public StringProperty commentsProperty() {
        return comments;
    }

    public StringProperty app_codeProperty() {
        return app_code;
    }

    public StringProperty createdProperty() {
        return created;
    }

    public StringProperty updatedProperty() {
        return updated;
    }

    public StringProperty patientProperty() {
        return patient;
    }

    public StringProperty doctorProperty() {
        return doctor;
    }

    public StringProperty updated_byProperty() {
        return updated_by;
    }

    public StringProperty created_byProperty() {
        return created_by;
    }

    public Records() {
    }

}
