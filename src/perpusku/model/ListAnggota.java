/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusku.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
import perpusku.Perpusku;





public class ListAnggota {
    
    private ObservableList<Anggota> list;
    private ObservableList<Integer> durasi;
    private String extFile;
    private Anggota anggota;
   
    
    private boolean ada;
    private int index;
    
    public ListAnggota(){
        list = FXCollections.observableArrayList();
        
        durasi = FXCollections.observableArrayList();      
        
        ada = false;
        extFile = "Anggota.xml";
    }
    
    
    
    public ObservableList<Anggota> getListAnggota(){
        return list;
    }
    public int panjang(){
        return list.size();
    }
    public void addAnggota(Anggota anggota){
        list.add(new Anggota(anggota.getNamaDepan2(),anggota.getNamaBelakang2(),anggota.getAlamat2(),anggota.getUmur2()));
    }
    public void hapus(int i){
        list.remove(i);
    }
    public int index(Anggota anggota){
        int j = 0;
        for (int i = 0 ;i<list.size();i++){
            if (list.get(i).getIdNumber2() == anggota.getIdNumber2()){
                j = i;
            }
        }
        return j;
    }
    public void setAnggota(int i,Anggota anggota) throws Exception{
        
        this.anggota = list.get(i);
        this.anggota.setIdNumber(this.anggota.getUmur2());
        this.anggota.setNamaDepan(anggota.getNamaDepan2());
        this.anggota.setNamaBelakang(anggota.getNamaBelakang2());
        this.anggota.setAlamat(anggota.getAlamat2());
        this.anggota.setUmur(anggota.getUmur2());
        this.anggota.setDurasi();
    }
    public Anggota search(int id){
        for (int i = 0;i<list.size();i++){
            if (id == list.get(i).getIdNumber2()){
                anggota = list.get(i);
                ada = true;
            }
        }
        return anggota;
    }
    
    
    public void setDummy(){
        
        list.add(new Anggota("Fatih","Assidhiqi","Bumijawa",25));
       list.add(new Anggota("Naura","Faisa","Slawi",17));
        list.add(new Anggota("Faras","Maharani","Cilegon",18));
        
    }
    
    public boolean getAdaAnggota(){
        return ada;
    }
    
    
    
    public void setDummyDurasi() throws Exception{
        ListTransaksi listTransaksi = new ListTransaksi();
        listTransaksi.read();
        int du;
        for (int i =0 ; i < list.size() ;i++){
            du = 0;
            for (int j=0 ; j<listTransaksi.ukuran();j++ ){
                if (list.get(i).getIdNumber2() == listTransaksi.getTransaksi(j).getIdNumber2()){
                    du = du + listTransaksi.getTransaksi(j).getDurasi2();
                }
            }
            anggota = list.get(i);
            anggota.setDurasi2(du);
        }
    }
    
     public void setDurasi(){
        int i = 0;
        while(i<list.size()){
                anggota = list.get(i);
                durasi.add( anggota.getDurasi2());
                
            
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
            
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("Anggota");
            doc.appendChild(rootElement);

            for(int i=0; i<list.size();i++){

                Element elemen_mahasiswa = doc.createElement("Anggota");
                elemen_mahasiswa.setAttribute("id", ""+list.get(i).getIdNumber2());
                rootElement.appendChild(elemen_mahasiswa);
                
                Element fieldId = doc.createElement("id");
                fieldId.setTextContent(String.valueOf(list.get(i).getIdNumber2()));
                elemen_mahasiswa.appendChild(fieldId);
                
                Element fieldNama = doc.createElement("namaDepan");
                fieldNama.setTextContent(list.get(i).getNamaDepan2());
                elemen_mahasiswa.appendChild(fieldNama);

                Element fieldFakultas = doc.createElement("namaBelakang");
                fieldFakultas.setTextContent(list.get(i).getNamaBelakang2());
                elemen_mahasiswa.appendChild(fieldFakultas);

                Element fieldJurusan = doc.createElement("alamat");
                fieldJurusan.setTextContent(list.get(i).getAlamat2());
                elemen_mahasiswa.appendChild(fieldJurusan);

                Element fieldAlamat = doc.createElement("umur");
                fieldAlamat.setTextContent(String.valueOf(list.get(i).getUmur2()));
                elemen_mahasiswa.appendChild(fieldAlamat);

                Element fieldEmail = doc.createElement("durasi");
                fieldEmail.setTextContent(String.valueOf(list.get(i).getDurasi2()));
                elemen_mahasiswa.appendChild(fieldEmail);

                
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Anggota.xml"));
            transformer.transform(dom, result);
        }
        catch(Exception e){
            System.out.println("ERROR : "+e.getMessage());
        }
    }
    
    public void read() throws Exception{
        
        String namaDepan,namaBelakang,alamat,RootElemen, RowElemen;
        int umur, durasi,id;
        float ipk;
        list.clear();
        
       File fileXML = new File("Anggota.xml");
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(fileXML);
       doc.getDocumentElement().normalize();
       
       RootElemen = doc.getDocumentElement().getNodeName();
       NodeList nList = doc.getElementsByTagName("Anggota");
       
       for (int i = 1; i < nList.getLength(); i++) {
          Node nNode = nList.item(i);
          RowElemen = nNode.getNodeName();
          //System.out.println("\nCurrent Element :" + nNode.getNodeName());
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             anggota = new Anggota();
             anggota.setIdNumber2(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
             anggota.setNamaDepan(eElement.getElementsByTagName("namaDepan").item(0).getTextContent());
             anggota.setNamaBelakang(eElement.getElementsByTagName("namaBelakang").item(0).getTextContent());
             
             anggota.setAlamat( eElement.getElementsByTagName("alamat").item(0).getTextContent());
             anggota.setUmur(Integer.parseInt(eElement.getElementsByTagName("umur").item(0).getTextContent()));
             anggota.setDurasi2(Integer.parseInt(eElement.getElementsByTagName("durasi").item(0).getTextContent()));
          
             list.add(anggota);
         }
           
       } 
    }
}
