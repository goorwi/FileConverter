import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.vyatsu.fileconverter.exception.ConvertingException;
import ru.vyatsu.fileconverter.service.converter.JsonToXmlConverter;
import ru.vyatsu.fileconverter.service.converter.XmlToJsonConverter;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConvertersTests {
    private URL xmlFilePath = getClass().getClassLoader().getResource("data.xml");
    private URL jsonFilePath = getClass().getClassLoader().getResource("data.json");

    @Test
    void testConvertXmlToJson(@TempDir Path tempDir) throws ConvertingException, URISyntaxException {
        val tempFile = tempDir.resolve("testData.json").toString();
        new XmlToJsonConverter().convert(new File(xmlFilePath.toURI()).getPath(), tempFile);

        assertTrue(new JsonToXmlConverter().readJson(new File(jsonFilePath.toURI()).getPath())
                .equals(new JsonToXmlConverter().readJson(tempFile)));
    }

    @Test
    void testConvertJsonToXml(@TempDir Path tempDir) throws ConvertingException, URISyntaxException {
        val tempFile = tempDir.resolve("testData.xml").toString();
        new JsonToXmlConverter().convert(new File(jsonFilePath.toURI()).getPath(), tempFile);

        assertTrue(new XmlToJsonConverter().readXml(new File(xmlFilePath.toURI()).getPath())
                .equals(new XmlToJsonConverter().readXml(tempFile)));
    }
}
