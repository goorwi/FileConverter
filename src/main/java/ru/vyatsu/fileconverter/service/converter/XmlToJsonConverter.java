package ru.vyatsu.fileconverter.service.converter;

import lombok.val;
import ru.vyatsu.fileconverter.service.Converter;
import ru.vyatsu.fileconverter.exception.ConvertingException;
import ru.vyatsu.fileconverter.model.Phone;
import ru.vyatsu.fileconverter.model.Phones;

import javax.json.*;
import javax.json.stream.JsonGenerator;

import javax.xml.bind.JAXBContext;

import java.io.*;
import java.util.Map;


public class XmlToJsonConverter implements Converter {
    public void convert(final String sourceFilePath, final String destinationFilePath) throws ConvertingException {
        val phones = readXml(sourceFilePath);
        writeJson(destinationFilePath, phones);
    }

    private Phones readXml(final String path) throws ConvertingException {
        try {
            val file = new File(path);
            if (!file.exists()) throw new ConvertingException("Файл не был найден!");
            return (Phones) JAXBContext.newInstance(Phones.class)
                    .createUnmarshaller()
                    .unmarshal(file);
        } catch (Exception thrown) {
            throw new ConvertingException("Ошибка при чтении файла!", thrown);
        }
    }

    private void writeJson(final String path, final Phones phones) throws ConvertingException {
        val objectBuilder = Json.createObjectBuilder();
        val modelsBuilder = Json.createArrayBuilder();

        for (Phone phone : phones.getPhones()) {
            val modelBuilder = Json.createObjectBuilder();
            val phoneBuilder = Json.createObjectBuilder();
            val specBuilder = Json.createObjectBuilder();

            modelBuilder.add("model", phone.getModel());
            phoneBuilder.add("brand", phone.getBrand());
            phoneBuilder.add("color", phone.getColor());
            phoneBuilder.add("price", phone.getPrice());
            specBuilder.add("cpu", phone.getSpecifications().getCpu());
            specBuilder.add("storage", phone.getSpecifications().getStorage());
            phoneBuilder.add("specifications", specBuilder);
            modelBuilder.add("phone", phoneBuilder);

            modelsBuilder.add(modelBuilder);
        }
        objectBuilder.add("models", modelsBuilder);
        try {
            File outputFile = new File(path);
            boolean isCreated = false;
            if (!outputFile.exists()) {
                isCreated = outputFile.createNewFile();
            }
            if (!isCreated) throw new ConvertingException("Не удаётся создать файл с именем: " + path);
        } catch (Exception thrown) {
            throw new ConvertingException("Ошибка при записи файла!", thrown);
        }

        try (OutputStream outputStream = new FileOutputStream(path)) {
            JsonWriter jsonWriter = Json.createWriterFactory(Map.of(JsonGenerator.PRETTY_PRINTING, true))
                    .createWriter(outputStream);

            jsonWriter.writeObject(objectBuilder.build());
            jsonWriter.close();
        } catch (Exception thrown) {
            throw new ConvertingException("Ошибка при записи файла!", thrown);
        }
    }
}
