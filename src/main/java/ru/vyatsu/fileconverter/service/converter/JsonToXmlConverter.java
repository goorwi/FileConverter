package ru.vyatsu.fileconverter.service.converter;

import lombok.val;
import ru.vyatsu.fileconverter.service.Converter;
import ru.vyatsu.fileconverter.exception.ConvertingException;
import ru.vyatsu.fileconverter.model.Phone;
import ru.vyatsu.fileconverter.model.Phones;
import ru.vyatsu.fileconverter.model.Specifications;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JsonToXmlConverter implements Converter {
    public void convert(final String sourceFilePath, final String destinationFilePath) throws ConvertingException {
        val phones = readJson(sourceFilePath);
        writeXml(destinationFilePath, phones);
    }

    private void writeXml(final String path, final Phones phones) throws ConvertingException {
        try {
            File outputFile = new File(path);
            boolean isCreated = false;
            if (!outputFile.exists()) {
                isCreated = outputFile.createNewFile();
            }
            if (!isCreated) throw new ConvertingException("Не удаётся создать файл с именем: " + path);

            Marshaller marshaller = JAXBContext.newInstance(Phones.class).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(phones, new File(path));
        } catch (Exception thrown) {
            throw new ConvertingException("Ошибка при записи файла!", thrown);
        }
    }

    private Phones readJson(final String path) throws ConvertingException {
        try {
            val file = new File(path);
            if (!file.exists()) throw new ConvertingException("Файл не был найден!");
        } catch (Exception thrown) {
            throw new ConvertingException("Ошибка при чтении файла!", thrown);
        }

        try (JsonParser parser = Json.createParserFactory(null).createParser(new FileInputStream(path), UTF_8)) {
            String keyName = "";

            if (!parser.hasNext() && parser.next() != JsonParser.Event.START_ARRAY) return null;

            val phones = new Phones();
            val phoneList = new ArrayList<Phone>();
            Phone phone = new Phone();
            Specifications specifications = new Specifications();

            while (parser.hasNext()) {
                switch (parser.next()) {
                    case KEY_NAME -> keyName = parser.getString();
                    case VALUE_STRING -> setStringValue(phone, specifications, keyName, parser.getString());
                    case VALUE_NUMBER -> {
                        if (keyName.equals("price")) {
                            phone.setPrice(parser.getInt());
                        }
                    }
                    case END_OBJECT -> {
                        if (Boolean.FALSE.equals(phone.isNull())) {
                            phone.setSpecifications(specifications);
                            phoneList.add(phone);
                            phone = new Phone();
                            specifications = new Specifications();
                        }
                    }
                }
            }
            phones.setPhones(phoneList);

            return phones;
        } catch (Exception thrown) {
            throw new ConvertingException("Ошибка при чтении файла!", thrown);
        }
    }

    private void setStringValue(Phone phone, Specifications spec, String key, String value) {
        switch (key) {
            case "model" -> phone.setModel(value);
            case "brand" -> phone.setBrand(value);
            case "color" -> phone.setColor(value);
            case "cpu" -> spec.setCpu(value);
            case "storage" -> spec.setStorage(value);
        }
    }
}
