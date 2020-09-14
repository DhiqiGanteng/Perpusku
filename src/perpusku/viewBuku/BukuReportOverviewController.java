/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.viewBuku;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.Buku;
import perpusku.model.ListBuku;

public class BukuReportOverviewController {
    @FXML
    private TableView<Buku> bukuTable;
    @FXML
    private TableColumn<Buku, Integer> idBukuCol;
    @FXML
    private TableColumn<Buku, String> judulCol;
    @FXML
    private TableColumn<Buku, String> penulisCol;
    @FXML
    private TableColumn<Buku, Integer> durasiCol;
    
    @FXML
    private Label meanLabel;
    
    @FXML
    private Label medianLabel;
    
    @FXML
    private Label modusLabel;
    
    @FXML
    private Label kuantitasLabel;
    
    private Stage dialogStage;
    private Buku buku;
    private ListBuku listBuku;
    private Perpusku mainApp;
    
    
     @FXML
    private void initialize() throws Exception {
        
        listBuku = new ListBuku();
        listBuku.read();
        listBuku.setDummyDurasi();
        bukuTable.setItems(listBuku.getListBuku());
        
        idBukuCol.setCellValueFactory(cellData -> cellData.getValue().getIdBuku().asObject());
        judulCol.setCellValueFactory(cellData -> cellData.getValue().getJudul());
        penulisCol.setCellValueFactory(cellData -> cellData.getValue().getPenulis());
        durasiCol.setCellValueFactory(cellData -> cellData.getValue().getDurasi().asObject());
        
        showReport(listBuku);
    }
    
    @FXML
    private void handleCancel() throws Exception {
        dialogStage = null;
        mainApp.initRootLayout();
        mainApp.showBukuOverview();
    }
    
    private void showReport(ListBuku listBuku){
        meanLabel.setText(Double.toString(listBuku.setMean()));
        medianLabel.setText(Double.toString(listBuku.setMedian()));
        modusLabel.setText(Integer.toString(listBuku.setModus()));
        kuantitasLabel.setText(Integer.toString(listBuku.getListBuku().size()));
    }
    
    public void setAnggota(Buku buku) throws Exception{
        listBuku = new ListBuku();
        listBuku.read();
        listBuku.setDummyDurasi();
        bukuTable.setItems(listBuku.getListBuku());
        
        idBukuCol.setCellValueFactory(cellData -> cellData.getValue().getIdBuku().asObject());
        judulCol.setCellValueFactory(cellData -> cellData.getValue().getJudul());
        penulisCol.setCellValueFactory(cellData -> cellData.getValue().getPenulis());
        durasiCol.setCellValueFactory(cellData -> cellData.getValue().getDurasi().asObject());
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
}
