package ru.vyatsu;

import ru.vyatsu.service.JsonToXmlConverter;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JAXBException {
        JsonToXmlConverter j = new JsonToXmlConverter();
        j.Convert("src\\main\\resources\\data.json", "src\\main\\resources\\outData.xml");



    }
}