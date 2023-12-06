package ru.vyatsu.fileconverter.service.converter;

import lombok.val;
import org.apache.commons.io.FileUtils;
import ru.vyatsu.fileconverter.exception.ConvertingException;
import ru.vyatsu.fileconverter.model.Phone;
import ru.vyatsu.fileconverter.model.Phones;

import javax.json.*;
import javax.json.stream.JsonGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class XmlToJsonConverter implements Converter {
    private JAXBContext jaxbContext;
    private JsonWriterFactory jsonWriterFactory;

    public void convert(final String sourceFilePath, final String destinationFilePath) throws ConvertingException {
        try {
            jsonWriterFactory = Json.createWriterFactory(Map.of(JsonGenerator.PRETTY_PRINTING, true));
            jaxbContext = JAXBContext.newInstance(Phones.class);
            writeJson(destinationFilePath, readXml(sourceFilePath));
        } catch (JAXBException thrown) {
            throw new ConvertingException("Произошла ошибка при создании JAXB контекста!", thrown);
        }
    }

    private Phones readXml(final String path) throws ConvertingException {
        try {
            val file = new File(path);
            if (!file.exists()) {
                throw new FileNotFoundException(String.format("Файл '%s' не был найден!", path));
            }
            return (Phones) jaxbContext.createUnmarshaller()
                    .unmarshal(file);
        } catch (JAXBException thrown) {
            throw new ConvertingException("Ошибка при чтении файла!", thrown);
        } catch (FileNotFoundException thrown) {
            throw new ConvertingException(thrown.getMessage(), thrown);
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
            val outputFile = new File(path);
            boolean isCreated = false;
            if (!outputFile.exists()) {
                isCreated = outputFile.createNewFile();
            }
            if (!isCreated) {
                throw new IOException(String.format("Не удаётся создать файл с именем: %s", path));
            }
        } catch (IOException thrown) {
            throw new ConvertingException(thrown.getMessage(), thrown);
        }

        try (JsonWriter jsonWriter = jsonWriterFactory.createWriter(new FileOutputStream(path))) {
            jsonWriter.writeObject(objectBuilder.build());
            FileUtils.writeStringToFile(new File(path), FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8).replace("\n", "\r\n"), StandardCharsets.UTF_8);
        } catch (Exception thrown) {
            throw new ConvertingException("Ошибка при записи файла!", thrown);
        }
    }
}
