/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Model;

public class User {

    private int id;
    private String username;
<<<<<<< Updated upstream
  //  private String password;
=======
<<<<<<< Updated upstream
    private String password;
>>>>>>> Stashed changes
    private String firstName;
    private String surname;
//    private ContactDetails contactDetails;
//    private AddressDetails addressDetails;
   // private int addressID;
   // private int contactID;
    private int ruleID;

    public int getRuleID() {
        return ruleID;
    }

    public void setRuleID(int ruleID) {
        this.ruleID = ruleID;
    }
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
=======
    private String firstName;
    private String surname;
    private int roleID;
    private String code;
    private static User instance = null;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
>>>>>>> Stashed changes

<<<<<<< Updated upstream
   // public String getPassword() {return password;}
   // public void setPassword(String password) {this.password = password;}
=======
    public int getId() {
        return id;
    }

<<<<<<< Updated upstream
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
=======
    public void setId(int id) {
        this.id = id;
    }
>>>>>>> Stashed changes
>>>>>>> Stashed changes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

<<<<<<< Updated upstream
  //  public int getAddressID() {return addressID;}
  //  public void setAddressID(int addressID) {this.addressID = addressID;}
=======
<<<<<<< Updated upstream
    public int getAddressID() {return addressID;}
    public void setAddressID(int addressID) {this.addressID = addressID;}
>>>>>>> Stashed changes

   // public int getContactID() {return contactID;}
   // public void setContactID(int contactID) {this.contactID = contactID;}

 //   public User(String username, String password) {
 //       this.username = username;
 //       this.password = password;
 //   }
    
    
=======
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

    public void setCode(String surname) {
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
>>>>>>> Stashed changes
    

}
