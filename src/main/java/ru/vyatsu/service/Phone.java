package ru.vyatsu.service;

import javax.xml.bind.annotation.*;

public class Phone {

    private String brand;
    private String model;
    private String color;
    private Integer price;
    private Specifications specifications;

    public void setBrand(String brand) {this.brand = brand;}
    @XmlAttribute
    public String getBrand() {return this.brand;}

    public void setModel(String model) {this.model = model;}
    @XmlElement
    public String getModel() {return this.model;}

    public void setColor(String color) {this.color = color;}
    @XmlElement
    public String getColor() {return this.color;}

    public void setPrice(Integer price) {this.price = price;}
    @XmlElement
    public Integer getPrice() {return this.price;}

    public void setSpecifications(Specifications specifications) {this.specifications = specifications;}
    @XmlElement
    public Specifications getSpecifications() {return this.specifications;}

}
