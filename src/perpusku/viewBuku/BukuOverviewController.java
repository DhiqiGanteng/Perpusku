/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.viewBuku;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.Buku;
import perpusku.model.ListBuku;


public class BukuOverviewController {
    private Stage dialogStage;
    private ListBuku listBuku;
    private Perpusku mainApp;
    
    @FXML
    private TableView<Buku> bukuTable;
    @FXML
    private TableColumn<Buku, Integer> idBukuCol;
    @FXML
    private TableColumn<Buku, String> judulCol;
    
    @FXML
    private Label idBukuLabel;
    @FXML
    private Label judulLabel;
    @FXML
    private Label penulisLabel;
    @FXML
    private Label durasiLabel;
    
    
    public BukuOverviewController(){
        
    }
    
    @FXML
    private void initialize() throws Exception {
        
        
        listBuku = new ListBuku();
        listBuku.read();
        listBuku.setDummyDurasi();
        bukuTable.setItems(listBuku.getListBuku());
        
        idBukuCol.setCellValueFactory(cellData -> cellData.getValue().getIdBuku().asObject());
        judulCol.setCellValueFactory(cellData -> cellData.getValue().getJudul());
        
        // Initialize the person table with the two columns.
        
        
        showBukuDetails(null);
        
        
        bukuTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showBukuDetails(newValue));
    }
    
    @FXML
    private void handleDeleteBuku() throws Exception {
        Buku selectedIndex = null ;
        selectedIndex = bukuTable.getSelectionModel().getSelectedItem();
        
        if (selectedIndex != null) {
            int i = listBuku.index(selectedIndex);
            listBuku.hapus(i);
            listBuku.write();
        
        
    } else if (selectedIndex == null){
        
        Alert alert = new Alert(AlertType.WARNING);
        
        alert.setTitle("No Selection");
        alert.setHeaderText("No Book Selected");
        alert.setContentText("Please select a Book in the table.");

        alert.showAndWait();
        
        
    }
    }
    
    @FXML
    private void handleNewBuku() throws Exception{
        dialogStage = null;
        Buku tempBuku = new Buku();
        boolean okClicked = mainApp.showBukuEditDialog(1,tempBuku,listBuku);       
        
            
         
        
    }
    @FXML
    private void handleEditBuku() throws Exception{
        dialogStage = null;
        Buku selectedBuku = bukuTable.getSelectionModel().getSelectedItem();
        boolean okClicked = true;
        if (selectedBuku != null){
            mainApp.showBukuEditDialog(0,selectedBuku,listBuku);
            
                
                
                 
            
        }else {
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Buku Selected");
            alert.setContentText("Please selected a buku in the table.");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleReport() throws Exception{
       Buku tempBuku = new Buku();
       dialogStage = null;
       mainApp.showBukuReport();
        
   
    }
    private void showBukuDetails(Buku buku){
        
        if (buku != null){
            idBukuLabel.setText(Integer.toString(buku.getIdBuku2()));
            judulLabel.setText(buku.getJudul2());
            penulisLabel.setText(buku.getPenulis2());
            durasiLabel.setText(Integer.toString(buku.getDurasi2()));
            
        }else {
            idBukuLabel.setText("");
            judulLabel.setText("");
            penulisLabel.setText("");
            durasiLabel.setText("");
            
        }
    }
    
    public void setPerpusku(ListBuku listBuku) throws Exception{
        listBuku = this.listBuku;
        listBuku.read();
        listBuku.setDummyDurasi();
        bukuTable.setItems(listBuku.getListBuku());
    
    }
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
}
