package ru.vyatsu.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
public class Phones {
    private List<Phone> phones;

    public void setPhones(List<Phone> phones) {this.phones = phones;}
    @XmlElement(name = "brand")
    public List<Phone> getPhones() {return this.phones;}
}
