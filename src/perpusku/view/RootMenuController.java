/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.view;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.ListAnggota;

public class RootMenuController {
    private Perpusku mainApp;
    private BorderPane rootLayout;
    private Stage primaryStage;
    private ListAnggota list;
    
    public RootMenuController(){
        
    }
    
    @FXML
    private void handleHome(){
        primaryStage = null; 
                
        
        mainApp.initRootLayout();
        mainApp.showMenu();
   
    }
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    
}
    

