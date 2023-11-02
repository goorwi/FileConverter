package ru.vyatsu.service;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonToXmlConverter {
    public void Convert(String in, String out) throws FileNotFoundException, JAXBException {
        Phones phones = ReadJson(in);

    }

    private Phones ReadJson(String path) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(path);

        JsonParserFactory factory = Json.createParserFactory(null);
        JsonParser parser = factory.createParser(inputStream, StandardCharsets.UTF_8);
        String keyName = null;

        if (!parser.hasNext() && parser.next() != JsonParser.Event.START_ARRAY) return null;

        Phones phones = new Phones();
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        Specifications spec = new Specifications();

        boolean isNew = false;
        while(parser.hasNext())
        {
            JsonParser.Event event = parser.next();
            switch (event)
            {
                case KEY_NAME -> {
                    keyName = parser.getString();
                    break;
                }
                case VALUE_STRING -> {
                    SetStringValue(phone,spec,keyName,parser.getString());
                    break;
                }
                case VALUE_NUMBER -> {
                    if (keyName.equals("price")) {
                        phone.setPrice(parser.getInt());
                    }
                    break;
                }
                case END_OBJECT -> {
                    if (!phone.isNull()) {
                        phone.setSpecifications(spec);
                        phoneList.add(phone);
                        phone = new Phone();
                        spec = new Specifications();
                    }
                    break;
                }
                default -> {
                    break;
                }
            }
        }
        phones.setPhones(phoneList);


        return null;
    }
    private void SetStringValue(Phone phone, Specifications spec, String key, String value){
        switch (key)
        {
            case "model" -> {
                phone.setModel(value);
                break;
            }
            case "brand" -> {
                phone.setBrand(value);
                break;
            }
            case "color" -> {
                phone.setColor(value);
                break;
            }
            case "cpu" -> {
                spec.setCpu(value);
                break;
            }
            case "storage" -> {
                spec.setStorage(value);
                break;
            }
            default -> {break;}
        }
    }
}
