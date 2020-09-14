/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Anggota {
    private IntegerProperty idNumber;
    private StringProperty namaDepan;
    private StringProperty namaBelakang;
    private StringProperty alamat;
    private IntegerProperty umur;
    private IntegerProperty durasi;
    private Transaksi transaksi;
    private ListTransaksi listTransaksi;
    
    
    
    
    public Anggota(String nd ,String nb,String al ,int age){
        
        this.idNumber = new SimpleIntegerProperty(175000000+(0 + (int) (Math.random() * 1000)+(age*10000)));
        this.namaDepan = new SimpleStringProperty(nd);
        this.namaBelakang = new SimpleStringProperty(nb);
        this.alamat = new SimpleStringProperty(al);
        this.umur = new SimpleIntegerProperty(age);
        this.durasi = new SimpleIntegerProperty(0);
    }
    
    public Anggota(){
        this("","","",0);
    }
    
    public IntegerProperty getIdNumber(){
        return idNumber;
    }
    public void setIdNumber2(int age){
        idNumber.set(age);
    }
    public void setIdNumber(int age){
        if (idNumber ==null){ 
            idNumber.set(175000000+(0 + (int) (Math.random() * 1000)+(age*10000)));
        }else{
            idNumber.get();
        }
    }
    public int getIdNumber2(){
        return idNumber.get();
    }
    
    public StringProperty getNamaDepan(){
        return namaDepan;
    }
    
    public void setNamaDepan(String nd){
        namaDepan.set(nd);
        
    }
    public String getNamaDepan2(){
        return namaDepan.get();
    }
    
    
    public StringProperty getNamaBelakang(){
        return namaBelakang;
    }
    
    public void setNamaBelakang(String nb){
        namaBelakang.set(nb);
        
    }
    public String getNamaBelakang2(){
        return namaBelakang.get();
    }
    
    public StringProperty getAlamat(){
        return alamat;
    }
    
    public void setAlamat(String al){
        alamat.set(al);
        
    }
    public String getAlamat2(){
        return alamat.get();
    }
    
    public IntegerProperty getUmur(){
        return umur;
    }
     public int getUmur2(){
        return umur.get();
    }
    
    public void setUmur(int age){
        umur.set(age);
        
    }
    
    public IntegerProperty getDurasi(){
        return durasi;
    }
    public void setDurasi2(int i){
        durasi.set(i);
    }
    public void setDurasi() throws Exception{
        listTransaksi = new ListTransaksi();
        listTransaksi.read();
        int du = 0;
        for (int i = 0 ; i<listTransaksi.ukuran();i++){
            if (idNumber == listTransaksi.getTransaksi(i).getIdNumber()){
                du = du + listTransaksi.getTransaksi(i).getDurasi2();
            }
        }
        durasi.set(du);
        
    }
    
    public int getDurasi2(){
        return durasi.get();
    }
}
