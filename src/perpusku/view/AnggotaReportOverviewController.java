/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.Anggota;
import perpusku.model.ListAnggota;

public class AnggotaReportOverviewController {
    
    @FXML
    private TableView<Anggota> anggotaTable;
    @FXML
    private TableColumn<Anggota, Integer> idNumberCol;
    @FXML
    private TableColumn<Anggota, String> namaDepanCol;
    @FXML
    private TableColumn<Anggota, String> namaBelakangCol;
    @FXML
    private TableColumn<Anggota, String> alamatCol;
    @FXML
    private TableColumn<Anggota, Integer> umurCol;
    @FXML
    private TableColumn<Anggota, Integer> durasiCol;
    
    @FXML
    private Label meanLabel;
    
    @FXML
    private Label medianLabel;
    
    @FXML
    private Label modusLabel;
    
    @FXML
    private Label kuantitasLabel;
    
    private Stage dialogStage;
    private Anggota anggota;
    private ListAnggota anggotaList;
    private Perpusku mainApp;
            
    @FXML
    private void initialize() throws Exception {
        
        anggotaList = new ListAnggota();
        anggotaList.read();
        anggotaList.setDummyDurasi();
        anggotaTable.setItems(anggotaList.getListAnggota());
        
        idNumberCol.setCellValueFactory(cellData -> cellData.getValue().getIdNumber().asObject());
        namaDepanCol.setCellValueFactory(cellData -> cellData.getValue().getNamaDepan());
        namaBelakangCol.setCellValueFactory(cellData -> cellData.getValue().getNamaBelakang());
        alamatCol.setCellValueFactory(cellData -> cellData.getValue().getAlamat());
        umurCol.setCellValueFactory(cellData -> cellData.getValue().getUmur().asObject());
        durasiCol.setCellValueFactory(cellData -> cellData.getValue().getDurasi().asObject());
        
        showReport(anggotaList);
    }
    
    @FXML
    private void handleCancel() throws Exception {
        dialogStage = null;
        mainApp.initRootLayout();
        mainApp.showAnggotaOverview();
    }
    
    private void showReport(ListAnggota anggotaList){
        meanLabel.setText(Double.toString(anggotaList.setMean()));
        medianLabel.setText(Double.toString(anggotaList.setMedian()));
        modusLabel.setText(Integer.toString(anggotaList.setModus()));
        kuantitasLabel.setText(Integer.toString(anggotaList.panjang()+1));
    }
    
    public void setAnggota(Anggota anggota) throws Exception{
        anggotaList = new ListAnggota();
        anggotaList.read();
        anggotaList.setDummyDurasi();
        anggotaTable.setItems(anggotaList.getListAnggota());
        
        idNumberCol.setCellValueFactory(cellData -> cellData.getValue().getIdNumber().asObject());
        namaDepanCol.setCellValueFactory(cellData -> cellData.getValue().getNamaDepan());
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
    
    
    
}
