package ru.vyatsu.service;

import javax.xml.bind.annotation.XmlElement;

public class Specifications {
    private String cpu;
    private String storage;
    public void setCpu(String cpu) {this.cpu = cpu;}
    @XmlElement
    public String getCpu() {return this.cpu;}
    public void setStorage(String storage) {this.storage = storage;}
    @XmlElement
    public String getStorage() {return this.storage;}
    public Boolean isNull()
    {
        if (cpu == null && storage == null) return true;
        else return false;
    }
}
