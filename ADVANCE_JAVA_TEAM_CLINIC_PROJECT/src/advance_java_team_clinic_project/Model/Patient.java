/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

import java.sql.Date;

/**
 *
 * @author Tasos
 */
public class Patient extends User {
    private int amka,ama,gender,eco_status_id,nationality_id,member_id;
    private String fathers_name,mothers_name,global_code,profession,place_of_birth;
    private Date date_of_birth;
    
    public Patient(String username, String password) {
        super(username, password);
    }

    public int getAmka() {
        return amka;
    }

    public void setAmka(int amka) {
        this.amka = amka;
    }

    public int getAma() {
        return ama;
    }

    public void setAma(int ama) {
        this.ama = ama;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getEco_status_id() {
        return eco_status_id;
    }

    public void setEco_status_id(int eco_status_id) {
        this.eco_status_id = eco_status_id;
    }

    public int getNationality_id() {
        return nationality_id;
    }

    public void setNationality_id(int nationality_id) {
        this.nationality_id = nationality_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getFathers_name() {
        return fathers_name;
    }

    public void setFathers_name(String fathers_name) {
        this.fathers_name = fathers_name;
    }

    public String getMothers_name() {
        return mothers_name;
    }

    public void setMothers_name(String mothers_name) {
        this.mothers_name = mothers_name;
    }

    public String getGlobal_code() {
        return global_code;
    }

    public void setGlobal_code(String global_code) {
        this.global_code = global_code;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

}
