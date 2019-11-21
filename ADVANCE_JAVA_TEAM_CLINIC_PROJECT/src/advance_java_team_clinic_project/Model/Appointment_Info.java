/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Model;

import java.sql.Date;

/**
 *
 * @author Tasos
 */
public class Appointment_Info {
    private int patient_id,doctor_id;

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getApp_date() {
        return app_date;
    }

    public void setApp_date(Date app_date) {
        this.app_date = app_date;
    }

    public String getCommentsapp_code() {
        return commentsapp_code;
    }

    public void setCommentsapp_code(String commentsapp_code) {
        this.commentsapp_code = commentsapp_code;
    }
    private Date app_date;
    private String commentsapp_code;
    
   
    
}
