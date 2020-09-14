/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.viewTransaksi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.ListReport;

import perpusku.model.Report;
import perpusku.model.Transaksi;

/**
 *
 * @author 17523220
 */
public class TableIntervalController {
    private Report report;
    private ListReport listReport;
    private Stage dialogStage;
    private Perpusku mainApp;
    
    @FXML
    private TableView<Report> transaksiTable;
    @FXML
    private TableColumn<Report, Integer> noCol;
    @FXML
    private TableColumn<Report, Integer> bbcol;
    @FXML
    private TableColumn<Report, Integer> bacol;

    @FXML
    private TableColumn<Report, Integer> frekuensiCol;
    @FXML
    private Label meanLabel;
    
    @FXML
    private Label medianLabel;
    
    @FXML
    private Label modusLabel;
    
    @FXML
    private Label kuantitasLabel;
    
    @FXML
    private void initialize() throws Exception {
        listReport = new ListReport();
        listReport.interval();
        listReport.frekuensi();
        transaksiTable.setItems(listReport.getListt());
        
        noCol.setCellValueFactory(cellData -> cellData.getValue().getNo().asObject());
        bbcol.setCellValueFactory(cellData -> cellData.getValue().getBB().asObject());
        bacol.setCellValueFactory(cellData -> cellData.getValue().getBA().asObject());
        frekuensiCol.setCellValueFactory(cellData -> cellData.getValue().getFrekuensi().asObject());
        showReport(listReport);
    }
    private void showReport(ListReport listReport){
        meanLabel.setText(Double.toString(listReport.setMean()));
        modusLabel.setText(Double.toString(listReport.setModus()));
        medianLabel.setText(Double.toString(listReport.setMedian()));
        kuantitasLabel.setText(Integer.toString(listReport.getListt().size()));
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
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
}
