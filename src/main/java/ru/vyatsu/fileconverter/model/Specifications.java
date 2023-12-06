package ru.vyatsu.fileconverter.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Specifications {
    @XmlElement(name = "cpu")
    String cpu;
    @XmlElement(name = "storage")
    String storage;
}
