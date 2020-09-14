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

public class Buku {
    private IntegerProperty idBuku;
    private StringProperty judul;
    private StringProperty penulis;
    private IntegerProperty durasi;
  
    
    
    
    public Buku(String jd ,String pn){
        this.idBuku = new SimpleIntegerProperty( 1750000+(0 + (int) (Math.random() * 1000)));
        this.judul = new SimpleStringProperty(jd);
        this.penulis = new SimpleStringProperty(pn);
        this.durasi = new SimpleIntegerProperty(0);
    }
    
    public Buku(){
        this("","");
    }
    
    public IntegerProperty getIdBuku(){
        return idBuku;
    }
    public void setIdBuku(int i){
        idBuku.set(i);
    }
    public void setIdBuku(){
        if (idBuku == null) {
            idBuku.set(1750000+(0 + (int) (Math.random() * 1000)));
        }else{
            idBuku.get();
        }
    }

    public int getIdBuku2(){
        return idBuku.get();
    }
    
    public StringProperty getJudul(){
        return judul;
    }
    
    public void setJudul(String jd){
        judul.set(jd);
        
    }
    public String getJudul2(){
        return judul.get();
    }
    
    public StringProperty getPenulis(){
        return penulis;
    }
    
    public void setPenulis(String pn){
        penulis.set(pn);
        
    }
    public String getPenulis2(){
        return penulis.get();
    }
    
    public IntegerProperty getDurasi(){
        return durasi;
    }
    
    public void setDurasi2(int du){
         durasi.set(du);
    }
    public void setDurasi() throws Exception{
        ListTransaksi listTransaksi = new ListTransaksi();
        listTransaksi.read();
        int du = 0;
        for (int i = 0 ; i<listTransaksi.ukuran();i++){
            if (idBuku == listTransaksi.getTransaksi(i).getIdBuku()){
                du = du + listTransaksi.getTransaksi(i).getDurasi2();
            }
        }
        durasi.set(du);
        
    }
    public int getDurasi2(){
        return durasi.get();
    }
}
