/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.viewBuku;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import perpusku.Perpusku;
import perpusku.model.Buku;
import perpusku.model.ListBuku;
public class BukuEditDialogController {
    @FXML
    private TextField idBukuField;
    @FXML
    private TextField judulField;
    @FXML
    private TextField penulisField;
    
    
    private Buku buku;
    private ListBuku listBuku;
    private Stage dialogStage;
    private Perpusku mainApp;
    private int i;
    private boolean okClicked = false;
    
    @FXML
    private void initialize(){
        
    }
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    public void setBuku(int i,Buku buku,ListBuku listBuku){
        this.listBuku = listBuku;
        this.buku = buku;
        this.i=i;
        judulField.setText(buku.getJudul2());
        penulisField.setText(buku.getPenulis2());
        
 
        
        
    }
    
    public boolean isOkCliced(){
        return okClicked;
    }
    
    @FXML
    private void handleOk() throws Exception{
       if (isInputValid()) {
        int j = listBuku.index(buku);
        if(i==1)buku = new Buku();
        buku.setIdBuku();
        buku.setJudul(judulField.getText());
        buku.setPenulis(penulisField.getText());
        buku.setDurasi2(0);
        if(i==1){
            listBuku.addBuku(buku);
            listBuku.write();
        }else{
            listBuku.setBuku(i, buku);
            listBuku.write();
        }       
        okClicked = true;
        dialogStage = null;
        mainApp.initRootLayout();
        mainApp.showBukuOverview();
       }
    }
    
    @FXML
    private void handleCancel() throws Exception{
        dialogStage = null;
        mainApp.initRootLayout();
        mainApp.showBukuOverview();
        
    }
    
    private boolean isInputValid(){
        String errorMassage = "";
        
        if (judulField.getText() == null || judulField.getText().length() == 0){
            errorMassage += "No Valid Title!\n";
        }
        if (penulisField.getText() == null || penulisField.getText().length() == 0){
            errorMassage += "No Valid Writer!\n";
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
