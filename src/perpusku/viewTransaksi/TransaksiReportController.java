/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.viewTransaksi;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.ListTransaksi;
import perpusku.model.Transaksi;

public class TransaksiReportController {
    @FXML
    private TableView<Transaksi> transaksiTable;
    @FXML
    private TableColumn<Transaksi, Integer> idTransaksiCol;
    @FXML
    private TableColumn<Transaksi, String> namaDepanCol;
    @FXML
    private TableColumn<Transaksi, String> namaBelakangCol;
    @FXML
    private TableColumn<Transaksi, String> judulCol;
    @FXML
    private TableColumn<Transaksi, LocalDate> meminjamCol;
    @FXML
    private TableColumn<Transaksi, LocalDate> pengembalianCol;
    @FXML
    private TableColumn<Transaksi, Integer> durasiCol;
    
    @FXML
    private Label meanLabel;
    
    @FXML
    private Label medianLabel;
    
    @FXML
    private Label modusLabel;
    
    @FXML
    private Label kuantitasLabel;
    
   
    
    private Stage dialogStage;
    private Transaksi transaksi;
    private ListTransaksi listTransaksi;
    private Perpusku mainApp;
    
    @FXML
    private void initialize() throws Exception {
        
        listTransaksi = new ListTransaksi();
        listTransaksi.read();
        transaksiTable.setItems(listTransaksi.getListTransaksi());
        
        
        idTransaksiCol.setCellValueFactory(cellData -> cellData.getValue().getIdTransaksi().asObject());
        namaDepanCol.setCellValueFactory(cellData -> cellData.getValue().getNamaDepan());
        namaBelakangCol.setCellValueFactory(cellData -> cellData.getValue().getNamaBelakang());
        judulCol.setCellValueFactory(cellData -> cellData.getValue().getJudul());
        meminjamCol.setCellValueFactory(cellData -> cellData.getValue().getMeminjam());
        pengembalianCol.setCellValueFactory(cellData -> cellData.getValue().getMengembalikan());
        durasiCol.setCellValueFactory(cellData -> cellData.getValue().getDurasi().asObject());
        
        showReport(listTransaksi);
    }
    @FXML
    private void handleBarchart() throws Exception{
        dialogStage = null;
        mainApp.showTransaksiReportBarchart();
    }
    @FXML
    private void handleTable() throws Exception{
        dialogStage = null;
        mainApp.showTransaksiReport();
    }
    @FXML
    private void handleTableInterval() throws Exception{
        dialogStage = null;
        mainApp.showTransaksiReport2();
    }
    @FXML
    private void handleCancel() {
        dialogStage= null;
        mainApp.initRootLayout();
        mainApp.showTransaksiOverview();
    }
    
    private void showReport(ListTransaksi listTransaksi){
        meanLabel.setText(Double.toString(listTransaksi.setMean()));
        modusLabel.setText(Integer.toString(listTransaksi.setModus()));
        medianLabel.setText(Double.toString(listTransaksi.setMedian()));
        kuantitasLabel.setText(Integer.toString(listTransaksi.ukuran()));
    }
    
    public void setAnggota(Transaksi transaksi) throws Exception{
        listTransaksi = new ListTransaksi();
        listTransaksi.read();
        
        transaksiTable.setItems(listTransaksi.getListTransaksi());
        
        idTransaksiCol.setCellValueFactory(cellData -> cellData.getValue().getIdTransaksi().asObject());
        namaDepanCol.setCellValueFactory(cellData -> cellData.getValue().getNamaDepan());
        namaBelakangCol.setCellValueFactory(cellData -> cellData.getValue().getNamaBelakang());
        judulCol.setCellValueFactory(cellData -> cellData.getValue().getJudul());
        meminjamCol.setCellValueFactory(cellData -> cellData.getValue().getMeminjam());
        pengembalianCol.setCellValueFactory(cellData -> cellData.getValue().getMengembalikan());
        durasiCol.setCellValueFactory(cellData -> cellData.getValue().getDurasi().asObject());
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
}
