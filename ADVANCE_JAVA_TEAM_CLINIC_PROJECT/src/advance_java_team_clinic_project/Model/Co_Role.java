/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Co_Role {
    
    private int id;
    private String description;
    private List<Co_Role> only_list; 
   // private List<String> Description_list;

    public List<Co_Role> getOnly_list() {
        return only_list;
    }

    public void setOnly_list(List<Co_Role> only_list) {
        this.only_list = only_list;
    }

    public Co_Role(int id, String description) {
        this.id = id;
        this.description = description;
    
    }

   
    public Co_Role() {
        //only_list = new ArrayList<Co_Role>(100); 
     //   Description_list = new ArrayList<String>(100); 
    }

    // Setter/Getter for id list
//    public void setId_list(List<Integer> id_list) {
//        this.id_list = id_list;
//    }
//
//    public List<Integer> getId_list() {
//        return id_list;
//    }
//    
//    //Add to list int x to index y
//    public void AddToIdList(int x,int y){ 
//        id_list.add(x, y);
//    }
//    
//    //Get index 2 from list
//    public void GetFromIdList(int x){
//        id_list.get(x);
//    }
//    
//     // Setter/Getter for id list
//    public void setDescription_list(List<String> Description_list) {
//        this.Description_list = Description_list;
//    }
//
//    public List<String> getDescription_list() {
//        return Description_list;
//    }
//    
//    //Add to list int x to index y
//    public void AddToDescription_list(int x,String y){ 
//        Description_list.add(x, y);
//    }
//    
//    //Get index 2 from list
//    public void GetFromDescription_list(int x){
//        Description_list.get(x);
//    }
//    
    // Setter/Getter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Setter/Getter for Description
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
