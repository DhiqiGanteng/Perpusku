/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.view;


import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;
import perpusku.Perpusku;
import perpusku.model.Anggota;
import perpusku.model.ListAnggota;

/**
 *
 * @author 17523220
 */
public class AnggotaController {
    private ListAnggota anggotaList;
    private Perpusku mainApp;
    private int pilihan;
    private Stage primaryStage;
    
    
    @FXML
    private TableView<Anggota> anggotaTable;
    @FXML
    private TableColumn<Anggota, Integer> idNumberCol;
    @FXML
    private TableColumn<Anggota, String> namaDepanCol;
    
    @FXML
    private Label idNumberLabel;
    @FXML
    private Label namaDepanLabel;
    @FXML
    private Label namaBelakangLabel;
    @FXML
    private Label alamatLabel;
    @FXML
    private Label umurLabel;
    @FXML
    private Label durasiLabel;
   
    
    public AnggotaController(){
    
    }
    
    @FXML
    private void initialize() throws  Exception {
        
        
        anggotaList = new ListAnggota();
        anggotaList.read();
        anggotaList.setDummyDurasi();
        anggotaTable.setItems(anggotaList.getListAnggota());
        
        idNumberCol.setCellValueFactory(cellData -> cellData.getValue().getIdNumber().asObject());
        namaDepanCol.setCellValueFactory(cellData -> cellData.getValue().getNamaDepan());
        
        // Initialize the person table with the two columns.
    idNumberCol.setCellValueFactory(
            cellData -> cellData.getValue().getIdNumber().asObject());
    namaDepanCol.setCellValueFactory(
            cellData -> cellData.getValue().getNamaDepan());
        
        showAnggotaDetails(null);
        
        
        anggotaTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showAnggotaDetails(newValue));
    }
    
    @FXML
    private void handleDeleteAnggota() throws Exception {
    Anggota selectedIndex = null ;
    selectedIndex = anggotaTable.getSelectionModel().getSelectedItem();
    if (selectedIndex != null) {
        int i = anggotaList.index(selectedIndex);
        anggotaList.hapus(i);
        anggotaList.write();
        anggotaTable.getItems().remove(selectedIndex);
        
        
    } else if (selectedIndex == null){
        
        Alert alert = new Alert(AlertType.WARNING);
        
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
        
        
    }
    }
    
    @FXML
    private void handleNewAnggota() throws Exception{
        Anggota tempAnggota = new Anggota();
        primaryStage = null;
        boolean okClicked = mainApp.showAnggotaEditDialog(1,tempAnggota,anggotaList);       
        
    }
    
    @FXML
    private void handleEditPerson() throws Exception{
        Anggota selectedAnggota = anggotaTable.getSelectionModel().getSelectedItem();
        if (selectedAnggota != null){
            boolean okClicked = true;
            primaryStage = null;
            mainApp.showAnggotaEditDialog(2,selectedAnggota,anggotaList);
            
        }else {
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Anggota Selected");
            alert.setContentText("Please selected a anggota in the table.");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleReport() throws Exception{
       Anggota tempAnggota = new Anggota();
       primaryStage = null;
       mainApp.showAnggotaReport();
        
   
    }
    private void showAnggotaDetails(Anggota anggota){
        
        if (anggota != null){
            idNumberLabel.setText(Integer.toString(anggota.getIdNumber2()));
            namaDepanLabel.setText(anggota.getNamaDepan2());
            namaBelakangLabel.setText(anggota.getNamaBelakang2());
            alamatLabel.setText(anggota.getAlamat2());
            umurLabel.setText(Integer.toString(anggota.getUmur2()));
            durasiLabel.setText(Integer.toString(anggota.getDurasi2()));
        }else {
            idNumberLabel.setText("");
            namaDepanLabel.setText("");
            namaBelakangLabel.setText("");
            alamatLabel.setText("");
            umurLabel.setText("");
            durasiLabel.setText("");
        }
    }
    
    public void setPerpusku(ListAnggota anggotaList) throws Exception{
        anggotaList = this.anggotaList;
        anggotaList.read();
        anggotaList.setDummyDurasi();
        anggotaTable.setItems(anggotaList.getListAnggota());
        
        
    }
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
    
    

}
