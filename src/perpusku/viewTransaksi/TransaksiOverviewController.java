/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.viewTransaksi;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.Anggota;
import perpusku.model.DateUtil;
import perpusku.model.ListAnggota;
import perpusku.model.ListBuku;
import perpusku.model.ListTransaksi;
import perpusku.model.Transaksi;

public class TransaksiOverviewController {
    private Transaksi transaksi;
    private Transaksi transaksi2;
    private ListTransaksi listTransaksi;
    private Perpusku mainApp;
    @FXML
    private TableView<Transaksi> transaksiTable;
    @FXML
    private TableColumn<Transaksi, Integer> idTransaksiCol;
    @FXML
    private TableColumn<Transaksi, String> namaDepanCol;
    @FXML
    private TableColumn<Transaksi, String> judulCol;
    
    @FXML
    private Label idTransaksiLabel;
    @FXML
    private Label idAnggotaLabel;
    @FXML
    private Label namaDepanLabel;
    @FXML
    private Label namaBelakangLabel;
    @FXML
    private Label idBukuLabel;
    @FXML
    private Label judulLabel;
    @FXML
    private Label penulisLabel;
    @FXML
    private Label meminjamLabel;
    @FXML
    private Label mengembalikanLabel;
    @FXML
    private Label durasiLabel;
    
    @FXML
    private TextField idNumberField;
    @FXML
    private TextField idBukuField;
    @FXML
    private DatePicker meminjamField;
    @FXML
    private DatePicker mengembalikanField;
    
    private boolean okClicked = false;
    private Stage primaryStage;
    private DateUtil dateUtil;
    private ListAnggota listAnggota;
    private ListBuku listBuku;
    private Anggota anggota;
    public TransaksiOverviewController(){
    
    }
    
