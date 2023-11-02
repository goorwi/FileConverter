package ru.vyatsu.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

public class Specifications {
    private String cpu;
    private String storage;
    public void setCpu(String cpu) {this.cpu = cpu;}
    @XmlElement
    public String getCpu() {return this.cpu;}
    public void setStorage(String storage) {this.storage = storage;}
    @XmlElement
    public String getStorage() {return this.storage;}
}
