/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.view;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import perpusku.Perpusku;


public class MenuOverviewController {
    private Perpusku mainApp;
    private BorderPane rootLayout;
    private Stage primaryStage;
    
    public MenuOverviewController(){
        
    }
    
    @FXML
    private void initialize(){
        
    }
    
    @FXML
    private void handleAnggota() throws Exception{
        primaryStage = null;
        mainApp.initRootLayout();
        mainApp.showAnggotaOverview();
        
        
    }
    
    @FXML
    private void handleBuku() throws Exception{
        primaryStage = null;
        mainApp.initRootLayout();
        mainApp.showBukuOverview();
    }
    
    @FXML
    private void handleTransaksi(){
        primaryStage = null;
        mainApp.initRootLayout();
        mainApp.showTransaksiOverview();
    }
    
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
}
