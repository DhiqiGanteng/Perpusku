/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import perpusku.model.Anggota;

import perpusku.model.Buku;
import perpusku.model.ListAnggota;
import perpusku.model.ListBuku;
import perpusku.model.Transaksi;
import perpusku.view.AnggotaController;
import perpusku.view.AnggotaEditDialogController;
import perpusku.view.AnggotaReportOverviewController;
import perpusku.view.MenuOverviewController;
import perpusku.view.RootMenuController;
import perpusku.viewBuku.BukuEditDialogController;
import perpusku.viewBuku.BukuOverviewController;
import perpusku.viewBuku.BukuReportOverviewController;
import perpusku.viewTransaksi.ReportBarchartController;
import perpusku.viewTransaksi.TableIntervalController;
import perpusku.viewTransaksi.TransaksiOverviewController;
import perpusku.viewTransaksi.TransaksiReportController;


/**
 *
 * @author 17523220
 */
public class Perpusku extends Application {
    private ListAnggota list;
    
    private Anggota anggota;
    private ListBuku listBuku;
    private Buku buku;
    private Transaksi transaksi;
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    
    public Perpusku(){
      
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        
        initRootLayout();
        showMenu();
       
    }
    
    
    public void initRootLayout() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Perpusku.class.getResource("view/RootMenu.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("LibararyApp");
            RootMenuController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showMenu() {
        try{
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(Perpusku.class.getResource("view/MenuOverview.fxml"));
        AnchorPane menuOverview = (AnchorPane) loader.load();
        
        MenuOverviewController controller = loader.getController();
        controller.setMainApp(this);
        rootLayout.setCenter(menuOverview);
        
        
        }catch (IOException e){
            e.printStackTrace();
        }
        
            
    }
    public void showAnggotaOverview() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Perpusku.class.getResource("view/AnggotaOverview.fxml"));
            AnchorPane anggotaOverview = (AnchorPane) loader.load();
            
            
            rootLayout.setCenter(anggotaOverview);
            AnggotaController controller = loader.getController();
            controller.setPerpusku(list);
            controller.setMainApp(this);
            
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean showAnggotaEditDialog(int i,Anggota anggota, ListAnggota list){
       try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Perpusku.class.getResource("view/AnggotaEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            
            AnggotaEditDialogController controller = loader.getController();
            controller.setDialogStage(primaryStage);
            controller.setAnggota(i,anggota,list);
            controller.setMainApp(this);
            primaryStage.show();
            
            return controller.isOkCliced();
            } catch (IOException e){
            e.printStackTrace();
            return false;
            }
    }
    
    public boolean showAnggotaReport() throws Exception {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Perpusku.class.getResource("view/AnggotaReportOverview.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        AnggotaReportOverviewController controller = loader.getController();
        controller.setDialogStage(primaryStage);
        controller.setAnggota(anggota);
        controller.setMainApp(this);

        // Show the dialog and wait until the user closes it
        primaryStage.show();

        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
    
    
    
    
    public void showBukuOverview() throws Exception{
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Perpusku.class.getResource("viewBuku/BukuOverview.fxml"));
            AnchorPane bukuOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(bukuOverview);
            BukuOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean showBukuEditDialog(int i,Buku buku,ListBuku listBuku){
       try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Perpusku.class.getResource("viewBuku/BukuEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            
            BukuEditDialogController controller = loader.getController();
            controller.setDialogStage(primaryStage);
            controller.setBuku(i,buku,listBuku);
            controller.setMainApp(this);
            primaryStage.show();
            
            return controller.isOkCliced();
            } catch (IOException e){
            e.printStackTrace();
            return false;
            }
    }
    
    public boolean showBukuReport() throws Exception {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Perpusku.class.getResource("viewBuku/BukuReportOverview.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        BukuReportOverviewController controller = loader.getController();
        controller.setDialogStage(primaryStage);
        controller.setAnggota(buku);
        controller.setMainApp(this);
        // Show the dialog and wait until the user closes it
        primaryStage.show();

        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
    
    
    
    public void showTransaksiOverview(){
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Perpusku.class.getResource("viewTransaksi/TransaksiOverview.fxml"));
        AnchorPane transaksiOverview = (AnchorPane) loader.load();
        
        TransaksiOverviewController controller = loader.getController();
        controller.setMainApp(this);
        rootLayout.setCenter(transaksiOverview);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean showTransaksiReport() throws Exception {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Perpusku.class.getResource("viewTransaksi/TransaksiReport.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        TransaksiReportController controller = loader.getController();
        controller.setDialogStage(primaryStage);
        controller.setAnggota(transaksi);
        controller.setMainApp(this);
        // Show the dialog and wait until the user closes it
        primaryStage.show();

        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    public boolean showTransaksiReportBarchart() throws Exception  {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Perpusku.class.getResource("viewTransaksi/ReportBarchart.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        ReportBarchartController controller = loader.getController();
        controller.setDialogStage(primaryStage);
        controller.setAnggota(transaksi);
        controller.setMainApp(this);
        // Show the dialog and wait until the user closes it
        primaryStage.show();

        return true;
        } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public boolean showTransaksiReport2() throws Exception  {
        try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Perpusku.class.getResource("viewTransaksi/TableInterval.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        TableIntervalController controller = loader.getController();
        controller.setDialogStage(primaryStage);
        controller.setMainApp(this);
        // Show the dialog and wait until the user closes it
        primaryStage.show();

        return true;
        } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    }
    
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    public BorderPane getRootLayout(){
        return rootLayout;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
