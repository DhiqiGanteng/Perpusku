/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import perpusku.Perpusku;

import perpusku.model.Anggota;
import perpusku.model.ListAnggota;
import perpusku.model.ListTransaksi;



public class AnggotaEditDialogController {
    
    @FXML
    private TextField idNameField;
    @FXML
    private TextField namaDepanField;
    @FXML
    private TextField namaBelakangField;
    @FXML
    private TextField alamatField;
    @FXML
    private TextField umurField;
    
    
    private Stage dialogStage;
    private Anggota anggota;
    private ListAnggota list;
    private int i;
    private Perpusku mainApp;

    private boolean okClicked = false;
    
    @FXML
    private void initialize(){
        
    }
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    public void setAnggota(int i ,Anggota anggota,ListAnggota listAnggota){
        list = listAnggota;
        this.anggota = anggota;
        this.i=i;
        namaDepanField.setText(anggota.getNamaDepan2());
        namaBelakangField.setText(anggota.getNamaBelakang2());
        alamatField.setText(anggota.getAlamat2());
        umurField.setText(Integer.toString(anggota.getUmur2()));
       
    }
    
    public boolean isOkCliced(){
        return okClicked;
    }
    
    @FXML
    private void handleOk() throws Exception{
        if (isInputValid()) {
                int j = list.index(anggota);
                if(i==1)anggota = new Anggota();
                
                anggota.setIdNumber(Integer.parseInt(umurField.getText()));
                anggota.setNamaDepan(namaDepanField.getText());
                anggota.setNamaBelakang(namaBelakangField.getText());
                anggota.setAlamat(alamatField.getText());
                anggota.setUmur(Integer.parseInt(umurField.getText()));
                anggota.setDurasi2(0);
     
                okClicked = true;
                if(i==1){
                    list.addAnggota(anggota);
                    list.write();
                }else{
                    list.setAnggota(j, anggota);
                    list.write();
                }
                
                dialogStage = null;
                mainApp.initRootLayout();
                mainApp.showAnggotaOverview();
                
        }
       
    }
    
    
    @FXML
    private void handleCancel() throws Exception{
        dialogStage = null;
        mainApp.initRootLayout();
        mainApp.showAnggotaOverview();
    }
    
    private boolean isInputValid(){
        String errorMassage = "";
        int i = 0;
        if (namaDepanField.getText() == null || namaDepanField.getText().length() == 0){
            errorMassage += "No Valid Fisrt Name!\n";
        }
        if (namaBelakangField.getText() == null || namaBelakangField.getText().length() == 0){
            errorMassage += "No Valid LastName!\n";
        }
        if (alamatField.getText() == null || alamatField.getText().length() == 0){
            errorMassage += "No Valid Street!\n";
        }
        if (umurField.getText() == null || umurField.getText().length() == 0){
            errorMassage += "No Valid Age!\n";
        }else {
            try {
                Integer.parseInt(umurField.getText());
            }catch (NumberFormatException e){
                errorMassage += "No Valid Age (must be an Integer)!\n";
            }try{
                if (Integer.parseInt(umurField.getText())<=0)
                errorMassage += "No Valid Age (must be > 0)!\n";
            }catch (NumberFormatException e){
                errorMassage += "No Valid Age (must be an Integer)!\n";
        
            }
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
        
    public void setMainApp(Perpusku mainApp) {
        this.mainApp = mainApp;
    }
}
