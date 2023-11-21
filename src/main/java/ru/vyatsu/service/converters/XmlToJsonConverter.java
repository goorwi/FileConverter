package ru.vyatsu.service.converters;

import lombok.val;
import ru.vyatsu.service.Convertable;
import ru.vyatsu.service.ConvertingException;
import ru.vyatsu.service.model.Phone;
import ru.vyatsu.service.model.Phones;

import javax.json.*;
import javax.json.stream.JsonGenerator;

import javax.xml.bind.JAXBContext;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static ru.vyatsu.Main.logger;

public class XmlToJsonConverter implements Convertable {
    public void convert(String in, String out) throws ConvertingException {
        var phones = readXml(in);
        logger.info("Данные из файла xml считаны.");
        writeJson(out, phones);
        logger.info("Данные записаны в файл json.");
    }

    private Phones readXml(String path) throws ConvertingException {
        try {
            val file = new File(path);
            val jaxbContext = JAXBContext.newInstance(Phones.class);

            val jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Phones) jaxbUnmarshaller.unmarshal(file);
        } catch (Exception ex) {
            throw new ConvertingException("Ошибка при чтении файла!", ex);
        }
    }

    private void writeJson(String path, Phones phones) throws ConvertingException {
        val objectBuilder = Json.createObjectBuilder();
        val modelsBuilder = Json.createArrayBuilder();

        for (Phone p : phones.getPhones()) {
            val modelBuilder = Json.createObjectBuilder();
            val phoneBuilder = Json.createObjectBuilder();
            val specBuilder = Json.createObjectBuilder();

            modelBuilder.add("model", p.getModel());
            phoneBuilder.add("brand", p.getBrand());
            phoneBuilder.add("color", p.getColor());
            phoneBuilder.add("price", p.getPrice());
            specBuilder.add("cpu", p.getSpecifications().getCpu());
            specBuilder.add("storage", p.getSpecifications().getStorage());
            phoneBuilder.add("specifications", specBuilder);
            modelBuilder.add("phone", phoneBuilder);

            modelsBuilder.add(modelBuilder);
        }
        objectBuilder.add("models", modelsBuilder);

        val jo = objectBuilder.build();

        try (OutputStream os = new FileOutputStream(path)) {
            JsonWriter jsonWriter;

            Map<String, Boolean> config = new HashMap<>();
            config.put(JsonGenerator.PRETTY_PRINTING, true);

            JsonWriterFactory factory = Json.createWriterFactory(config);
            jsonWriter = factory.createWriter(os);

            jsonWriter.writeObject(jo);
            jsonWriter.close();
        } catch (Exception ex) {
            throw new ConvertingException("Ошибка при записи файла!", ex);
        }
    }
}
