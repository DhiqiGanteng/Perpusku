/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.model;

import java.time.LocalDate;
import java.time.Month;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaksi {
    private IntegerProperty idTransaksi;
    private IntegerProperty idNumber;
    private StringProperty namaDepan;
    private StringProperty namaBelakang;
    private IntegerProperty idBuku;
    private StringProperty judul;
    private StringProperty penulis;
    private ObjectProperty<LocalDate> meminjam;
    private ObjectProperty<LocalDate> mengembalikan;
    
    private IntegerProperty durasi;
    
    
    private int durMengembalikan,durMeminjam;
    private Anggota anggota;
    private Buku buku;
    private ListAnggota list;
    private ListBuku listBuku;
    private boolean ada;
    
    
    public Transaksi(int id,int idB,LocalDate date,LocalDate date2) throws Exception{
        // Mencari Anggota dengan id yang ada
        searchAnggota(id);
        
        //mencari Buku dengan id yang ada
        searchBuku(idB);
       
        
        
        
        
        
        idNumber = new SimpleIntegerProperty(id);
        idTransaksi = new SimpleIntegerProperty(175000000+(0 + (int) (Math.random() * 1000)));
        namaDepan = new SimpleStringProperty(anggota.getNamaDepan2());
        namaBelakang = new SimpleStringProperty(anggota.getNamaBelakang2());
        idBuku = new SimpleIntegerProperty(idB);
        judul = new SimpleStringProperty(buku.getJudul2());
        penulis = new SimpleStringProperty(buku.getPenulis2());
        meminjam = new SimpleObjectProperty<LocalDate>(date);
        if (date2!=null){
            mengembalikan = new SimpleObjectProperty<LocalDate>(date2);
            durMeminjam =   date.getYear()*265+date.getMonthValue()*30+date.getDayOfMonth();
            durMengembalikan = date2.getYear()*265+date2.getMonthValue()*30+date2.getDayOfMonth();
            durasi = new SimpleIntegerProperty(durMengembalikan-durMeminjam);
        }else{
            mengembalikan = new SimpleObjectProperty<LocalDate>(null);
            durasi = new SimpleIntegerProperty(0);
        }
        
        
        
    }
    public Transaksi() throws Exception{
        this(175250580,1750201,LocalDate.of(2018,6,7),LocalDate.of(2018,6,9));
        
    }
    
    public void searchAnggota(int id) throws Exception{
        anggota = new Anggota();
        list = new ListAnggota();
        list.read();
        list.getListAnggota();
        anggota= list.search(id);
    }
    public void searchBuku(int id) throws Exception{
        buku = new Buku();
        listBuku = new ListBuku();
        listBuku.read();
        listBuku.getListBuku();
        buku = listBuku.search(id);
       
    }
    public IntegerProperty getIdTransaksi(){
        return idTransaksi;
    }
    public void setIdTransaksi(int i){
        idTransaksi.set(i);
    }
    public void setIdTransaksi(){
        if (idTransaksi == null){
         idTransaksi.set(175000000+(0 + (int) (Math.random() * 1000)));
        }else {
            idTransaksi.get();
        }
        
    }

    public int getIdTransaksi2(){
        return idTransaksi.get();
    }
    
    public IntegerProperty getIdNumber(){
        return idNumber;
    }
    
    public void setIdNumber(int id){
         idNumber.set(id);
    }

    public int getIdNumber2(){
        
        return idNumber.get();
    }
    
    public StringProperty getNamaDepan(){
        return namaDepan;
    }
    
    public void setNamaDepan(int id) throws Exception{
        anggota = new Anggota();
        list = new ListAnggota();
        list.read();
        list.getListAnggota();
        anggota= list.search(id);
        namaDepan.set(anggota.getNamaDepan2());
        
    }
    public String getNamaDepan2(){
        return namaDepan.get();
    }
    
    public StringProperty getNamaBelakang(){
        return namaBelakang;
    }
    
    public void setNamaBelakang(int id) throws Exception{
        searchAnggota(id);
        namaBelakang.set(anggota.getNamaBelakang2());
        
    }
    public String getNamaBelakang2(){
        return namaBelakang.get();
    }
    public IntegerProperty getIdBuku(){
        return idBuku;
    }
    public void setIdBuku(int id){
        idBuku.set(id);
        
    }
    public int getIdBuku2(){
        return idBuku.get();
    }
    
    public StringProperty getJudul(){
        return judul;
    }
    
    public void setJudul(int id) throws Exception{
        searchBuku(id);
        judul.set(buku.getJudul2());
        
    }
    public String getJudul2(){
        return judul.get();
    
    }
    
    public StringProperty getPenulis(){
        return penulis;
    }
    
    public void setPenulis(int id) throws Exception{
        searchBuku(id);
        penulis.set(buku.getPenulis2());
        
    }
    public String getPenulis2(){
        return penulis.get();
    }
    
    public LocalDate getMeminjam2(){
        return meminjam.get();
        
    }
    
    public void setMeminjam(LocalDate date){
        this.meminjam.set(date);
    }
    public ObjectProperty<LocalDate> getMeminjam(){
        return meminjam;
    }
    
    public LocalDate getMengembalikan2(){
        return mengembalikan.get();
        
    }
    
    public void setMengembalikan(LocalDate date){
        if (date != null) mengembalikan.set(date);
        else if (date == null)mengembalikan.set(null);
    }
    public ObjectProperty<LocalDate> getMengembalikan(){
        return mengembalikan;
    }

    public int getDurMeminjam(){
        return durMeminjam = meminjam.get().getYear()*265+meminjam.get().getMonthValue()*30+meminjam.get().getDayOfMonth();
    }
    
    public int getDurMengembalikan(){
        return durMengembalikan = mengembalikan.get().getYear()*265+mengembalikan.get().getMonthValue()*30+mengembalikan.get().getDayOfMonth();
    }
    
    public void setDurasi(int m,int p){
        if (p==0)durasi.set(0);
        else durasi.set(p-m);
    }
    public int getDurasi2(){
        return durasi.get();
    }
    public IntegerProperty getDurasi(){
        return durasi;
    }
    
    public boolean getAda(){
        return ada;
    }
}
