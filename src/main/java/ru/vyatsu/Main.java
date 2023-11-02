package ru.vyatsu;

import ru.vyatsu.service.Phones;
import ru.vyatsu.service.XmlToJsonConverter;

public class Main {
    public static void main(String[] args) throws Exception {
        XmlToJsonConverter xml = new XmlToJsonConverter();
        xml.Convert("src\\main\\resources\\data.xml", "src\\main\\resources\\outData.json");
    }
}