import lombok.val;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.vyatsu.fileconverter.service.converter.JsonToXmlConverter;
import ru.vyatsu.fileconverter.service.converter.XmlToJsonConverter;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConvertersTests {
    private final String xmlFilePath = "src\\test\\resources\\data.xml";
    private final String jsonXmlPath = "src\\test\\resources\\data.json";

    @Test
    void testConvertXmlToJson(@TempDir Path tempDir) {
        try {
            val tempFile = tempDir.resolve("testData.json").toString();
            new XmlToJsonConverter().convert(xmlFilePath, tempFile);

            assertTrue(FileUtils.contentEquals(new File(jsonXmlPath), new File(tempFile)));
        } catch (Exception thrown) {
            Assertions.fail(thrown.getMessage());
        }
    }

    @Test
    void testConvertJsonToXml(@TempDir Path tempDir) {
        try {
            val tempFile = tempDir.resolve("testData.xml").toString();
            new JsonToXmlConverter().convert(jsonXmlPath, tempFile);

            assertTrue(FileUtils.contentEquals(new File(xmlFilePath), new File(tempFile)));
        } catch (Exception thrown) {
            Assertions.fail(thrown.getMessage());
        }
    }
}
