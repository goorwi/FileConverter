package ru.vyatsu.service;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlToJsonConverter {
    public void Convert() throws JAXBException, FileNotFoundException {
        Phones phones = ReadXml("src\\main\\resources\\data.xml");
        WriteJson("src\\main\\resources\\outData.json", phones);
    }
    private Phones ReadXml(String path) throws JAXBException {
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(Phones.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Phones) jaxbUnmarshaller.unmarshal(file);
    }
    private void WriteJson(String path, Phones phones) throws FileNotFoundException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder modelsBuilder = Json.createArrayBuilder();

        for (Phone p : phones.getPhones())
        {
            JsonObjectBuilder modelBuilder = Json.createObjectBuilder();
            JsonObjectBuilder phoneBuilder = Json.createObjectBuilder();
            JsonObjectBuilder specBuilder = Json.createObjectBuilder();

            modelBuilder.add("model", p.getModel());
            phoneBuilder.add("brand", p.getBrand());
            phoneBuilder.add("color", p.getColor());
            phoneBuilder.add("price", p.getPrice());
            specBuilder.add("cpu",p.getSpecifications().getCpu());
            specBuilder.add("storage",p.getSpecifications().getStorage());
            phoneBuilder.add("specifications", specBuilder);
            modelBuilder.add("phone", phoneBuilder);

            modelsBuilder.add(modelBuilder);
        }
        objectBuilder.add("models", modelsBuilder);

        JsonObject jo = objectBuilder.build();

        OutputStream os = new FileOutputStream(path);
        JsonWriter jsonWriter = Json.createWriter(os);

        Map< String, Boolean > config = new HashMap< String, Boolean >();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory factory = Json.createWriterFactory(config);
        jsonWriter = factory.createWriter(os);

        jsonWriter.writeObject(jo);
        jsonWriter.close();
    }
}
