/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advance_java_team_clinic_project.Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public abstract class NewStage {
    void setNewStage(String path, Stage currentStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
    }
    
    void loadUIonSamePane(String path, BorderPane pane){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
        }catch(IOException ex){
            
        }
        pane.setCenter(root);
    }
    
}