    @FXML
    private void initialize() throws Exception {
        
        
        listTransaksi = new ListTransaksi();
        listAnggota = new ListAnggota();
        listBuku = new ListBuku();
        listAnggota.read();
        listBuku.read();
        listTransaksi.read();
        transaksiTable.setItems(listTransaksi.getListTransaksi());
        
        // Initialize the person table with the two columns
        idTransaksiCol.setCellValueFactory(cellData -> cellData.getValue().getIdTransaksi().asObject());
        namaDepanCol.setCellValueFactory(cellData -> cellData.getValue().getNamaDepan());
        judulCol.setCellValueFactory(cellData -> cellData.getValue().getJudul());
        
        showTransaksiDetails(null);
        
        
        transaksiTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showTransaksiDetails(newValue));
    }
    
    @FXML
    private void handleDeleteTransaksi() throws Exception {
    Transaksi selectedIndex = null ;
    selectedIndex = transaksiTable.getSelectionModel().getSelectedItem();
    
    if (selectedIndex != null) {
        int i = listTransaksi.index(selectedIndex);
        listTransaksi.hapus(i);
        listTransaksi.write();
        
    } else if (selectedIndex == null){
        
        Alert alert = new Alert(AlertType.WARNING);
        
        alert.setTitle("No Selection");
        alert.setHeaderText("No Transaction Selected");
        alert.setContentText("Please select a Transaction in the table.");

        alert.showAndWait();
        
        
    }
    }
    
    @FXML
    private void handleNewTransaksi() throws Exception{
        if (isInputValid()) {
            

        // Add some action (in Java 8 lambda syntax style).
            meminjamField.setOnAction(event -> {
            LocalDate date = meminjamField.getValue();
            System.out.println("Selected date: " + date);});

            mengembalikanField.setOnAction(event -> {
            LocalDate date = mengembalikanField.getValue();
            System.out.println("Selected date: " + date);});
            
            
            
            
            
            
            listAnggota.search(Integer.parseInt(idNumberField.getText()));
            listBuku.search(Integer.parseInt(idBukuField.getText()));
            if (listAnggota.getAdaAnggota()==true){
                if(listBuku.getAdaBuku()== true){

                    Transaksi tempTransaksi = new Transaksi();
                    tempTransaksi.setIdTransaksi();
                    tempTransaksi.setIdNumber(Integer.parseInt(idNumberField.getText()));
                    tempTransaksi.setNamaDepan(Integer.parseInt(idNumberField.getText()));
                    tempTransaksi.setNamaBelakang(Integer.parseInt(idNumberField.getText()));
                    tempTransaksi.setIdBuku(Integer.parseInt(idBukuField.getText()));
                    tempTransaksi.setJudul(Integer.parseInt(idBukuField.getText()));
                    tempTransaksi.setPenulis(Integer.parseInt(idBukuField.getText()));
                    tempTransaksi.setMeminjam(meminjamField.getValue());
                    if(mengembalikanField.getValue()!=null){
                        tempTransaksi.setMengembalikan(mengembalikanField.getValue());
                        tempTransaksi.setDurasi(tempTransaksi.getDurMeminjam(),tempTransaksi.getDurMengembalikan());
                    }else {
                        tempTransaksi.setMengembalikan(null);
                        tempTransaksi.setDurasi(0,0);
                    }
                    
                    okClicked = true;
                    if(okClicked){
                        listTransaksi.addTransaksi(tempTransaksi);
                        listTransaksi.write();
                        transaksiTable.setItems(listTransaksi.getListTransaksi());
                    }
                }else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Not Found");
                    alert.setHeaderText("id of book not found");
                    alert.setContentText("Please Correct Input Book id.");
            
                    alert.showAndWait();
                
                }
            }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Not Found");
                alert.setHeaderText("id Anggota not found");
                alert.setContentText("Please Correct Input Id Anggota.");
            
                alert.showAndWait();
            }
    
        
        
    }
    }
    
    @FXML
    private void handleEditTransaksi() throws Exception{
        if(transaksi2 != null){
            if (isInputValid()) {
                
                int i = listTransaksi.index(transaksi2);
                
                
            listAnggota.search(Integer.parseInt(idNumberField.getText()));
            listBuku.search(Integer.parseInt(idBukuField.getText()));
            if (listAnggota.getAdaAnggota()==true){
                if(listBuku.getAdaBuku()==true){
                    transaksi2.setIdTransaksi();
                    transaksi2.setIdNumber(Integer.parseInt(idNumberField.getText()));
                    transaksi2.setNamaDepan(Integer.parseInt(idNumberField.getText()));
                    transaksi2.setNamaBelakang(Integer.parseInt(idNumberField.getText()));
                    transaksi2.setIdBuku(Integer.parseInt(idBukuField.getText()));
                    transaksi2.setJudul(Integer.parseInt(idBukuField.getText()));
                    transaksi2.setPenulis(Integer.parseInt(idBukuField.getText()));
                    transaksi2.setMeminjam(meminjamField.getValue());
                    if (mengembalikanField.getValue()!=null){
                                transaksi2.setMengembalikan(mengembalikanField.getValue());
                                transaksi2.setDurasi(transaksi2.getDurMeminjam(),transaksi.getDurMengembalikan());
                    }else if (mengembalikanField.getValue()==null) {
                        transaksi2.setMengembalikan(null);
                        transaksi2.setDurasi(0,0);
                    }
                    listTransaksi.setTransaksi(i, transaksi2);
                    listTransaksi.write();
                    transaksiTable.setItems(listTransaksi.getListTransaksi());
                    showTransaksiDetails(transaksi2);
            }else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Not Found");
                    alert.setHeaderText("IdBook not found");
                    alert.setContentText("Please Correct Input IdBook.");
            
                    alert.showAndWait();
                
                }
            }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Not Found");
                alert.setHeaderText("idAnggota not found");
                alert.setContentText("Please Correct Input IdAnggota.");
            
                alert.showAndWait();
            }
        }
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Transaction Selected");
            alert.setContentText("Please selected a transaction in the table and press edit.");
            
            alert.showAndWait();
        }
    } 
    
    @FXML
    private void handleReport() throws Exception{
        primaryStage = null;
        Transaksi tempTransaksi = new Transaksi();
        mainApp.showTransaksiReport();
    }
    
    @FXML
    private void handleEditPerson(){

        Transaksi selectedAnggota = transaksiTable.getSelectionModel().getSelectedItem();
        boolean okClicked = true;
        if (selectedAnggota != null){
            setAnggota(selectedAnggota);
            transaksi2 = selectedAnggota;
        }else {
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Anggota Selected");
            alert.setContentText("Please selected a anggota in the table.");
            
            alert.showAndWait();
        }
    }
    
    
    private void showTransaksiDetails(Transaksi transaksi){
        
        if (transaksi != null){
            idTransaksiLabel.setText(Integer.toString(transaksi.getIdTransaksi2()));
            idAnggotaLabel.setText(Integer.toString(transaksi.getIdNumber2()));
            namaDepanLabel.setText(transaksi.getNamaDepan2());
            namaBelakangLabel.setText(transaksi.getNamaBelakang2());
            idBukuLabel.setText(Integer.toString(transaksi.getIdBuku2()));
            judulLabel.setText(transaksi.getJudul2());
            penulisLabel.setText(transaksi.getPenulis2());
            meminjamLabel.setText(DateUtil.format(transaksi.getMeminjam2()));
            
            
            
            if (transaksi.getMengembalikan()!=null)mengembalikanLabel.setText(DateUtil.format(transaksi.getMengembalikan2()));
            else if(transaksi.getMengembalikan()==null) mengembalikanLabel.setText("-");
            if (transaksi.getDurasi2()!= 0)durasiLabel.setText(Integer.toString(transaksi.getDurasi2()));
            else durasiLabel.setText("-");
        }else {
            idTransaksiLabel.setText("");
            idAnggotaLabel.setText("");
            namaDepanLabel.setText("");
            namaBelakangLabel.setText("");
            idBukuLabel.setText("");
            judulLabel.setText("");
            penulisLabel.setText("");
            meminjamLabel.setText("");
            mengembalikanLabel.setText("");
            durasiLabel.setText("");
        }
    }
    public void setAnggota(Transaksi transaksi){
        
        this.transaksi = transaksi;
        idNumberField.setText(Integer.toString(transaksi.getIdNumber2()));
        idBukuField.setText(Integer.toString(transaksi.getIdBuku2()));
        meminjamField.setValue(transaksi.getMeminjam2());
        if(transaksi.getMeminjam2() != null){
            mengembalikanField.setValue(transaksi.getMengembalikan2());
        }
    }
    
    public boolean isOkCliced(){
        return okClicked;
    }
    
    

    
    private boolean isInputValid(){
        String errorMassage = "";
        
        if (idNumberField.getText() == null || idNumberField.getText().length() == 0){
            errorMassage += "No Valid idnumber!\n";
        }else {
            try {
                Integer.parseInt(idNumberField.getText());
                
            }catch (NumberFormatException e){
                errorMassage += "No Valid id number (must be an Integer)!\n";
            }
        }
        if (idBukuField.getText() == null || idBukuField.getText().length() == 0){
            errorMassage += "No Valid BookID!\n";
        }else {
            try {
                Integer.parseInt(idBukuField.getText());
            }catch (NumberFormatException e){
                errorMassage += "No Valid BookID (must be an Integer)!\n";
            }
        }
        if (meminjamField.getValue() == null){
            errorMassage += "No Valid Date!\n";
        }
        if (errorMassage.length() == 0){
            return true;
        }else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMassage);
            
            alert.showAndWait();
            return false;
        }
       }
        
    
    public void setPerpusku(ListTransaksi listTransaksi) throws Exception{
        this.listTransaksi = listTransaksi;
        this.listTransaksi.read();
        transaksiTable.setItems(listTransaksi.getListTransaksi());
        
    }
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
}
