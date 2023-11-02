package ru.vyatsu.service;

public class Specifications {
    private String cpu;
    private String storage;
    public void setCpu(String cpu) {this.cpu = cpu;}
    public String getCpu() {return this.cpu;}
    public void setStorage(String storage) {this.storage = storage;}
    public String getStorage() {return this.storage;}
    public Boolean isNull()
    {
        if (cpu == null && storage == null) return true;
        else return false;
    }
}
