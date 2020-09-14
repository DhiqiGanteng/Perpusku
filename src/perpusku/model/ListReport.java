/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 17523220
 */
public class ListReport {
    private ObservableList<Report> list;
    private Report report;
    private ObservableList<Integer> durasi;
     private ListTransaksi listTransaksi;
    private Transaksi transaksi;
    
    public ListReport(){
        list = FXCollections.observableArrayList();
        durasi = FXCollections.observableArrayList();
    }
    
    public ObservableList<Report> getListt(){
        return list;
    }  
    
    public void setReport(int i,Report report){
      
    }
    public void setDurasi() throws Exception{
        listTransaksi = new ListTransaksi();
        listTransaksi.read();
        int i = 0;
        while(i<listTransaksi.ukuran()){
                transaksi = listTransaksi.getTransaksi(i);
                durasi.add( transaksi.getDurasi2());
                
            
            i++;
        }
    }
    public void urut() throws Exception{
        setDurasi();
        int j=0;
        int m = 0;
        ObservableList<Integer> temp = FXCollections.observableArrayList(); 
        for (int i = 0;i<durasi.size();i++){
            for (int k = 0;k<durasi.size()-1;k++){ 
                if (durasi.get(k)>durasi.get(k+1)){
                    m = k+1;
                    j=durasi.get(k);
                    durasi.set(k,durasi.get(m));
                    durasi.set(m,j);
                }
            }
        }
    }
    public void interval() throws Exception{
        urut();
        int terbesar = durasi.get(durasi.size()-1);
        int terkecil = durasi.get(0);
        int rentang =  terbesar- terkecil;
        
        double banyak_kelas = (1 +(3.3)*Math.log(durasi.size()))/2;
        
        
        int panjang_kelas = (int) (rentang/banyak_kelas);
        
        for(int i =0; i< banyak_kelas; i++)
            {
                int batasBawah;
                report = new Report();
                if(list.size() == 0)
                {
                    batasBawah = durasi.get(0);
                    report.setBB(batasBawah);
                    report.setBA(batasBawah + panjang_kelas);
                }
                else
                {
                    batasBawah = list.get(list.size()-1).getBA().get() + 1;
                    report.setBB(batasBawah);
                    report.setBA(batasBawah + panjang_kelas);
                }
                report.setNo(i+1);
                list.add(new Report(report.getNo().get(),0,report.getBB().get(),report.getBA().get()));
            }
    }
    public void frekuensi(){
        for (Report report :list){
                int p = 0;
                for (Integer angka: durasi)
                {
                    if(angka>= report.getBB().get() && angka <= report.getBA().get())  
                    p=p+1;
                }
                report.setFrekuensi(p);
            
        }
    }
    public double setMean(){
        
        
        int jml = 0;
        int n = 0;
        for (int i = 0;i<list.size();i++){
            int temp = 0;
            temp = (list.get(i).getBA().get() + list.get(i).getBB().get())/2;
            
            temp = temp*list.get(i).getFrekuensi().get();
           
            jml = jml + temp;
           
            n = n + list.get(i).getFrekuensi().get();
            
        }
        return jml/n;
    }
    
    public double setMedian(){
        double Lo = 0;
        int fm = 0;
        int c = 0;
        int cek = list.get(0).getFrekuensi().get();
        int index=0;
       
        int n = 0;
        for(int i = 0;i<list.size();i++){
            n = n+list.get(i).getFrekuensi().get();
        }
        n=n/2;
        while(n>cek){
            index++;
            cek=cek+list.get(index).getFrekuensi().get();
            
        }
        Lo = list.get(index).getBB().get() - 0.5;
       
        c = list.get(index+1).getBB().get() - list.get(index).getBB().get();
     
        cek = cek -list.get(index).getFrekuensi().get();
        
        fm = list.get(index).getFrekuensi().get();
       
        double median = Lo + (c*( (double) (n-cek)/fm));
        
        return median;
    }
    
    public double setModus(){
        double c = 0;
        int cek = 0;
        int fm = 0;
        int index = 0;
        int f1 = 0;
        int f2 =0;
        for(int i=0;i<list.size();i++){
            if (c<list.get(i).getFrekuensi().get()){
                c = list.get(i).getFrekuensi().get();
                fm =list.get(i).getFrekuensi().get();
                index = i;
            }
        }
        System.out.println("fm :"+fm);
        System.out.println("index :"+index);
        
        for(int i=0;i<index;i++){
            f1 = f1+list.get(i).getFrekuensi().get();
        }
        System.out.println("f1 :"+f1);
        for(int i=index+1;i<list.size();i++){
            f2 = f2+list.get(i).getFrekuensi().get();
        }
        System.out.println("f2 :"+f2);
        double Lo= list.get(index).getBB().get()-0.5;
        System.out.println("Lo :"+Lo);
        double La= list.get(index).getBA().get()+0.5;
        c = La-Lo;
        System.out.println("c :"+c);
        f1 = Math.abs(fm-f1);
        f2 = Math.abs(fm-f2);
        double modus = Lo + c*((double)f1/(f1+f2));
        
        return modus;
    }
}
