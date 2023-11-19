package ru.vyatsu.service.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Phones {
    @XmlElement(name = "brand")
    List<Phone> phones;
}
