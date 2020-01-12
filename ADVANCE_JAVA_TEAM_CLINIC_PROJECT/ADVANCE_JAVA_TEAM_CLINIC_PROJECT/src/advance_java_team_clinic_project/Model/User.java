/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

public class User {

    private int id;
    private String username;
    private String firstName;
    private String surname;
    private int ruleID;
    private int addressID;   
    private int contactID; 

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public int getContactID() {
        return contactID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
    
    public int getRuleID() {
        return ruleID;
    }
    public void setRuleID(int ruleID) {
        this.ruleID = ruleID;
    }  
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    private int roleID;
    private String code;
    private static User instance = null;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    private User(){} 
  
    public static User getInstance() 
    { 
        if (instance == null) 
            instance = new User(); 
  
        return instance; 
    } 

    public void setInstance(User instance) {
        User.instance = null;
    }
   

}
