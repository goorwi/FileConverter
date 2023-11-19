import lombok.val;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.Convertable;
import ru.vyatsu.service.ConvertingException;
import ru.vyatsu.service.converters.JsonToXmlConverter;
import ru.vyatsu.service.converters.XmlToJsonConverter;

import javax.json.JsonArray;
import java.io.File;
import java.nio.file.Paths;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConvertersTests {
    @Test
    void testConvertXmlToJson() {
        try {
            XmlToJsonConverter converter = new XmlToJsonConverter();
            converter.convert("src\\test\\resources\\data.xml", "src\\test\\resources\\test.json");

            assertTrue(FileUtils.contentEquals(new File("src\\test\\resources\\data.json"), new File("src\\test\\resources\\test.json")));

            val outputFile = new File("src\\test\\resources\\test.json");
            outputFile.delete();
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    void testConvertJsonToXml() {
        try {
            JsonToXmlConverter converter = new JsonToXmlConverter();
            converter.convert("src\\test\\resources\\data.json", "src\\test\\resources\\test.xml");

            assertTrue(FileUtils.contentEquals(new File("src\\test\\resources\\data.xml"), new File("src\\test\\resources\\test.xml")));

            val outputFile = new File("src\\test\\resources\\test.xml");
            outputFile.delete();
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }
}
