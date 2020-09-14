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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Report {
    private IntegerProperty no;
    private StringProperty interval;
    private IntegerProperty frekuensi;
    private IntegerProperty bb;
    private IntegerProperty ba;
    
    public Report(int no,int fre,int bb,int ba){
        this.no = new SimpleIntegerProperty(no);
        frekuensi = new SimpleIntegerProperty(fre);
        this.bb = new SimpleIntegerProperty(bb);
        this.ba = new SimpleIntegerProperty(ba);
        
    }
    public Report(){
    this(0,0,0,0);
    }

    public IntegerProperty getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no.set(no);
    }

    public StringProperty getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval.set(interval);
    }

    public IntegerProperty getFrekuensi() {
        return frekuensi;
    }

    public void setFrekuensi(int frekuensi) {
        this.frekuensi.set(frekuensi);
    }
    public IntegerProperty getBB() {
        return bb;
    }

    public void setBB(int bb) {
        this.bb.set(bb);
    }
    public IntegerProperty getBA() {
        return ba;
    }

    public void setBA(int ba) {
        this.ba.set(ba);
    }
    
    
    
    
    
}
