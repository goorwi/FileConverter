package ru.vyatsu.service.converters;

import lombok.val;
import ru.vyatsu.service.Convertable;
import ru.vyatsu.service.ConvertingException;
import ru.vyatsu.service.model.Phone;
import ru.vyatsu.service.model.Phones;
import ru.vyatsu.service.model.Specifications;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static java.nio.charset.StandardCharsets.UTF_8;

public class JsonToXmlConverter implements Convertable {
    public void convert(String in, String out) throws ConvertingException {
        val phones = readJson(in);
        writeXml(out, phones);
    }

    private void writeXml(String path, Phones phones) throws ConvertingException {
        try {
            JAXBContext context = JAXBContext.newInstance(Phones.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(phones, new File(path));
        } catch (JAXBException ex) {
            throw new ConvertingException("Ошибка при чтении файла!", ex);
        }
    }

    private Phones readJson(String path) throws ConvertingException {
        try {
            FileInputStream inputStream = new FileInputStream(path);

            JsonParserFactory factory = Json.createParserFactory(null);
            JsonParser parser = factory.createParser(inputStream, UTF_8);
            String keyName = null;

            if (!parser.hasNext() && parser.next() != JsonParser.Event.START_ARRAY) return null;

            Phones phones = new Phones();
            List<Phone> phoneList = new ArrayList<>();
            Phone phone = new Phone();
            Specifications spec = new Specifications();

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case KEY_NAME -> keyName = parser.getString();
                    case VALUE_STRING -> setStringValue(phone, spec, keyName, parser.getString());
                    case VALUE_NUMBER -> {
                        if (keyName.equals("price")) {
                            phone.setPrice(parser.getInt());
                        }
                    }
                    case END_OBJECT -> {
                        if (!phone.isNull()) {
                            phone.setSpecifications(spec);
                            phoneList.add(phone);
                            phone = new Phone();
                            spec = new Specifications();
                        }
                    }
                    default -> {
                        break;
                    }
                }
            }
            phones.setPhones(phoneList);

            return phones;
        } catch (FileNotFoundException ex) {
            throw new ConvertingException("Ошибка при чтении файла!", ex);
        }
    }

    private void setStringValue(Phone phone, Specifications spec, String key, String value) {
        switch (key) {
            case "model" -> phone.setModel(value);
            case "brand" -> phone.setBrand(value);
            case "color" -> phone.setColor(value);
            case "cpu" -> spec.setCpu(value);
            case "storage" -> spec.setStorage(value);
            default -> {
                break;
            }
        }
    }
}
