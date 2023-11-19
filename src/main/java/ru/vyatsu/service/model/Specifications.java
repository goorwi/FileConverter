package ru.vyatsu.service.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Specifications {
    @XmlElement(name = "cpu")
    private String cpu;
    @XmlElement(name = "storage")
    private String storage;

    public Boolean isNull() {
        return (cpu == null && storage == null);
    }
}
