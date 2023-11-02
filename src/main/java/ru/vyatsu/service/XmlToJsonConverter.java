package ru.vyatsu.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class XmlToJsonConverter {
    public Phones Convert() throws JAXBException, FileNotFoundException {
        return ReadXml("src\\main\\resources\\data.xml");
    }
    private Phones ReadXml(String path) throws JAXBException, FileNotFoundException {
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(Phones.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Phones) jaxbUnmarshaller.unmarshal(file);
    }
}
