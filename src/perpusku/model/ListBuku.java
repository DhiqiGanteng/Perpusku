/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class ListBuku {
    private ObservableList<Buku> listBuku;
    private ObservableList<Integer> durasi;
    private String extFile;
    private Buku buku;
   ;
    private boolean ada;
    private int index;
    
    public ListBuku(){
        listBuku = FXCollections.observableArrayList();
        durasi = FXCollections.observableArrayList();      
        ada = false;
        extFile = "Buku.xml";
    }
    
    public ObservableList<Buku> getListBuku(){
        return listBuku;
    }
    
    public void addBuku(Buku buku){
        listBuku.add(new Buku(buku.getJudul2(),buku.getPenulis2()));
    }
    public void hapus(int i){
        listBuku.remove(i);
    }
    public int index(Buku buku){
        int j = 0;
        for (int i = 0 ;i<listBuku.size();i++){
            if (listBuku.get(i).getIdBuku2() == buku.getIdBuku2()){
                j = i;
            }
        }
        return j;
    }
    public void setBuku(int i,Buku buku) throws Exception{
        this.buku = listBuku.get(i);
        this.buku.setIdBuku();
        this.buku.setJudul(buku.getJudul2());
        this.buku.setPenulis(buku.getPenulis2());
        this.buku.setDurasi();
        
    }
     public void setFirstBuku(){
        listBuku.add(new Buku("Dilan","Parjo"));
        listBuku.add(new Buku("LaskarPelangi","Nafa"));
        listBuku.add(new Buku("HIJRAH","Faras"));
    }
    
    public Buku search(int id){
        int i = 0;
        while(i<listBuku.size()){
            if (id == listBuku.get(i).getIdBuku2()){
                buku = listBuku.get(i);
                ada = true;
            }
            i++;
        }
        return buku;
    }
    
    
    public boolean getAdaBuku(){
        return ada;
    }
    public void setDummyDurasi() throws Exception{
        ListTransaksi listTransaksi = new ListTransaksi();
        listTransaksi.read();
        int du;
        for (int i =0 ; i < listBuku.size() ;i++){
            du = 0;
            for (int j=0 ; j<listTransaksi.ukuran();j++ ){
                if (listBuku.get(i).getIdBuku2()== listTransaksi.getTransaksi(j).getIdBuku2()){
                    du = du + listTransaksi.getTransaksi(j).getDurasi2();
                }
            }
            buku = listBuku.get(i);
            buku.setDurasi2(du);
        }
    }
     public void setDurasi(){
        int i = 0;
        while(i<listBuku.size()){
                buku = listBuku.get(i);
                durasi.add( buku.getDurasi2());
                
            
            i++;
        }
    }
    public double setMean(){
        setDurasi();
        double temp = 0;
        int n = 0;
        for (int i = 0;i<durasi.size();i++){
            temp = temp+durasi.get(i);
            n++;
        }
            
        return temp/n;
    }
    
    public int setModus(){
        setDurasi();
        int temp=0;
        for (int i = 0;i<durasi.size();i++){
            if (temp <= durasi.get(i)) temp = durasi.get(i);
        }return temp;
    }
    public void urut(){
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
    public double setMedian(){
        urut();
        double temp = 0;
        int j =durasi.size();
        if (durasi.size()%2 == 0){
            temp =(durasi.get((j/2)-1) + durasi.get(j/2))/2;
        }else{
            temp = durasi.get((durasi.size()-1)/2);
        }
        return temp;
    }
    
    public  void write()throws Exception {
        try{
            // TODO code application logic here
            
            /*members.add(new member(
            "Elang Putra Sartika", "4321", "16523169", "FTI", "Teknik Informatika", "Jl Mijil no. 35 condong catrur",
            "16523169@students.uii.ac.id", "081335881757", "Banjarbaru", "2000-4-10"));
            members.add(new member(
            "Agung Ramadhan Putra", "1234", "16523165", "FTI", "Teknik Informatika", "Deket shafa 2 disitulah pokoknya",
            "16523165@students.uii.ac.id", "081123213213", "Bengkulu", "1998-0-00"));
            members.add(new member(
            "Khukuh Anugrah Yumawahendra", "4123", "16523179", "FTI", "Teknik Informatika", "Deket panti rehab khukuh direhab",
            "16523179@students.uii.ac.id", "081241431124", "Djambi", "1998-5-27"));*/
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Buku");
            doc.appendChild(rootElement);

            for(int i=0; i<listBuku.size();i++){

                Element elemen_mahasiswa = doc.createElement("Buku");
                elemen_mahasiswa.setAttribute("id", ""+listBuku.get(i).getIdBuku2());
                rootElement.appendChild(elemen_mahasiswa);
                
                Element fieldId = doc.createElement("id");
                fieldId.setTextContent(String.valueOf(listBuku.get(i).getIdBuku2()));
                elemen_mahasiswa.appendChild(fieldId);
                
                Element fieldNama = doc.createElement("judul");
                fieldNama.setTextContent(listBuku.get(i).getJudul2());
                elemen_mahasiswa.appendChild(fieldNama);

                Element fieldFakultas = doc.createElement("penulis");
                fieldFakultas.setTextContent(listBuku.get(i).getPenulis2());
                elemen_mahasiswa.appendChild(fieldFakultas);

                Element fieldEmail = doc.createElement("durasi");
                fieldEmail.setTextContent(String.valueOf(listBuku.get(i).getDurasi2()));
                elemen_mahasiswa.appendChild(fieldEmail);

                
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Buku.xml"));
            transformer.transform(dom, result);
        }
        catch(Exception e){
            System.out.println("ERROR : "+e.getMessage());
        }
    }
    
    public void read() throws Exception{
        // TODO code application logic hereList <member> mahasiswa = new ArrayList ();
        String namaDepan,namaBelakang,alamat,RootElemen, RowElemen;
        int umur, durasi,id;
        float ipk;
        
        
       File fileXML = new File("Buku.xml");
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(fileXML);
       doc.getDocumentElement().normalize();
       
       RootElemen = doc.getDocumentElement().getNodeName();
       NodeList nList = doc.getElementsByTagName("Buku");
       
       for (int i = 1; i < nList.getLength(); i++) {
          Node nNode = nList.item(i);
          RowElemen = nNode.getNodeName();
          //System.out.println("\nCurrent Element :" + nNode.getNodeName());
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             buku = new Buku();
             buku.setIdBuku(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
             buku.setJudul(eElement.getElementsByTagName("judul").item(0).getTextContent());
             buku.setPenulis(eElement.getElementsByTagName("penulis").item(0).getTextContent());
             buku.setDurasi2(Integer.parseInt(eElement.getElementsByTagName("durasi").item(0).getTextContent()));
          
             listBuku.add(buku);
         }
           
       } 
    }
}
