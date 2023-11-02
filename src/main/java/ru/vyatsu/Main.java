package ru.vyatsu;

import ru.vyatsu.service.Phones;
import ru.vyatsu.service.XmlToJsonConverter;

import javax.xml.bind.JAXBException;

public class Main {
    public static void main(String[] args) throws Exception {
        XmlToJsonConverter xml = new XmlToJsonConverter();
        Phones p = xml.Convert();




    }
}