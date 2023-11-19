package ru.vyatsu.service.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@XmlType(propOrder = {"brand", "model", "color", "price", "specifications"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Phone {
    @XmlAttribute(name = "name")
    String brand;
    @XmlElement(name = "model")
    String model;
    @XmlElement(name = "color")
    String color;
    @XmlElement(name = "price")
    Integer price;
    @XmlElement(name = "specifications")
    Specifications specifications;

    public Boolean isNull() {
        return (brand == null && model == null && color == null && price == null && specifications == null);
    }
}
