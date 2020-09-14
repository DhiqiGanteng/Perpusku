/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.viewTransaksi;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.ListTransaksi;
import perpusku.model.Transaksi;

/**
 *
 * @author 17523220
 */
public class ReportBarchartController {
    private ListTransaksi listTransaksi;
    private Stage dialogStage;
    private Perpusku mainApp;
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;
    
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
        
        listTransaksi = new ListTransaksi();
        listTransaksi.read();
        
        setTransaksiChart();
        xAxis.setCategories(listTransaksi.getBarchart());
        
        showReport(listTransaksi);
        
        
    }
    public void setTransaksiChart(){
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        listTransaksi.setBulan();
        listTransaksi.setBarChart();
        
        for (int i = 0; i < 12; i++) {
            series.getData().add(new XYChart.Data<>(listTransaksi.getBarchart(i), listTransaksi.getBulan(i)));
        }

        barChart.getData().add(series);
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
    private void handleCencel(){
        dialogStage = null;
        mainApp.initRootLayout();
        mainApp.showTransaksiOverview();
    }
    private void showReport(ListTransaksi listTransaksi){
        meanLabel.setText(Double.toString(listTransaksi.setMean()));
        modusLabel.setText(Integer.toString(listTransaksi.setModus()));
        medianLabel.setText(Double.toString(listTransaksi.setMedian()));
        kuantitasLabel.setText(Integer.toString(listTransaksi.ukuran()));
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setAnggota(Transaksi transaksi) throws Exception{
        listTransaksi = new ListTransaksi();
        listTransaksi.read();
    }
    
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
}
