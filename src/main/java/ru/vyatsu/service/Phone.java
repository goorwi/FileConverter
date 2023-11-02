package ru.vyatsu.service;

import java.util.Spliterator;

public class Phone {
    private String brand;
    private String model;
    private String color;
    private Integer price;
    private Specifications specifications;

    public void setBrand(String brand) {this.brand = brand;}
    public String getBrand() {return this.brand;}
    public void setModel(String model) {this.model = model;}
    public String getModel() {return this.model;}
    public void setColor(String color) {this.color = color;}
    public String getColor() {return this.color;}
    public void setPrice(Integer price) {this.price = price;}
    public Integer getPrice() {return this.price;}
    public void setSpecifications(Specifications specifications) {this.specifications = specifications;}
    public Specifications getSpecifications() {return this.specifications;}

}
