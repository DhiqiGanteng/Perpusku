/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.model;


import java.io.File;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.Locale;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ListTransaksi {
    private ObservableList<Transaksi> listTransaksi;
    private ObservableList<Integer> durasiAll;
    private ObservableList<String> monthNames;
    private String extFile;
    private int[] monthCounter;
    private ListAnggota listAnggota;
    private Transaksi transaksi;
    
    public ListTransaksi() {
        listTransaksi = FXCollections.observableArrayList();
        durasiAll = FXCollections.observableArrayList();   
        monthNames = FXCollections.observableArrayList();
        monthCounter = new int[12];
        extFile = "transaksi.xml";
    }
    
     public ObservableList<Transaksi> getListTransaksi(){
        return listTransaksi;
    }
    public void setTran() throws Exception{
        listTransaksi.add(new Transaksi(175250580,1750201,LocalDate.of(2018,5,2),LocalDate.of(2018,5,14)));
        listTransaksi.add(new Transaksi(175170089,1750289,LocalDate.of(2018,5,7),LocalDate.of(2018,5,9)));
        listTransaksi.add(new Transaksi(175270320,1750100,LocalDate.of(2018,6,5),LocalDate.of(2018,6,27)));
        listTransaksi.add(new Transaksi(175290564,1750153,LocalDate.of(2018,7,20),LocalDate.of(2018,7,27)));
        listTransaksi.add(new Transaksi(175120385,1750889,LocalDate.of(2018,8,9),LocalDate.of(2018,8,19)));
        listTransaksi.add(new Transaksi(175220133,1750311,LocalDate.of(2018,8,20),LocalDate.of(2018,8,22)));
         
    } 
    public void addTransaksi(Transaksi transaksi) throws Exception{
        listTransaksi.add(new Transaksi(transaksi.getIdNumber2(),transaksi.getIdBuku2(),transaksi.getMeminjam2(),transaksi.getMengembalikan2()));
    }
    public void hapus(int i){
        listTransaksi.remove(i);
    }
    public int index(Transaksi transaksi){
        int j = 0;
        for (int i = 0 ;i<listTransaksi.size();i++){
            if (listTransaksi.get(i).getIdNumber2() == transaksi.getIdNumber2()){
                j = i;
            }
        }
        return j;
    }
    public void setTransaksi(int i,Transaksi transaksi) throws Exception{
        
        this.transaksi = listTransaksi.get(i);
        this.transaksi.setIdNumber(transaksi.getIdNumber2());
        this.transaksi.setIdTransaksi();
        this.transaksi.setNamaDepan(transaksi.getIdNumber2());
        this.transaksi.setNamaBelakang(transaksi.getIdNumber2());
        this.transaksi.setIdBuku(transaksi.getIdBuku2());
        this.transaksi.setPenulis(transaksi.getIdBuku2());
        this.transaksi.setJudul(transaksi.getIdBuku2());
        
        this.transaksi.setMeminjam(transaksi.getMeminjam2());
        
            this.transaksi.setMengembalikan(transaksi.getMengembalikan2());
            this.transaksi.setDurasi(this.transaksi.getDurMeminjam(),this.transaksi.getDurMengembalikan());
        
    }
     
    public int ukuran(){
        return listTransaksi.size();
    }
    public Transaksi getTransaksi(int i){
        transaksi = listTransaksi.get(i);
        return transaksi;
    }
    public void setDurasi(){
        int i = 0;
        while(i<listTransaksi.size()){
                transaksi = listTransaksi.get(i);
                durasiAll.add( transaksi.getDurasi2());
                
            
            i++;
        }
    }
    //set barchart
    public void setBarChart(){
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        monthNames.addAll(Arrays.asList(months));
    }
    public String getBarchart(int i){ 
        return monthNames.get(i);
    }
    public ObservableList<String> getBarchart(){
      return monthNames;
    }
    
    public void setBulan(){
        
        for(int j = 0;j<12;j++){
            int month = 0;
            for(int i=0; i<listTransaksi.size() ; i++){
                transaksi = listTransaksi.get(i);
                
                if (transaksi.getMeminjam2().getMonthValue()==j+1){
                    month = month + transaksi.getDurasi2();
                    
                }
                monthCounter[j] = month;
            }
        }
    }
    public int getBulan(int i){
        return monthCounter[i];
    }
    
    //statistic 
    public double setMean(){
        setDurasi();
        double temp = 0;
        int n = 0;
        for (int i = 0;i<durasiAll.size();i++){
            temp = temp+durasiAll.get(i);
            n++;
        }
            
        return temp/n;
    }
    
    public int setModus(){
        setDurasi();
        int temp=0;
        for (int i = 0;i<durasiAll.size();i++){
            if (temp <= durasiAll.get(i)) temp = durasiAll.get(i);
        }return temp;
    }
    public void urut(){
        setDurasi();
        int j=0;
        int m = 0;
        ObservableList<Integer> temp = FXCollections.observableArrayList(); 
        for (int i = 0;i<durasiAll.size();i++){
            for (int k = 0;k<durasiAll.size()-1;k++){ 
                if (durasiAll.get(k)>durasiAll.get(k+1)){
                    m = k+1;
                    j=durasiAll.get(k);
                    durasiAll.set(k,durasiAll.get(m));
                    durasiAll.set(m,j);
                }
            }
        }
    }
    public double setMedian(){
        urut();
        double temp = 0;
        
        int j =durasiAll.size();
        if (durasiAll.size()%2 == 0){
            temp =(durasiAll.get((j/2)-1) + durasiAll.get(j/2))/2;
        }else{
            temp = durasiAll.get((durasiAll.size()-1)/2);
        }
        return temp;
    }
    
    public  void write()throws Exception {
        try{
            
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Transaksi");
            doc.appendChild(rootElement);

            for(int i=0; i<listTransaksi.size();i++){

                Element elemen_mahasiswa = doc.createElement("Transaksi");
                elemen_mahasiswa.setAttribute("idTransaksi", ""+listTransaksi.get(i).getIdTransaksi2());
                rootElement.appendChild(elemen_mahasiswa);
                
                Element fieldId = doc.createElement("idTransaksi");
                fieldId.setTextContent(String.valueOf(listTransaksi.get(i).getIdTransaksi2()));
                elemen_mahasiswa.appendChild(fieldId);
                
                Element fieldNama = doc.createElement("idAnggota");
                fieldNama.setTextContent(String.valueOf(listTransaksi.get(i).getIdNumber2()));
                elemen_mahasiswa.appendChild(fieldNama);

                Element fieldAlamat = doc.createElement("idBuku");
                fieldAlamat.setTextContent(String.valueOf(listTransaksi.get(i).getIdBuku2()));
                elemen_mahasiswa.appendChild(fieldAlamat);
                
                Element fieldYear = doc.createElement("year");
                fieldYear.setTextContent(String.valueOf(listTransaksi.get(i).getMeminjam2()));
                elemen_mahasiswa.appendChild(fieldYear );
                
                Element fieldMonth = doc.createElement("month");
                fieldMonth.setTextContent(String.valueOf(listTransaksi.get(i).getMengembalikan2()));
                elemen_mahasiswa.appendChild(fieldMonth );
                
                
                
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Transaksi.xml"));
            transformer.transform(dom, result);
        }
        catch(Exception e){
            System.out.println("ERROR : "+e.getMessage());
        }
    }
    
    public void read() throws Exception{
        
        String RootElemen, RowElemen;
       
        
        
       File fileXML = new File("Transaksi.xml");
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(fileXML);
       doc.getDocumentElement().normalize();
       
       RootElemen = doc.getDocumentElement().getNodeName();
       NodeList nList = doc.getElementsByTagName("Transaksi");
       
       for (int i = 1; i < nList.getLength(); i++) {
          Node nNode = nList.item(i);
          RowElemen = nNode.getNodeName();
          //System.out.println("\nCurrent Element :" + nNode.getNodeName());
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             transaksi = new Transaksi();
             transaksi.setIdTransaksi(Integer.parseInt(eElement.getElementsByTagName("idTransaksi").item(0).getTextContent()));
             transaksi.setIdNumber(Integer.parseInt(eElement.getElementsByTagName("idAnggota").item(0).getTextContent()));
             transaksi.setNamaDepan(Integer.parseInt(eElement.getElementsByTagName("idAnggota").item(0).getTextContent()));
             transaksi.setNamaBelakang(Integer.parseInt(eElement.getElementsByTagName("idAnggota").item(0).getTextContent()));
             transaksi.setIdBuku(Integer.parseInt(eElement.getElementsByTagName("idBuku").item(0).getTextContent()));
             transaksi.setJudul(Integer.parseInt(eElement.getElementsByTagName("idBuku").item(0).getTextContent()));
             transaksi.setPenulis(Integer.parseInt(eElement.getElementsByTagName("idBuku").item(0).getTextContent()));
             transaksi.setMeminjam(LocalDate.parse(eElement.getElementsByTagName("year").item(0).getTextContent()));
             transaksi.setMengembalikan(LocalDate.parse(eElement.getElementsByTagName("month").item(0).getTextContent()));
             transaksi.setDurasi(transaksi.getDurMeminjam(),transaksi.getDurMengembalikan());
          
             listTransaksi.add(transaksi);
         }
           
       } 
    }
}
